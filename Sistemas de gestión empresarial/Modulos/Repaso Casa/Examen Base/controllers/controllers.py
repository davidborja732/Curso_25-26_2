# -*- coding: utf-8 -*-
# from odoo import http


# class Esther(http.Controller):
#     @http.route('/esther/esther', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/esther/esther/objects', auth='public')
#     def list(self, **kw):
#         return http.request.render('esther.listing', {
#             'root': '/esther/esther',
#             'objects': http.request.env['esther.esther'].search([]),
#         })

#     @http.route('/esther/esther/objects/<model("esther.esther"):obj>', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('esther.object', {
#             'object': obj
#         })

