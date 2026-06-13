# -*- coding: utf-8 -*-

# pyrefly: ignore [missing-import]
from odoo import models, fields, api

class Marca(models.Model):
    _name = 'concesionario.marca'
    _description = 'Marca de Vehículos'

    name = fields.Char(string='Nombre de la Marca', required=True)
    pais_origen = fields.Char(string='País de Origen')
    vehiculos_ids = fields.One2many('concesionario.vehiculo', 'marca_id', string='Vehículos')
