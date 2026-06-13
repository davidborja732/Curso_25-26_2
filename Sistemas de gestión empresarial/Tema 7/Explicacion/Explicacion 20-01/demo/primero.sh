#!/bin/bash
i=0
echo "<odoo>"
echo "  <data>"
while read linea
do
    echo "      <record id='asignatura$i' model='school.subject'>"
    echo "          <field name='name'>$linea</field>"
    echo "          <field name='level_course'>1</field>"
    echo "      </record>"
    echo " "
    let i=i+1
done < primero.txt
echo "  </data>"
echo "</odoo>"