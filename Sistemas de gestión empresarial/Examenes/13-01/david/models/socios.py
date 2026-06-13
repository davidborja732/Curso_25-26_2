from odoo import models, fields, api


class Socios(models.Model):
    _name = 'david.socios'
    _description = 'modelo de socios'

    nombre = fields.Char()
    apellidos = fields.Char()
    dni = fields.Char()

