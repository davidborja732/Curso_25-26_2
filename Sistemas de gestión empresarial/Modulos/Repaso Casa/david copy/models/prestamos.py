from odoo import models, fields, api


class prestamos(models.Model):
    _name = 'david.prestamos'
    _description = 'Describe los prestamos'

    libro_id = fields.Many2one('david.libros', string="Libro")
    socio_id = fields.Many2one('david.socios', string="Socio")
    fecha_prestamo = fields.Date(string="Fecha de préstamo")
    fecha_devolucion = fields.Date(string="Fecha de devolución")
    activo = fields.Selection([
        ('1', 'Activo'),
        ('2', 'No activo')
    ])
    empleado = fields.Char(string="Empleado")
