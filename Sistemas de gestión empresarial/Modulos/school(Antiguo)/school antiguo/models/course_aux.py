# -*- coding: utf-8 -*-

from odoo import models, fields, api


class course_aux(models.TransientModel):
    _name_course_aux = 'school.course_aux'
    _description_course_aux = 'Describe la clase cours temporal'

    name_course_aux = fields.Char()
    level_course_aux= fields.Selection([
        ('1', 'Primero'),
        ('2', 'Segundo'),
        ('3', 'Tercero')
    ])    
    date_start_course_aux=fields.Date()
    date_end_course_aux=fields.Date()
    
    state_wizard= fields.Selection([
        ('1', 'Curso'),
        ('2', 'Asignaturas'),
        ('3', 'Estudiantes'),
        ('4', 'Confirmar')
    ], default='1')
    name_subject_aux= fields.Char()
    description_subject_aux= fields.Char()
    subject_id_aux= fields.One2many('school.subject_aux', 'course_id_aux')
    
    #seccion estudiante
    name_student_aux = fields.Char()
    surname_student_aux = fields.Char()
    birthday_student_aux = fields.Date()
    age_student_aux = fields.Integer(compute='_age_calculate',store="true")    
    gender_student_aux=fields.Selection([
        ('1', 'Femenino'),
        ('2', 'Masculino')
    ])
    dni_student_aux=fields.Char()
    student_id_aux= fields.One2many('school.student_aux', 'course_id_aux')
    
    # metodo accedido desde las vistas
    @api.model
    def action_course_aux(self):
        # devuelve la accion para devolver un singleton
        action=self.env.ref('school.action_course_aux').read()[0]
        return action
    def next(self):
        if self.state == '1':
            self.state = '2'
        elif self.state == '2':
            self.state = '3'
        elif self.state == '3':
            self.state = '4'
        return {
            'type': 'ir.actions.act_window',
            'res_model': self._name,
            'res_id': self.id,
            'view_mode': 'form',
            'target': 'new',
            }           
    def previous(self):
        if self.state == '2':
            self.state = '1'
        elif self.state == '3':
            self.state = '2'
        elif self.state == '4':
            self.state = '3'
        return {
            'type': 'ir.actions.act_window',
            'res_model': self._name,
            'res_id': self.id,
            'view_mode': 'form',
            'target': 'new',
        }



