from odoo import models, fields, api

class HospitalPatient(models.Model):
    _name = "hospital.patient"
    _description = "Paciente"

    name = fields.Char(string="Nombre", required=True)
    last_name = fields.Char(string="Apellidos", required=True)
    street = fields.Char(string="Dirección")
    city = fields.Char(string="Población")
    state = fields.Char(string="Provincia")
    zip = fields.Char(string="Código Postal")
    phone = fields.Char(string="Teléfono")
    birth_date = fields.Date(string="Fecha de nacimiento")

    admission_ids = fields.One2many(
        "hospital.admission", "patient_id", string="Ingresos"
    )

    current_admission_id = fields.Many2one(
        "hospital.admission",
        string="Ingreso actual",
        compute="_compute_current_admission",
        store=False,
    )

    current_room_id = fields.Many2one(
        "hospital.room",
        string="Habitación actual",
        related="current_admission_id.room_id",
        store=False,
    )

    current_doctor_id = fields.Many2one(
        "hospital.doctor",
        string="Médico actual",
        related="current_admission_id.doctor_id",
        store=False,
    )

    days_hospitalized_total = fields.Integer(
        string="Días totales ingresado",
        compute="_compute_days_hospitalized_total",
        store=False,
    )

    @api.depends("admission_ids", "admission_ids.days_hospitalized")
    def _compute_days_hospitalized_total(self):
        for patient in self:
            patient.days_hospitalized_total = sum(
                adm.days_hospitalized for adm in patient.admission_ids
            )

    def _compute_current_admission(self):
        for patient in self:
            current = patient.admission_ids.filtered(lambda a: not a.discharge_date)
            patient.current_admission_id = current[:1].id if current else False
