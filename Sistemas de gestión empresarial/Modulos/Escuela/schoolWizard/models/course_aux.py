# -*- coding: utf-8 -*-

from odoo import models, fields, api

class course_aux(models.TransientModel):
    _name = 'school.course_aux'
    _description = 'Describe la clase course temporal'

    name_course_aux = fields.Char(string='Nombre del curso')
    level_course_aux= fields.Selection([
        ('1', 'Primero'),
        ('2', 'Segundo')
    ],string='Nivel del curso')
    date_start_course_aux=fields.Date(string='Fecha de inicio',default='2025-09-10')
    date_end_course_aux = fields.Date(string='Fecha de finalización',default='2026-02-26') 
# estado del asistente
    state_wizard=fields.Selection([
                ('1', 'Curso'),
                ('2', 'Asignaturas'),
                ('3', 'Estudiantes'),
                ('4', 'Confirmar')], default='1')
    
    # seccion de asignaturas
    name_subject_aux = fields.Char(string='Nombre de la asignatura')
    description_subject_aux= fields.Char(string='Descripcion de la asignatura')

    subject_id_aux=fields.One2many('school.subject_aux','course_id_aux',
                                string='Listado de asignaturas')
    
    # seccion de student
    name_student_aux = fields.Char(string='Nombre del alumno')
    surname_student_aux = fields.Char(string='Apellido del alumno')
    birthday_student_aux = fields.Date(string='Fecha del nacimiento',default='2000-05-05')
    gender_student_aux=fields.Selection([
        ('1', 'Femenino'),
        ('2', 'Masculino')
    ],string='Genero del alumno')
    dni_student_aux=fields.Char(string='DNI del alumno')
    student_id_aux=fields.One2many('school.student_aux','course_id_aux',
                                string='Listado de alumnos')

    @api.model
    # metodo accedido desde las vistas
    def action_course_aux(self):
        # devuelvo la primera accion,para devolver un singleton
        action=self.env.ref('school.action_course_aux').read()[0]
        return action

    def next(self):
        if self.state_wizard == '1':
            self.state_wizard = '2'
        elif self.state_wizard == '2':
            self.state_wizard = '3'
        elif self.state_wizard == '3':
            self.state_wizard = '4'
        # devolvemos la vista que queremos actualizar
        return {
            'type': 'ir.actions.act_window',
            'res_model': self._name,
            'res_id': self.id,
            'view_mode': 'form',
            'target': 'new',
        }
    
    def previous(self):
        if self.state_wizard == '2':
            self.state_wizard = '1'
        elif self.state_wizard == '3':
            self.state_wizard = '2'
        elif self.state_wizard == '4':
            self.state_wizard = '3'
        # devolvemos la vista que queremos actualizar
        return {
            'type': 'ir.actions.act_window',
            'res_model': self._name,
            'res_id': self.id,
            'view_mode': 'form',
            'target': 'new',
        }
    #funcion para añadir nuestro estudiantes al modelo temporal
    def add_asignatura(self):
        for asig in self:
            asig.write({'subject_id_aux':[(0,0,
                        {'name_aux':asig.name_subject_aux
                        })]})
        return {
            'type': 'ir.actions.act_window',
            'res_model': self._name,
            'res_id': self.id,
            'view_mode': 'form',
            'target': 'new',
        }
    #funcion para añadir nuestro estudiantes al modelo temporal
    def add_student(self):
        for student in self:
            student.write({'student_id_aux':[(0,0,
                        {'name':student.name_student_aux,
                         'surname':student.surname_student_aux,
                         'birthday':student.birthday_student_aux,
                         'gender':student.gender_student_aux,
                         'dni':student.dni_student_aux
                        })]})
        return {
            'type': 'ir.actions.act_window',
            'res_model': self._name,
            'res_id': self.id,
            'view_mode': 'form',
            'target': 'new',
        }
    
    #transladamos al modelo los datos temporales
    #importante el orden, clase, asignatura, estudiantes a la clase
	
    def create_course(self):
         for curso in self:
            # en la variable micurso guardo el id, la referencia          
              micurso=curso.env['school.course'].create({'name': curso.name_course_aux,
                                                         'level': curso.level_course_aux,
                                                         'date_start': curso.date_start_course_aux,
                                                         'date_end': curso.date_end_course_aux,
                                                         })
               
              #arrays donde guardo los id 
              asignaturas = []
              estudiantes= []
              # creo las asignaturas
              for asig in curso.subject_id_aux:
                  asignatura= asig.env['school.subject'].create({'name': asig.name_aux,
                                                                 'course_id': micurso.id})
                  asignaturas.append(asignatura.id)
              # creo los estudiantes
              for st in curso.student_id_aux:
                  #creo el estudiante
                  student= st.env['school.student'].create({'name':st.name,
                                                            'surname':st.surname,
                                                            'birthday':st.birthday,
                                                            'gender':st.gender,
                                                            'dni':st.dni
                  })
                  estudiantes.append(student.id)
             #Introduzco las notas
             # por defecto coloco un anota de -1
             #recorro los array con los id  

              for asign in asignaturas:
                  for estu in estudiantes:
                      nota=self.env['school.note'].create({'note':'-1',
                                                           'subject_id':asign,
                                                           'student_id':estu})
            
            #Si fuera una relacion Many2many se escribiria asi
			# asignatura.write({'student_ids':[(6,0,students)]})
		    #cerramos la vista y actualizamos la vista de cursos
         return {
            'type': 'ir.actions.act_window_close',
            'res_model': 'school.course',
            'view_mode': 'list,form',
            'target': 'current',
            }
    


