#!/bin/bash
i=0
echo "<odoo>"
echo "  <data>"
while read linea
do
    nombre=$(echo $linea| cut -d ',' -f1)
    apellido=$(echo $linea| cut -d ',' -f2)
    nacimiento=$(echo $linea| cut -d ',' -f3)
    genero=$(echo $linea| cut -d ',' -f4)
    estado=$(echo $linea| cut -d ',' -f5)
    echo "      <record id='alumno$i' model='school.student'>"
    echo "          <field name='name'>$nombre</field>"
    echo "          <field name='surname'>$apellido</field>"
    echo "          <field name='birthday'>$nacimiento</field>"
    echo "          <field name='gender'>$genero</field>"
    echo "          <field name='state'>$estado</field>"
    echo "      </record>"
    let i=i+1
done < datosalumnos.csv
echo "  </data>"
echo "</odoo>"