# -*- coding: utf-8 -*-
{
    'name': "Concesionario David Borja Mateo",

    'summary': "Aplicación de gestión integral de un concesionario de vehículos.",

    'description': """
Módulo de Odoo para la gestión de un concesionario de coches.
Incluye vehículos, clientes, empleados, ventas y facturación.
    """,

    'author': "David Borja Mateo",
    'website': "https://www.iesch.org",

    'category': 'Sales',
    'version': '1.0',

    # any module necessary for this one to work correctly
    'depends': ['base'],

    # always loaded
    'data': [
        'security/group.xml', 
        'security/ir.model.access.csv',
        
        # Vistas a crear
        'views/marca_views.xml',
        'views/vehiculo_views.xml',
        'views/cliente_views.xml',
        'views/empleado_views.xml',
        'views/venta_views.xml',
        'views/menu_views.xml',

        # Wizard
        'wizard/actualizar_vehiculo_wizard_views.xml',

        # Reporte
        'reports/report_venta.xml',
    ],
    # only loaded in demonstration mode
    'demo': [
        'demo/demo_marca.xml',
        'demo/demo_vehiculo.xml',
        'demo/demo_cliente.xml',
        'demo/demo_venta.xml',
    ],
    'installable': True,
    'application': True,
    'auto_install': False,
}
