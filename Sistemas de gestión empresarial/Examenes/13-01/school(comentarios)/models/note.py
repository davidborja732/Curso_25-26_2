# -*- coding: utf-8 -*-                     # Indica que el archivo usa codificación UTF‑8.

from odoo import models, fields, api         # Importa las clases base y utilidades de Odoo.

class note(models.Model):                    # Define el modelo 'note', heredando de models.Model.
    _name = 'school.note'                    # Nombre técnico del modelo dentro de Odoo.
    _description = 'la clase notas'          # Descripción legible del modelo.

    note = fields.Integer()                  # Campo entero que almacena la nota del alumno.

    subject_id = fields.Many2one(            # Relación muchos-a-uno con la asignatura.
        'school.subject'                     # Modelo relacionado: school.subject.
    )

    student_id = fields.Many2one(            # Relación muchos-a-uno con el estudiante.
        'school.student'                     # Modelo relacionado: school.student.
    )

    course_subject_id = fields.Many2one(     # Campo relacionado que obtiene el curso desde la asignatura.
        related='subject_id.course_id'       # Accede al campo course_id del modelo subject.
    )

    course_student_id = fields.Many2one(     # Campo relacionado que obtiene el curso desde el estudiante.
        related='student_id.course_id'       # Accede al campo course_id del modelo student.
    )

    _sql_constraints = [                     # Lista de restricciones SQL para el modelo.
        (
            'nota_unica_alumno_asig',        # Nombre interno de la restricción.
            'unique(subject_id,student_id)', # Impide duplicar notas para la misma asignatura y alumno.
            'El alumno solo puede tener una nota por asignatura'  # Mensaje de error.
        ),
    ]
