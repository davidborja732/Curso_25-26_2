from odoo import models, fields, api

class HospitalRoom(models.Model):
    _name = "hospital.room"
    _description = "Habitación"

    name = fields.Char(string="Número de habitación", required=True)
    active = fields.Boolean(default=True)

    admission_ids = fields.One2many(
        "hospital.admission", "room_id", string="Ingresos"
    )

    current_occupancy = fields.Integer(
        string="Ocupación actual",
        compute="_compute_current_occupancy",
        store=True,  # <- opcional, pero consistente
    )

    is_full = fields.Boolean(
        string="Habitación completa",
        compute="_compute_current_occupancy",
        store=True,  # <- IMPORTANTE: ahora es almacenable y se puede usar en domain
    )

    @api.depends("admission_ids", "admission_ids.discharge_date")
    def _compute_current_occupancy(self):
        for room in self:
            current = room.admission_ids.filtered(lambda a: not a.discharge_date)
            room.current_occupancy = len(current)
            room.is_full = room.current_occupancy >= 2
