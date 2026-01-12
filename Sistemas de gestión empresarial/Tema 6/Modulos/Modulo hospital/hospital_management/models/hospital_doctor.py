from odoo import models, fields, api, _
from odoo.exceptions import ValidationError

class HospitalDoctor(models.Model):
    _name = "hospital.doctor"
    _description = "Médico"

    name = fields.Char(string="Nombre", required=True)
    last_name = fields.Char(string="Apellidos", required=True)
    phone = fields.Char(string="Teléfono")
    specialty = fields.Char(string="Especialidad")
    license_number = fields.Char(string="Número de colegiado", required=True)
    image_1920 = fields.Image(string="Foto")

    admission_ids = fields.One2many(
        "hospital.admission", "doctor_id", string="Ingresos atendidos"
    )
    diagnosis_ids = fields.One2many(
        "hospital.diagnosis", "doctor_id", string="Diagnósticos"
    )

    _sql_constraints = [
        (
            "license_number_unique",
            "unique(license_number)",
            "El número de colegiado debe ser único.",
        )
    ]
