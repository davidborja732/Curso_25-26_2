# -*- coding: utf-8 -*-

from odoo import models, fields

class ClienteConcesionario(models.Model):
    # Herencia de clase (Punto 8)
    _inherit = 'res.partner'

    is_cliente_concesionario = fields.Boolean(string='Es Cliente Concesionario', default=False)
    
    # Relación One2many hacia ventas
    venta_ids = fields.One2many('concesionario.venta', 'cliente_id', string='Ventas del Cliente')
