# -*- coding: utf-8 -*-

from odoo import models, fields, api
from odoo.exceptions import ValidationError

class Vehiculo(models.Model):
    _name = 'concesionario.vehiculo'
    _description = 'Vehículo del Concesionario'

    name = fields.Char(string='Matrícula', required=True)
    modelo = fields.Char(string='Modelo', required=True)
    
    # Foto para el Kanban (Punto 10)
    image_1920 = fields.Image("Fotografía")
    
    # Relación (Punto 2)
    marca_id = fields.Many2one('concesionario.marca', string='Marca', required=True)
    
    # Campo related (Punto 4)
    pais_origen_marca = fields.Char(related='marca_id.pais_origen', string='País de Marca', readonly=True)
    
    precio_base = fields.Float(string='Precio Base', required=True, default=0.0)
    
    # Campo calculado complejo (Punto 7)
    precio_total = fields.Float(string='Precio Total (con IVA)', compute='_compute_precio_total', store=True)
    
    estado = fields.Selection([
        ('disponible', 'Disponible'),
        ('reservado', 'Reservado'),
        ('vendido', 'Vendido')
    ], string='Estado', default='disponible', required=True)
    
    # Campo reference (Punto 4) - Ejemplo: documento de origen (puede ser factura de compra u otro)
    documento_origen = fields.Reference(selection=[('concesionario.marca', 'Marca')], string="Ref. Documento")

    # Restricción SQL (Punto 5)
    _sql_constraints = [
        ('name_unique', 'unique(name)', 'La matrícula debe ser única en el sistema.')
    ]

    # Campo calculado (Punto 7)
    @api.depends('precio_base')
    def _compute_precio_total(self):
        for record in self:
            record.precio_total = record.precio_base * 1.21  # Añadir 21% de IVA

    # Restricción Python (Punto 6)
    @api.constrains('precio_base')
    def _check_precio_positivo(self):
        for record in self:
            if record.precio_base < 0:
                raise ValidationError("El precio base no puede ser negativo.")
