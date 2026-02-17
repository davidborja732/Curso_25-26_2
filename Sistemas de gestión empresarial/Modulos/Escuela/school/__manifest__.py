# -*- coding: utf-8 -*-
{
    'name': "school",

    'summary': "Aplicación gestión de un colegio",

    'description': """
Aplicación gestión de un colegio
    """,

    'author': "Pascual Pérez",
    'website': "www.iesch.org",

    # Categories can be used to filter modules in modules listing
    # Check https://github.com/odoo/odoo/blob/15.0/odoo/addons/base/data/ir_module_category_data.xml
    # for the full list
    'category': 'Uncategorized',
    'version': '0.1',

    # any module necessary for this one to work correctly
    'depends': ['base'],

    # always loaded
    'data': [
        'security/group.xml',
        'security/ir.model.access.csv',
        'views/views.xml',
        'views/templates.xml',
        'views/student.xml',  
        'views/subject.xml',   
        'views/teacher.xml', 
        'views/course.xml',  
        'views/note.xml',            
    ],
    # only loaded in demonstration mode
    'demo': [
        'demo/demo.xml',
        'demo/cursos.xml',
    ],
}

