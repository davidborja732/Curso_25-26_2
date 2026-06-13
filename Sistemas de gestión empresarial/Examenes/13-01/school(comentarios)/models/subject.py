# -*- coding: utf-8 -*-                         # Indica que el archivo usa codificación UTF‑8.

from odoo import models, fields, api             # Importa las clases base y utilidades de Odoo.

class subject(models.Model):                     # Define el modelo 'subject', heredando de models.Model.
    _name = 'school.subject'                     # Nombre técnico del modelo en Odoo.
    _description = 'Describe la clase subject'   # Descripción legible del modelo.

    name = fields.Char()                         # Nombre de la asignatura.
    description = fields.Char()                  # Descripción breve de la asignatura.

    note_ids = fields.One2many(                  # Relación uno-a-muchos con notas.
        'school.note',                           # Modelo relacionado.
        'subject_id'                             # Campo en school.note que apunta a esta asignatura.
    )

    teacher_id = fields.Many2one(                # Profesor asignado a la asignatura.
        'school.teacher'                         # Modelo relacionado.
    )

    course_id = fields.Many2one(                 # Curso al que pertenece la asignatura.
        'school.course'                          # Modelo relacionado.
    )

    level_course = fields.Selection(             # Nivel del curso, obtenido automáticamente.
        related='course_id.level'                # Campo relacionado, no editable.
    )
