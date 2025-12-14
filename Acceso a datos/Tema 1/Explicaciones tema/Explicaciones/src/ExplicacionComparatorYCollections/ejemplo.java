package ExplicacionComparatorYCollections;

import ExplicacionComparatorYCollections.modelo.Teacher;
import ExplicacionComparatorYCollections.modelo.TeacherNameComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ejemplo {
    public static void main(String[] args) {
        /*Student estudiante1=new Student(4,"Luis");
        Student estudiante2=new Student(1,"Juan");
        Student estudiante3=new Student(2,"Pepe");
        Student estudiante4=new Student(5,"Mateo");
        List<Student> lista=new ArrayList<>();
        lista.add(estudiante1);
        lista.add(estudiante2);
        lista.add(estudiante3);
        lista.add(estudiante4);
        Collections.sort(lista);
        System.out.println(lista);*/
        Teacher estudiante1=new Teacher(4,"Juan");
        Teacher estudiante2=new Teacher(1,"Bira");
        Teacher estudiante3=new Teacher(2,"Dav");
        Teacher estudiante4=new Teacher(5,"Entr");
        Teacher estudiante5=new Teacher(3,"Carlos");
        //List<Teacher> lista=List.of(estudiante1, estudiante2, estudiante3, estudiante4,estudiante5);
        List<Teacher> lista=new ArrayList<>();
        lista.add(estudiante1);
        lista.add(estudiante2);
        lista.add(estudiante3);
        lista.add(estudiante4);
        lista.add(estudiante5);
        Collections.sort(lista,new TeacherNameComparator());
        System.out.println(lista);
    }
}
