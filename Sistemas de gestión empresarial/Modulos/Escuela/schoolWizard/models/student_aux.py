# -*- coding: utf-8 -*-

from odoo import models, fields, api
from dateutil.relativedelta import relativedelta
from datetime import datetime

import secrets
import string


class student_aux(models.TransientModel):

    _name = 'school.student_aux'
    _description = 'Describe la clase student'

    name = fields.Char(string='Nombre del alumno')
    surname = fields.Char(string='Apellido del alumno')
    birthday = fields.Date(string='Cumpleaños del alumno')
    gender=fields.Selection([
        ('1', 'Femenino'),
        ('2', 'Masculino')
    ],string='Genero del alumno')
    dni=fields.Char(string='DNI del alumno')
    course_id_aux=fields.Many2one('school.course_aux')

   










