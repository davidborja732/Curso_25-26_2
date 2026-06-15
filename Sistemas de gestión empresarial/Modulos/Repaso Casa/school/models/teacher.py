# -*- coding: utf-8 -*-

from odoo import models, fields, api


class teacher(models.Model):
    _name = 'school.teacher'
    _description = 'Describe la clase teacher'

    name = fields.Char()
    description= fields.Char()
    subject_ids = fields.One2many('school.subject','teacher_id')
    phone=fields.Char()



