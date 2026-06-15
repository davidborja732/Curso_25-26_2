from odoo import models, fields, api


class socios(models.Model):
    _name = 'david.socios'
    _description = 'Describe los socios'
    _rec_name = 'nombre'

    nombre = fields.Char()
    apellido = fields.Char()
    dni = fields.Char()

    prestamo_ids = fields.One2many('david.prestamos', 'socio_id', string="Prestamos")