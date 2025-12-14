package Ej_9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ej_9_Prueba {
    static void main() {
        List<Alumno> listaAlumnos = new ArrayList<>();
        listaAlumnos.add(new Alumno(1, "1717213183", "Javier", "Molina Cano", "Java 8", 7, 28));
        listaAlumnos.add(new Alumno(2, "1717456218", "Ana", "Gómez Álvarez", "Java 8", 10, 33));
        listaAlumnos.add(new Alumno(3, "1717328901", "Pedro", "Marín López", "Java 8", 8.6, 15));
        listaAlumnos.add(new Alumno(4, "1717567128", "Emilio", "Duque Gutiérrez", "Java 8", 10, 13));
        listaAlumnos.add(new Alumno(5, "1717902145", "Alberto", "Sáenz Hurtado", "Java 8", 9.5, 15));
        listaAlumnos.add(new Alumno(6, "1717678456", "Germán", "López Fernández", "Java 8", 8, 34));
        listaAlumnos.add(new Alumno(7, "1102156732", "Oscar", "Murillo González", "Java 8", 10, 32));
        listaAlumnos.add(new Alumno(8, "1103421907", "Antonio Jesús", "Palacio Martínez", "PHP", 9.5, 17));
        listaAlumnos.add(new Alumno(9, "1717297015", "César", "González Martínez", "Java 8", 8, 26));
        listaAlumnos.add(new Alumno(10, "1717912056", "Gloria", "González Castaño", "PHP", 10, 28));
        listaAlumnos.add(new Alumno(11, "1717912058", "Jorge", "Ruiz Ruiz", "Python", 8, 22));
        listaAlumnos.add(new Alumno(12, "1717912985", "Ignacio", "Duque García", "Java Script", 9.4, 32));
        listaAlumnos.add(new Alumno(13, "1717913851", "Julio", "González Castaño", "C Sharp", 10, 22));
        listaAlumnos.add(new Alumno(14, "1717986531", "Gloria", "Rodas Carretero", "Ruby", 7, 18));
        listaAlumnos.add(new Alumno(15, "1717975232", "Jaime", "Jiménez Gómez", "Java Script", 10, 18));
        System.out.println(1);
        listaAlumnos.forEach(alumno -> System.out.println(alumno.toString()));
        System.out.println(2);
        listaAlumnos.stream().filter(alumno -> alumno.getApellidos().startsWith("L") || alumno.getApellidos().startsWith("G")).forEach(System.out::println);
        System.out.println(3);
        System.out.println("El numero de alumnos es " + listaAlumnos.size());
        System.out.println(4);
        listaAlumnos.stream().filter(alumno -> alumno.getNombreCurso().equals("PHP") && alumno.getNota() > 9).forEach(System.out::println);
        System.out.println(5);
        listaAlumnos.stream().limit(2).forEach(alumno -> System.out.println(alumno.toString()));
        System.out.println(6);
        listaAlumnos.stream().min(Comparator.comparing(Alumno::getEdad)).ifPresent(System.out::println);
        System.out.println(7);
        listaAlumnos.stream().max(Comparator.comparing(Alumno::getEdad)).ifPresent(System.out::println);
        System.out.println(8);
        listaAlumnos.stream().findFirst().ifPresent(System.out::println);
        System.out.println(9);
        listaAlumnos.stream().filter(alumno -> alumno.getNombreCurso().toLowerCase().contains("a")).forEach(System.out::println);
        System.out.println(10);
        listaAlumnos.stream().filter(alumno -> alumno.getNombre().length()>10).forEach(System.out::println);
        System.out.println(11);
        listaAlumnos.stream().filter(alumno -> alumno.getNombreCurso().startsWith("P") && alumno.getNombre().length() <= 6).forEach(System.out::println);
        System.out.println(12);
        List<Alumno> listanueva=listaAlumnos.stream().filter(alumno -> alumno.getNombreCurso().startsWith("P") && alumno.getNombre().length() <= 6).toList();
        listanueva.forEach(System.out::println);
    }
}
