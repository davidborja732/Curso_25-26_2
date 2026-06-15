

from odoo import models, fields

class ClienteConcesionario(models.Model):
    _inherit = 'res.partner'

    is_cliente_concesionario = fields.Boolean(string='Es Cliente Concesionario', default=False)
    
    venta_ids = fields.One2many('concesionario.venta', 'cliente_id', string='Ventas del Cliente')
