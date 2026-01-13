# -*- coding: utf-8 -*-                         # Indica que el archivo usa codificación UTF‑8.

from odoo import models, fields, api             # Importa las clases base y utilidades de Odoo.

class teacher(models.Model):                     # Define el modelo 'teacher', heredando de models.Model.
    _name = 'school.teacher'                     # Nombre técnico del modelo en Odoo.
    _description = 'Describe la clase teacher'   # Descripción legible del modelo.

    name = fields.Char()                         # Nombre del profesor.
    description = fields.Char()                  # Descripción o información adicional del profesor.

    subject_ids = fields.One2many(               # Relación uno-a-muchos con asignaturas.
        'school.subject',                        # Modelo relacionado.
        'teacher_id'                             # Campo en school.subject que apunta al profesor.
    )

    phone = fields.Char()                        # Teléfono de contacto del profesor.
