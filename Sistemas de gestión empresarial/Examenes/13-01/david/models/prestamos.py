from odoo import models, fields, api


class Prestamos(models.Model):
    _name = 'david.prestamos'
    _description = 'modelo de prestamos'

    libro_titulo = fields.One2many("david.libros","titulo")
    socio = fields.One2many("david.socios","dni")
    dni = fields.Char()
    fecha_prestamo=fields.Date(
        string="Fecha de prestamo",
        default=fields.Date.context_today,
        )
    fecha_devolucion=fields.Date(
        string="Fecha de devolucion",
        default=fields.Date.context_today,
        )
    empleado=fields.Char()
    prestamo_activo=fields.One2many('david.libros','prestado')