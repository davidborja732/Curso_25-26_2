# -*- coding: utf-8 -*-                         # Indica que el archivo usa codificación UTF‑8.

from odoo import models, fields, api             # Importa las clases base y utilidades de Odoo.
from dateutil.relativedelta import relativedelta # Permite calcular diferencias entre fechas.
from datetime import datetime                    # Importa utilidades de fecha y hora (no usado aquí).

import secrets                                   # Módulo para generar valores aleatorios seguros.
import string                                    # Proporciona conjuntos de caracteres (letras, dígitos...).

class student(models.Model):                     # Define el modelo 'student', heredando de models.Model.
    _name = 'school.student'                     # Nombre técnico del modelo en Odoo.
    _description = 'Describe la clase student'   # Descripción legible del modelo.

    name = fields.Char()                         # Nombre del estudiante.
    surname = fields.Char()                      # Apellidos del estudiante.
    birthday = fields.Date()                     # Fecha de nacimiento.
    
    age = fields.Integer(                        # Edad calculada automáticamente.
        compute='_age_calculate',                # Método que calcula la edad.
        store="true"                             # Guarda el valor en la base de datos.
    )
    
    gender = fields.Selection([                  # Género del estudiante.
        ('1', 'Femenino'),
        ('2', 'Masculino')
    ])
    
    note_ids = fields.One2many(                  # Relación uno-a-muchos con notas.
        'school.note',                           # Modelo relacionado.
        'student_id'                             # Campo en school.note que apunta al estudiante.
    )
    
    country_id = fields.Many2one(                # País del estudiante.
        'res.country',                           # Modelo de países.
        default=lambda self: self.env.ref('base.es')  # Por defecto España.
    )
    
    province = fields.Many2one(                  # Provincia del estudiante.
        'res.country.state',                     # Modelo de provincias.
        domain="[('country_id','=',country_id)]" # Filtra provincias según el país seleccionado.
    )
    
    password = fields.Char()                     # Contraseña del estudiante.

    dni = fields.Char()                          # DNI del estudiante.
    
    course_id = fields.Many2one(                 # Curso al que pertenece el estudiante.
        'school.course'
    )
    
    level = fields.Selection(                    # Nivel del curso, obtenido automáticamente.
        related='course_id.level'                # Campo relacionado, no editable.
    )
    
    image = fields.Image(                        # Imagen del estudiante.
        max_width=200,                           # Ancho máximo.
        max_height=200                           # Alto máximo.
    )
    
    state = fields.Selection([                   # Estado del estudiante.
        ('1', 'Prematriculado'),
        ('2', 'Matriculado'),
        ('3', 'Ex-alumno')
    ])

    _sql_constraints = [                         # Restricciones SQL del modelo.
        (
            'dni_unico',                         # Nombre interno de la restricción.
            'unique(dni)',                       # Impide duplicar DNIs.
            'El dni debe ser unico'              # Mensaje de error.
        )
    ]

    def regenerar(self):                         # Método para regenerar la contraseña.
        for student in self:                     # Recorre cada estudiante seleccionado.
            alphabet = (                         # Conjunto de caracteres permitidos.
                string.ascii_letters +           # Letras mayúsculas y minúsculas.
                string.digits +                  # Dígitos del 0 al 9.
                string.punctuation               # Símbolos especiales.
            )
            password = ''.join(                  # Genera una contraseña aleatoria.
                secrets.choice(alphabet)         # Selecciona un carácter seguro.
                for i in range(12)               # Repite 12 veces.
            )
            student.password = password          # Asigna la nueva contraseña.

    @api.depends('birthday')                     # Indica que depende del campo birthday.
    def _age_calculate(self):                    # Método que calcula la edad.
        today = fields.Date.today()              # Obtiene la fecha actual.
        for student in self:                     # Recorre cada estudiante.
            if student.birthday:                 # Si tiene fecha de nacimiento...
                student.age = relativedelta(     # Calcula la diferencia en años.
                    today, student.birthday
                ).years
            else:
                student.age = 0                  # Si no hay fecha, la edad es 0.
