

from odoo import models, fields, api

class EmpleadoConcesionario(models.Model):
    _inherit = 'res.partner'

    is_empleado_concesionario = fields.Boolean(string='Es Empleado', default=False)
    rol_empleado = fields.Selection([
        ('vendedor', 'Vendedor'),
        ('mecanico', 'Mecánico'),
        ('director', 'Director')
    ], string='Rol en Concesionario')
    
    ventas_empleado_ids = fields.One2many('concesionario.venta', 'empleado_id', string='Ventas Realizadas')
