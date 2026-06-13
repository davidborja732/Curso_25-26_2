# -*- coding: utf-8 -*-

from odoo import http
import json

class ConcesionarioController(http.Controller):

    # Punto 27: Poder leer datos via web.
    @http.route('/concesionario/api/vehiculos', type='json', auth='public', methods=['GET', 'POST'], cors='*', csrf=False)
    def api_get_vehiculos(self, **args):
        # OBTENER TODOS LOS VEHÍCULOS DISPONIBLES O FILTRAR
        domain = [('estado', '=', 'disponible')]
        if 'marca_id' in args:
            domain.append(('marca_id', '=', int(args['marca_id'])))
            
        vehiculos = http.request.env['concesionario.vehiculo'].sudo().search(domain)
        
        resultado = []
        for v in vehiculos:
            resultado.append({
                'id': v.id,
                'matricula': v.name,
                'modelo': v.modelo,
                'marca': v.marca_id.name,
                'precio_base': v.precio_base,
                'precio_total': v.precio_total,
                'estado': v.estado
            })
            
        return resultado
