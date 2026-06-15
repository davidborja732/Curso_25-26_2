# -*- coding: utf-8 -*-
{
    'name': "David",

    'summary': "Esto es una biblioteca",

    'description': """
Una biblioteca es un lugar donde hay libros, revistas, tambien se puede ir a estudiar y muchas mas cosas
    """,

    'author': "David",
    'website': "https://www.yourcompany.com",

    # Categories can be used to filter modules in modules listing
    # Check https://github.com/odoo/odoo/blob/15.0/odoo/addons/base/data/ir_module_category_data.xml
    # for the full list
    'category': 'Uncategorized',
    'version': '0.1',

    # any module necessary for this one to work correctly
    'depends': ['base'],

    # always loaded
    'data': [
        # 'security/ir.model.access.csv',
        'security/ir.model.access.csv',
        'views/views.xml',
        'views/templates.xml',
        'views/libros.xml',
        'views/socios.xml',
        'views/prestamos.xml',
    ],
    # only loaded in demonstration mode
    'demo': [
        'demo/demo.xml',
        'demo/socios2.xml',
        'demo/libros2.xml',
    ],
}

