# -*- coding: utf-8 -*-
# from odoo import http


# class David(http.Controller):
#     @http.route('/david/david', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/david/david/objects', auth='public')
#     def list(self, **kw):
#         return http.request.render('david.listing', {
#             'root': '/david/david',
#             'objects': http.request.env['david.david'].search([]),
#         })

#     @http.route('/david/david/objects/<model("david.david"):obj>', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('david.object', {
#             'object': obj
#         })

