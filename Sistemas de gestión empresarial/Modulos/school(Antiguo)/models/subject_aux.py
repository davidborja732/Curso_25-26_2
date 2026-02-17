# -*- coding: utf-8 -*-

from odoo import models, fields, api


class subject_aux(models.TransientModel):
    _name = 'school.subject_aux'
    _description = 'Describe la clase subject temporal'
    _rec_name = 'name'

    name = fields.Char()
    description= fields.Char()
    course_id_aux = fields.Many2one('school.course_aux')

