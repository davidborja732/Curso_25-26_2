import modelo.Empleado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<Empleado> employees = Arrays.asList(
                new Empleado("Alice", new ArrayList<>(Arrays.asList("Project1", "Project2")), new ArrayList<>(Arrays.asList("Java", "Spring")), 35, 60000),
                new Empleado("Bob", new ArrayList<>(Arrays.asList("Project3")), new ArrayList<>(Arrays.asList("JavaScript", "React")), 28, 45000),
                new Empleado("Charlie", new ArrayList<>(Arrays.asList("Project1", "Project4")), new ArrayList<>(Arrays.asList("Java", "Angular")), 40, 70000),
                new Empleado("David", new ArrayList<>(Arrays.asList("Project2", "Project3", "Project5")), new ArrayList<>(Arrays.asList("Python", "Django")), 32, 80000),
                new Empleado("Eve", new ArrayList<>(Arrays.asList("Project5")), new ArrayList<>(Arrays.asList("Java", "Spring", "Hibernate")), 25, 50000),
                new Empleado("Frank", new ArrayList<>(Arrays.asList("Project1", "Project3")), new ArrayList<>(Arrays.asList("Java", "Spring")), 35, 90000),
                new Empleado("Grace", new ArrayList<>(Arrays.asList("Project2", "Project4")), new ArrayList<>(Arrays.asList("Java", "Spring")), 35, 80000),
                new Empleado("Heidi", new ArrayList<>(Arrays.asList("Project1", "Project3")), new ArrayList<>(Arrays.asList("Java", "Spring")), 35, 38000)
        );



        //1 - Muestra los empleados mayores de 30 años que tengan un salario superior a 50.000.
        List<Empleado> lista1=employees.stream().filter(empleado -> empleado.getEdad()>30 && empleado.getSalario()>50000).toList();
        lista1.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------");


        //2 - Muestra los empleados agrupados a los empleados según el número de proyectos en los que participan.
        employees.stream().collect(Collectors.groupingBy(empleado -> empleado.getProyectos().size())).forEach((integer, empleados) -> System.out.println(integer+" "+empleados));
        System.out.println("----------------------------------------------------------------------------");

        //3 - Indica el número de empleados que poseen cada habilidad.

        employees.stream().collect(Collectors.groupingBy(empleado -> empleado.getHabilidades().get(0))).forEach((s, empleados) -> System.out.println(s+" "+empleados.size()));
        employees.stream().collect(Collectors.groupingBy(empleado -> empleado.getHabilidades().get(1))).forEach((s, empleados) -> System.out.println(s+" "+empleados.size()));
        System.out.println("----------------------------------------------------------------------------");

        //4 - Ordenar a los empleados por su salario en orden descendente.
        employees.stream().sorted((o1, o2) -> Integer.compare((int) o2.getSalario(),(int) o1.getSalario())).forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------");

        //5 - Encuentra al empleado con el salario más alto.
        employees.stream().sorted((o1, o2) -> Integer.compare((int) o2.getSalario(),(int) o1.getSalario())).limit(1).forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------");


        //6 - Encuentra al empleado con el salario más bajo que tenga más de 30 años.
        employees.stream().filter(empleado -> empleado.getEdad()>30).sorted((o1, o2) -> Integer.compare((int) o1.getSalario(),(int) o2.getSalario())).limit(1).forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------");


        //7 - Encuentra al empleado con el salario más alto que tenga menos de 30 años y que participe en más de un proyecto.
        List<Empleado> lista7=employees.stream().filter(empleado -> empleado.getEdad()<30 && empleado.getProyectos().size()>1).sorted((o1, o2) -> Integer.compare((int) o2.getSalario(),(int) o1.getSalario())).limit(1).toList();
        if (lista7.isEmpty()){
            System.out.println("Empleado menor de 30 con mas de un proyecto y salario mas alto: null");
        }else {
            System.out.println("Empleado menor de 30 con mas de un proyecto y salario mas alto"+lista7.getFirst());
        }
    }

}