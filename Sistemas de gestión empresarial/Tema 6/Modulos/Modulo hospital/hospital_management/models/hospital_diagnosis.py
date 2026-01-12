from odoo import models, fields, api

class HospitalDiagnosis(models.Model):
    _name = "hospital.diagnosis"
    _description = "Diagnóstico por ingreso"

    name = fields.Char(string="Título", required=True)
    diagnosis_date = fields.Date(
        string="Fecha de diagnóstico",
        default=fields.Date.context_today,
        required=True,
    )

    admission_id = fields.Many2one(
        "hospital.admission",
        string="Ingreso",
        required=True,
        ondelete="cascade",
    )
    patient_id = fields.Many2one(
        "hospital.patient",
        string="Paciente",
        related="admission_id.patient_id",
        store=True,
    )
    doctor_id = fields.Many2one(
        "hospital.doctor",
        string="Médico",
        related="admission_id.doctor_id",
        store=True,
    )

    type = fields.Selection(
        [
            ("leve", "Leve"),
            ("moderado", "Moderado"),
            ("grave", "Grave"),
        ],
        string="Gravedad",
        required=True,
    )

    description = fields.Text(string="Descripción")
