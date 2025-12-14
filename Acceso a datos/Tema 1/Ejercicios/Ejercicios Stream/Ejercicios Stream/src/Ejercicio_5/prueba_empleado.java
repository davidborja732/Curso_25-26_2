package Ejercicio_5;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class prueba_empleado {
    static void main() {
        List<Empleado> empleados = List.of(
                new Empleado("Bea", "Logística"),
                new Empleado("Lucía", "IT"),
                new Empleado("Carlos", "Marketing"),
                new Empleado("Iván", "Diseño"),
                new Empleado("Ana", "Ventas"),
                new Empleado("Raúl", "Legal"),
                new Empleado("Pedro", "IT"),
                new Empleado("Tomás", "Recursos Humanos"),
                new Empleado("Luis", "Ventas"),
                new Empleado("Clara", "Calidad"),
                new Empleado("Jorge", "IT"),
                new Empleado("Sofía", "IT"),
                new Empleado("María", "Ventas"),
                new Empleado("Elena", "Marketing"),
                new Empleado("Nuria", "Finanzas")
        );
        // Agrupar lista por departamento
        Map<String, List<Empleado>> agrupados = empleados.stream()
                .collect(Collectors.groupingBy(Empleado::getDepartamento));
        System.out.println("Empleados agrupados por departamento:");
        System.out.println(agrupados);
        /*agrupados.forEach((departamento, lista) -> {
            System.out.println(departamento +"= "+lista);
        });*/
        // Contar Empleados por departamento
        Map<String, Long> conteoPorDepartamento = empleados.stream()
                .collect(Collectors.groupingBy(Empleado::getDepartamento, Collectors.counting()));
        System.out.println("Cantidad de empleados por departamento:");
        conteoPorDepartamento.forEach((departamento, cantidad) ->
                System.out.println(departamento+" Tiene "+cantidad+" empleado(s)")
        );

        // Mostrar empleados de un departamento
        Scanner scanner=new Scanner(System.in);
        System.out.println("De que departamento quieres ver los empleados ");
        String departamento= scanner.nextLine().toLowerCase();
        if (empleados.stream().anyMatch(empleado -> empleado.getDepartamento().toLowerCase().equals(departamento))){
            empleados.stream().filter(empleado -> empleado.getDepartamento().toLowerCase().equals(departamento)).forEach(System.out::println);
        }else {
            System.out.println("Departamento vacio o desconocido");
        }
        // Mostrar departamento de empleado
        System.out.println("De que empleado quieres saber su departamento ");
        String empleadobuscar= scanner.nextLine();
        if (empleados.stream().anyMatch(empleado -> empleado.getNombre().toLowerCase().equals(empleadobuscar))) {
            empleados.stream().filter(empleado -> empleado.getNombre().toLowerCase().equals(empleadobuscar)).forEach(
                    empleado -> System.out.println("Su departamento es " + empleado.getDepartamento())
            );
        }else {
            System.out.println("Empleado desconocido o sin departamento");
        }
    }
}

