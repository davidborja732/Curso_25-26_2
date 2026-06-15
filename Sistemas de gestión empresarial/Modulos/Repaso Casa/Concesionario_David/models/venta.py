

from odoo import models, fields, api

class Venta(models.Model):
    _name = 'concesionario.venta'
    _description = 'Venta de Vehículo'

    name = fields.Char(string='Referencia de Venta', required=True, copy=False, readonly=True, default='Nueva Venta')
    fecha_venta = fields.Date(string='Fecha de Venta', default=fields.Date.context_today)
    
    cliente_id = fields.Many2one('res.partner', string='Cliente', domain="[('is_cliente_concesionario', '=', True)]", required=True)
    empleado_id = fields.Many2one('res.partner', string='Vendedor', domain="[('is_empleado_concesionario', '=', True), ('rol_empleado', '=', 'vendedor')]", required=True)
    
    lineas_ids = fields.One2many('concesionario.venta.linea', 'venta_id', string='Vehículos Vendidos')
    
    state = fields.Selection([
        ('borrador', 'Borrador'),
        ('confirmado', 'Confirmado'),
        ('cancelado', 'Cancelado')
    ], string='Estado', default='borrador', tracking=True)
    
    total_venta = fields.Float(string='Total de la Venta', compute='_compute_total_venta', store=True)
    
    @api.depends('lineas_ids.precio_linea')
    def _compute_total_venta(self):
        for record in self:
            record.total_venta = sum(linea.precio_linea for linea in record.lineas_ids)

    def action_confirmar(self):
        for record in self:
            record.state = 'confirmado'
            for linea in record.lineas_ids:
                if linea.vehiculo_id:
                    linea.vehiculo_id.estado = 'vendido'

    def action_cancelar(self):
        for record in self:
            record.state = 'cancelado'
            for linea in record.lineas_ids:
                if linea.vehiculo_id:
                    linea.vehiculo_id.estado = 'disponible'

class VentaLinea(models.Model):
    _name = 'concesionario.venta.linea'
    _description = 'Línea de Venta'

    venta_id = fields.Many2one('concesionario.venta', string='Venta')
    vehiculo_id = fields.Many2one('concesionario.vehiculo', string='Vehículo', domain="[('estado', '=', 'disponible')]", required=True)
    
    precio_linea = fields.Float(string='Precio', related='vehiculo_id.precio_total', readonly=True)
