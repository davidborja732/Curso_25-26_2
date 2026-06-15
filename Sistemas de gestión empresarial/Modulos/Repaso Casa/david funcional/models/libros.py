# -*- coding: utf-8 -*-
from odoo import models, fields, api
import secrets

class libros(models.Model):
    _name = 'david.libros'
    _description = 'Describe un libro'
    _rec_name = 'titulo'

    titulo = fields.Char()
    autor = fields.Char()
    isbn = fields.Char()
    numeropaginas = fields.Integer(string = 'Numero de paginas')
    aniolanzamiento = fields.Date(string = 'Año de lanzamiento')
    prestado = fields.Selection([
        ('1', 'Esta prestado'),
        ('2', 'No esta prestado')
    ])

    prestamo_ids = fields.One2many('david.prestamos', 'libro_id', string="Prestamos")

    _sql_constraints= [
        ('isbn_unico', 'unique(isbn)', 'El isbn debe ser unico')
    ]

   # @api.constrains('numeropaginas')
    #def_numero_paginas_a_cero(self):
     #   for record in self:
      #      if record.numeropaginas <= 0:
       #         raise ValidationError("El libro debe de tener mas de 0 paginas")

