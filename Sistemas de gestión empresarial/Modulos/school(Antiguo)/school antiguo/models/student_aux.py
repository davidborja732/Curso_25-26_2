# -*- coding: utf-8 -*-

from odoo import models, fields, api
from dateutil.relativedelta import relativedelta
from datetime import datetime

import secrets
import string


class student_aux(models.TransientModel):
    _name = 'school.student_aux'
    _description = 'Describe la clase student temporal'

    name = fields.Char()
    surname = fields.Char()
    birthday = fields.Date()
    age = fields.Integer(compute='_age_calculate',store="true")    
    gender=fields.Selection([
        ('1', 'Femenino'),
        ('2', 'Masculino')
    ])
    dni=fields.Char()
    course_id_aux = fields.Many2one('school.course_aux')
    