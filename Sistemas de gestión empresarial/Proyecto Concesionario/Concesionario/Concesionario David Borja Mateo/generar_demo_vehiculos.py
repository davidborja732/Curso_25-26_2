import random

def generar_xml_vehiculos(num_registros=1000):
    marcas = ['marca_toyota', 'marca_ford', 'marca_seat']
    modelos = ['Corolla', 'Focus', 'Ibiza', 'Auris', 'Fiesta', 'Leon', 'Mustang', 'Arona', 'Yaris']
    estados = ['disponible', 'reservado', 'vendido']
    
    xml_content = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
    xml_content += "<odoo>\n    <data noupdate=\"1\">\n"
    
    for i in range(1, num_registros + 1):
        matricula = f"{random.randint(1000, 9999)}{random.choice('BCDFGHJKLMNPRSTVWXYZ')}{random.choice('BCDFGHJKLMNPRSTVWXYZ')}{random.choice('BCDFGHJKLMNPRSTVWXYZ')}"
        modelo = random.choice(modelos)
        marca_id = random.choice(marcas)
        precio = round(random.uniform(10000.0, 40000.0), 2)
        estado = random.choice(estados)
        
        xml_content += f"        <record id=\"vehiculo_demo_{i}\" model=\"concesionario.vehiculo\">\n"
        xml_content += f"            <field name=\"name\">{matricula}</field>\n"
        xml_content += f"            <field name=\"modelo\">{modelo}</field>\n"
        xml_content += f"            <field name=\"marca_id\" ref=\"{marca_id}\"/>\n"
        xml_content += f"            <field name=\"precio_base\">{precio}</field>\n"
        xml_content += f"            <field name=\"estado\">{estado}</field>\n"
        xml_content += f"        </record>\n"
        
    xml_content += "    </data>\n</odoo>\n"
    
    with open('demo/demo_vehiculo.xml', 'w', encoding='utf-8') as f:
        f.write(xml_content)

if __name__ == '__main__':
    generar_xml_vehiculos()
    print("demo_vehiculo.xml generado con 1000 registros.")
