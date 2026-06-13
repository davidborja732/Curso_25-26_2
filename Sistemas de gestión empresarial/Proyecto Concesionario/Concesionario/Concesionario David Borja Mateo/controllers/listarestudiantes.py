# -*- coding: utf-8 -*-
from odoo import http
import json


class lista_estudiantes(http.Controller):
     @http.route('/school/school/', auth='public', cors='*', type='http')
     def index(self, **kw):
         return "Hello, world"

     @http.route('/school/school/<modelo>', auth='public', cors='*', type='http')
     def devolverEstudiantes(self, **kw):
         estudiantes=http.request.env['school.student'].sudo().search([])
         lista=[]
         for estu in estudiantes:
             lista.append([estu.name,
                          estu.surname,
                          estu.dni])
         json_result=json.dumps(lista, default=str)
         return json_result
        
        
        
         return http.request.render('school.listing', {
             'root': '/school/school',
             'objects': http.request.env['school.school'].search([]),
         })

#     @http.route('/school/school/objects/<model("school.school"):obj>', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('school.object', {
#             'object': obj
#         })

