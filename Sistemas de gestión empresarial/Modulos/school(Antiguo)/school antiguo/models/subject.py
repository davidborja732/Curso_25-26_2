# -*- coding: utf-8 -*-

from odoo import models, fields, api


class subject(models.Model):
    _name = 'school.subject'
    _description = 'Describe la clase subject'
    _rec_name = 'name'

    name = fields.Char()
    description= fields.Char()
    note_ids = fields.One2many('school.note', 'subject_id')
    teacher_id = fields.Many2one('hr.employee')
    course_id = fields.Many2one('school.course')  
    #level_course= fields.Selection(related='course_id.level')
    level_course= fields.Selection([
        ('1', 'Primero'),
        ('2', 'Segundo'),
        ('3', 'Tercero')
    ])  

