# -*- coding: utf-8 -*-                     # Indica que el archivo usa codificación UTF‑8.

{
    'name': "school",                       # Nombre del módulo tal como aparecerá en Odoo.

    'summary': "Aplicación gestión de un colegio",  
    # Resumen corto que aparece en la vista de módulos.

    'description': """
Aplicación gestión de un colegio
    """,                                    # Descripción larga del módulo.

    'author': "Pascual Pérez",              # Autor del módulo.
    'website': "www.iesch.org",             # Sitio web del autor o del proyecto.

    # Categoría del módulo para clasificarlo en la lista de apps.
    'category': 'Uncategorized',

    'version': '0.1',                       # Versión del módulo.

    # Módulos de los que depende este para funcionar.
    'depends': ['base'],                    # 'base' es obligatorio para casi cualquier módulo.

    # Archivos que se cargan siempre al instalar el módulo.
    'data': [
        'security/group.xml',               # Definición de grupos de seguridad.
        'security/ir.model.access.csv',     # Permisos de acceso a modelos.
        'views/views.xml',                  # Vista general generada por scaffolding.
        'views/templates.xml',              # Plantillas QWeb (si se usan).
        'views/student.xml',                # Vista del modelo student.
        'views/subject.xml',                # Vista del modelo subject.
        'views/teacher.xml',                # Vista del modelo teacher.
        'views/course.xml',                 # Vista del modelo course.
        'views/note.xml',                   # Vista del modelo note.
    ],

    # Archivos que solo se cargan en modo demo.
    'demo': [
        'demo/demo.xml',                    # Datos de demostración generales.
        'demo/cursos.xml',                  # Datos demo específicos de cursos.
    ],
}
