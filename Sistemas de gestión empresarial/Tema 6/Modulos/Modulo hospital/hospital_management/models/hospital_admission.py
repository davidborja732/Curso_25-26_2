from datetime import date, timedelta

from odoo import models, fields, api, _
from odoo.exceptions import ValidationError


class HospitalAdmission(models.Model):
    _name = "hospital.admission"
    _description = "Ingreso de paciente"
    _order = "admission_date desc"

    name = fields.Char(
        string="Referencia",
        default=lambda self: _("Ingreso"),
        readonly=True,
    )

    patient_id = fields.Many2one(
        "hospital.patient",
        string="Paciente",
        required=True,
        ondelete="cascade",
    )
    doctor_id = fields.Many2one(
        "hospital.doctor",
        string="Médico",
        required=True,
        ondelete="restrict",
    )

    room_id = fields.Many2one(
        "hospital.room",
        string="Habitación",
        required=True,
        domain="[('is_full', '=', False)]",  # ahora sí funciona, porque is_full es store=True
    )

    bed = fields.Selection(
        [
            ("1", "Cama 1"),
            ("2", "Cama 2"),
        ],
        string="Cama",
        required=True,
    )

    admission_date = fields.Date(
        string="Fecha de ingreso",
        default=fields.Date.context_today,
        required=True,
    )

    discharge_date = fields.Date(
        string="Fecha de alta",
    )

    symptoms = fields.Text(string="Síntomas")

    active = fields.Boolean(string="Activo", default=True)

    days_hospitalized = fields.Integer(
        string="Días ingresado",
        compute="_compute_days_hospitalized",
        store=False,
    )

    diagnosis_ids = fields.One2many(
        "hospital.diagnosis", "admission_id", string="Diagnósticos"
    )

    @api.depends("admission_date", "discharge_date")
    def _compute_days_hospitalized(self):
        today = date.today()
        for rec in self:
            start = rec.admission_date or today
            end = rec.discharge_date or today
            rec.days_hospitalized = max(0, (end - start).days + 1)

    @api.constrains("admission_date")
    def _check_admission_date_range(self):
        today = date.today()
        one_week_ago = today - timedelta(days=7)
        for rec in self:
            if rec.admission_date and rec.admission_date < one_week_ago:
                raise ValidationError(
                    _("La fecha de ingreso solo puede ser como máximo una semana anterior.")
                )
            if rec.admission_date and rec.admission_date > today:
                raise ValidationError(
                    _("La fecha de ingreso no puede ser futura.")
                )

    @api.constrains("discharge_date")
    def _check_discharge_date(self):
        today = date.today()
        for rec in self:
            if rec.discharge_date and rec.admission_date and rec.discharge_date < rec.admission_date:
                raise ValidationError(
                    _("La fecha de alta no puede ser anterior a la fecha de ingreso.")
                )

    @api.constrains("room_id", "bed", "admission_date", "discharge_date")
    def _check_room_bed_and_capacity(self):
        for rec in self:
            if not rec.room_id or not rec.bed:
                continue

            overlapping = self.search([
                ("id", "!=", rec.id),
                ("room_id", "=", rec.room_id.id),
                ("discharge_date", "=", False),
            ])

            if len(overlapping) >= 2:
                raise ValidationError(
                    _("Las habitaciones son dobles. No puede haber más de 2 pacientes ingresados a la vez en la misma habitación.")
                )

            same_room_bed = self.search([
                ("id", "!=", rec.id),
                ("room_id", "=", rec.room_id.id),
                ("bed", "=", rec.bed),
                ("discharge_date", "=", False),
            ])
            if same_room_bed:
                raise ValidationError(
                    _("La combinación de habitación y cama ya está ocupada.")
                )

    @api.onchange("room_id")
    def _onchange_room_id(self):
        if self.room_id and self.room_id.is_full:
            return {
                "warning": {
                    "title": _("Habitación completa"),
                    "message": _("Esta habitación ya está ocupada por 2 pacientes."),
                }
            }
