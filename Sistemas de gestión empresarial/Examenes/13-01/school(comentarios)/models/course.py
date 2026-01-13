# -*- coding: utf-8 -*-            # Indica que el archivo usa codificación UTF-8.

from odoo import models, fields, api   # Importa las clases base y utilidades de Odoo.

class course(models.Model):            # Define un nuevo modelo llamado 'course' que hereda de models.Model.
    _name = 'school.course'            # Nombre técnico del modelo dentro de Odoo.
    _description = 'Describe la clase course'   # Descripción legible del modelo.

    name = fields.Char()               # Campo de texto corto para el nombre del curso.
    
    level = fields.Selection([         # Campo selección para indicar el nivel del curso.
        ('1', 'Primero'),              # Opción 1: Primero.
        ('2', 'Segundo'),              # Opción 2: Segundo.
        ('3', 'Tercero')               # Opción 3: Tercero.
    ])
    
    subject_id = fields.One2many(      # Relación uno-a-muchos con asignaturas.
        'school.subject',              # Modelo relacionado.
        'course_id'                    # Campo en el modelo relacionado que apunta a este curso.
    )
    
    student_ids = fields.One2many(     # Relación uno-a-muchos con estudiantes.
        'school.student',              # Modelo relacionado.
        'course_id'                    # Campo en el modelo relacionado que apunta a este curso.
    )
    
    date_start = fields.Date()         # Fecha de inicio del curso.
    date_end = fields.Date()           # Fecha de fin del curso.
