from odoo import models, fields, api
from odoo.exceptions import ValidationError


class Libros(models.Model):
    _name = 'david.libros'
    _description = 'modelo de libros'

    titulo = fields.Char()
    autor = fields.Char()
    isbn = fields.Char()
    paginas = fields.Integer()
    lanzamiento = fields.Integer()
    prestado = fields.Boolean(string="noprestado", default=False)
    _sql_constraints = [
        ('isbn_unica', 
        'unique(isbn)',
        'El isbn no se puede repetir' ),
    ] 
    @api.constrains('paginas')
    def _check_age(self):
        for record in self:
            if record.paginas <= 0:
                raise ValidationError("El libro debe tener mas de 0 paginas")