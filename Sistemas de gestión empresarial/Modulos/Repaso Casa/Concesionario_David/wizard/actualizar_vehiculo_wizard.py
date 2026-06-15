

from odoo import models, fields, api

class ActualizarVehiculoWizard(models.TransientModel):
    _name = 'concesionario.actualizar_vehiculo.wizard'
    _description = 'Wizard para actualizar vehículos masivamente'

    state = fields.Selection([
        ('paso1', 'Selección de Vehículos'),
        ('paso2', 'Configuración de Actualización'),
        ('paso3', 'Confirmación')
    ], default='paso1')

    vehiculo_ids = fields.Many2many('concesionario.vehiculo', string='Vehículos a actualizar', relation='concesionario_actualizar_vehiculo_wizard_vehiculo_rel')
    nuevo_estado = fields.Selection([
        ('disponible', 'Disponible'),
        ('reservado', 'Reservado'),
        ('vendido', 'Vendido')
    ], string='Nuevo Estado')
    
    porcentaje_descuento = fields.Float(string='Descuento (%)', default=0.0)

    @api.model
    def default_get(self, fields_list):
        res = super(ActualizarVehiculoWizard, self).default_get(fields_list)
        if self._context.get('active_model') == 'concesionario.vehiculo' and self._context.get('active_ids'):
            res['vehiculo_ids'] = self._context.get('active_ids')
        return res

    def action_next(self):
        if self.state == 'paso1':
            self.state = 'paso2'
        elif self.state == 'paso2':
            self.state = 'paso3'
        return {
            'type': 'ir.actions.act_window',
            'res_model': self._name,
            'res_id': self.id,
            'view_mode': 'form',
            'target': 'new',
        }

    def action_previous(self):
        if self.state == 'paso2':
            self.state = 'paso1'
        elif self.state == 'paso3':
            self.state = 'paso2'
        return {
            'type': 'ir.actions.act_window',
            'res_model': self._name,
            'res_id': self.id,
            'view_mode': 'form',
            'target': 'new',
        }

    def action_apply(self):
        for vehiculo in self.vehiculo_ids:
            if self.nuevo_estado:
                vehiculo.estado = self.nuevo_estado
            if self.porcentaje_descuento > 0:
                vehiculo.precio_base = vehiculo.precio_base * (1 - (self.porcentaje_descuento / 100))
        return {'type': 'ir.actions.act_window_close'}
