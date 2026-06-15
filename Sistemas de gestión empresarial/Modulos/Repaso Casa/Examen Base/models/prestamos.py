from odoo import models, fields, api


class prestamos(models.Model):
    _name = 'esther.prestamos'
    _description = 'Describe los prestamos'

    libro = fields.Char()
    socio = fields.Char()
    fecha_prestamo = fields.Date()
    fecha_devolucion = fields.Date()
    activo = fields.Selection([
        ('1', 'Activo'),
        ('2', 'No activo')
    ])
    empleado = fields.Char()

    libros_id = fields.One2many('esther.libros', 'prestamos_id')
    socios_id = fields.One2many('esther.socios', 'prestamos_id')
