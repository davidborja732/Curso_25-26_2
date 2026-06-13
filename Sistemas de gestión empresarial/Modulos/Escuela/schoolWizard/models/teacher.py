# -*- coding: utf-8 -*-

from odoo import models, fields, api


class teacher(models.Model):
    _inherit = 'hr.employee'
    _description = 'Hereda de empleados'

    subject_ids = fields.One2many('school.subject','teacher_id')




