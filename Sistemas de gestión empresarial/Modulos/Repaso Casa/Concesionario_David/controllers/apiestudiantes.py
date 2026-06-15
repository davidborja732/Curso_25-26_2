from odoo import http
from odoo.http import request
import json

class apiRest (http.Controller):
    @http.route('/gestion/<model>', auth='none', cors=False, csrf=False,
            methods=["POST","PUT"], type='http')
    
    
    def apiPost(self, **args):
        modelo=args['model']

        dicDatos=json.loads(args['data'])
        if dicDatos["dni"]:
            buscado = [('dni','=',dicDatos["dni"])]
        else:
            return "{'estado':'ESTUDIANTE NO INDICADO'}"
        if (http.request.httprequest.method == 'POST'):
            record =request.env[modelo].sudo().create(dicDatos)
            return http.Response(
                json.dumps(
                    record.read(),
                    default=str),
                    status=200,
                    mimetype='application/json'
                )
        if (http.request.httprequest.method == 'PUT'):
            record =request.env[modelo].sudo().search(buscado)
            if record and record[0]:
                record[0].write(dicDatos)
                return http.Response(
                json.dumps(
                    record.read(),
                    default=str),
                    status=200,
                    mimetype='application/json'
                )
            return "Registro no encontrado"


        return http.request.env['ir.http'].session_info()   

    @http.route('/gestion/<model>', auth='none', cors=False, csrf=False,
            methods=["GET","DELETE"], type='http')
    
    def apiGet(self, **args):
        modelo=args['model']
        dicDatos=json.loads(args['data'])
        if dicDatos["dni"]:
            buscado = [('dni','=',dicDatos["dni"])]
        else:
            return "{'estado':'ESTUDIANTE NO INDICADO'}"
        if (http.request.httprequest.method == 'GET'):
            record =request.env[modelo].sudo().search(buscado)
            if record and record[0]:
                return http.Response(
                json.dumps(
                    record.read(),
                    default=str),
                    status=200,
                    mimetype='application/json'
                )
            return "Registro no encontrado"

        if (http.request.httprequest.method == 'DELETE'):
            record =request.env[modelo].sudo().search(buscado)
            
            if record and record[0]:
                aux =http.Response(
                    json.dumps(
                    record.read(),
                    default=str),
                    status=200,
                    mimetype='application/json'
            )
                
                record[0].unlink()
                return aux
            return "Registro no encontrado"


        return http.request.env['ir.http'].session_info()   

