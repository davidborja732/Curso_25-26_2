from odoo import models, fields, api


class socios(models.Model):
    _name = 'esther.socios'
    _description = 'Describe los socios'

    nombre = fields.Char()
    apellido = fields.Char()
    dni = fields.Char()

    prestamos_id= fields.Many2one('esther.prestamos')