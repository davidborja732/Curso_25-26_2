from odoo import models, fields, api
from odoo.exceptions import ValidationError


class prestamos(models.Model):
    _name = 'david.prestamos'
    _description = 'Describe los prestamos'

    libro_id = fields.Many2one('david.libros', string="Libro")
    socio_id = fields.Many2one('david.socios', string="Socio")
    fecha_prestamo = fields.Date(string="Fecha de préstamo", required=True, default=fields.Date.context_today)
    fecha_devolucion = fields.Date(string="Fecha de devolución", required=True)
    activo = fields.Selection([
        ('1', 'Activo'),
        ('2', 'No activo')
    ])
    empleado = fields.Char(string="Empleado")

    @api.constrains('fecha_prestamo', 'fecha_devolucion', 'libro_id')
    def _check_fechas_prestamo(self):
        for record in self:
            if record.fecha_prestamo and record.fecha_prestamo < fields.Date.today():
                raise ValidationError("La fecha de préstamo no puede ser menor a la actual.")
                
            if record.libro_id and record.libro_id.prestado == '1':
                raise ValidationError(f"El libro '{record.libro_id.titulo}' ya figura con estado 'Está prestado'.")
                
            if record.fecha_devolucion and record.fecha_devolucion < fields.Date.today():
                raise ValidationError("La fecha de devolución no puede ser menor a la actual.")
            
            if record.fecha_prestamo and record.fecha_devolucion and record.fecha_prestamo > record.fecha_devolucion:
                raise ValidationError("La fecha de devolución no puede ser anterior a la fecha de préstamo.")
                
            if record.libro_id and record.fecha_prestamo and record.fecha_devolucion:
                # Comprobar solapamiento de fechas para el mismo libro
                domain = [
                    ('libro_id', '=', record.libro_id.id),
                    ('id', '!=', record.id),
                    ('fecha_prestamo', '<=', record.fecha_devolucion),
                    ('fecha_devolucion', '>=', record.fecha_prestamo),
                ]
                overlapping_loans = self.search(domain)
                if overlapping_loans:
                    raise ValidationError("Este libro ya está reservado para esas fechas.")
