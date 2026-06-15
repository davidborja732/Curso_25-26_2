# -*- coding: utf-8 -*-
import random
import json
from odoo import http


class SchoolController(http.Controller):
    @http.route('/school/random', type='http', auth='public', methods=['GET'], csrf=False, cors='*')
    def random_number(self, **kw):
        """Return a random integer between 1 and 100 as plain text."""
        number = random.randint(1, 100)
        return str(number)

    @http.route('/socio', type='json', auth='public', methods=['GET'], csrf=False, cors='*')
    def socio(self, **kw):
        """Accepts a JSON string via the 'data' query parameter and returns the parsed fields.
        Expected format: {"dni": "...", "nombre": "...", "apellidos": "..."}
        """
        data_str = kw.get('data')
        if not data_str:
            return {"error": "Missing 'data' parameter"}
        try:
            payload = json.loads(data_str)
        except Exception:
            return {"error": "Invalid JSON"}
        # Simple validation – ensure required keys exist
        for key in ("dni", "nombre", "apellidos"):
            if key not in payload:
                return {"error": f"Missing key: {key}"}
        # Echo back the received data
        return {
            "dni": payload["dni"],
            "nombre": payload["nombre"],
            "apellidos": payload["apellidos"]
        }
    @http.route('/socios', type='http', auth='public', methods=['GET'], csrf=False, cors='*')
    def socios(self, **kw):
        """Return all socios from the `school.socio` model as a JSON string.
        If the model does not exist or an error occurs, return an error JSON.
        """
        try:
            Socio = http.request.env['school.socio']
            records = Socio.search([])
            data = [{
                'dni': rec.dni,
                'nombre': rec.nombre,
                'apellidos': rec.apellidos,
            } for rec in records]
            import json as _json
            return _json.dumps(data)
        except Exception as e:
            import json as _json
            return _json.dumps({'error': str(e)})



#     @http.route('/school/school', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/school/school/objects', auth='public')
#     def list(self, **kw):
#         return http.request.render('school.listing', {
#             'root': '/school/school',
#             'objects': http.request.env['school.school'].search([]),
#         })

#     @http.route('/school/school/objects/<model("school.school"):obj>', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('school.object', {
#             'object': obj
#         })


