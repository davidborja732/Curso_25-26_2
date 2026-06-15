
{
    'name': "Concesionario David Borja Mateo",

    'summary': "Aplicacion de gestion integral de un concesionario de vehiculos.",

    'description': """
Modulo de Odoo para la gestion de un concesionario de coches.
Incluye vehiculos, clientes, empleados, ventas y facturacion.
    """,

    'author': "David Borja Mateo",
    'website': "https://www.iesch.org",

    'category': 'Sales',
    'version': '1.0',

    'depends': ['base'],

    'data': [
        'security/group.xml', 
        'security/ir.model.access.csv',
        
        'views/marca_views.xml',
        'views/vehiculo_views.xml',
        'views/cliente_views.xml',
        'views/empleado_views.xml',
        'views/venta_views.xml',
        'views/menu_views.xml',

        'wizard/actualizar_vehiculo_wizard_views.xml',

        'reports/report_venta.xml',
    ],
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
