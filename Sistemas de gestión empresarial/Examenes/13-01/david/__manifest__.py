# -*- coding: utf-8 -*-
{
    'name': "david",

    'summary': "Examen David Borja Mateo",

    'description': """
Examen David Borja Mateo sobre un modulo de una biblioteca el dia 13/01
    """,

    'author': "David Borja Mateo",
    'website': "https://www.david.com",


    # Categories can be used to filter modules in modules listing
    # Check https://github.com/odoo/odoo/blob/15.0/odoo/addons/base/data/ir_module_category_data.xml
    # for the full list
    'category': 'Uncategorized',
    'version': '0.1',

    # any module necessary for this one to work correctly
    'depends': ['base'],

    # always loaded
    'data': [
        'security/ir.model.access.csv',
        'views/views.xml',
        'views/templates.xml',
        'views/socios.xml', 
        'views/libros.xml', 
        'views/prestamos.xml', 
    ],
    # only loaded in demonstration mode
    'demo': [
        'demo/demo.xml',
    ],
    "images": ["static/description/icon.png"],
}

