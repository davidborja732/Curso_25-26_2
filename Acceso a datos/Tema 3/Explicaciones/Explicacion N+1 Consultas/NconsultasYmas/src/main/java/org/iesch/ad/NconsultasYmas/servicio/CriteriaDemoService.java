package org.iesch.ad.NconsultasYmas.servicio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.iesch.ad.NconsultasYmas.modelo.Autor;
import org.iesch.ad.NconsultasYmas.modelo.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CriteriaDemoService {
    @Autowired
    private EntityManager entityManager;
    @Transactional
    public void ejemploCOnsultaSimple() {
        System.out.println("SELECT * FROM autores");
        // Nos creamos el builder
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        // Entidad a interrogar
        CriteriaQuery<Autor> criteriaQuery= cb.createQuery(Autor.class);
        // Craar root (From en mysql)
        Root<Autor> autor=criteriaQuery.from(Autor.class);
        // Construimod el select
        criteriaQuery.select(autor);
        // Crear y ejecutar la consulta
        TypedQuery<Autor> query= entityManager.createQuery(criteriaQuery);
        List<Autor> autores=query.getResultList();
        System.out.println("Resultados encontrados");
        for (Autor a:autores){
            System.out.println(a.getNombre());
        }
    }
    @Transactional
    public void ejemploCOnsultaConWHERE() {
        System.out.println("SELECT * FROM autores WHERE naconalidad='argentina'");
        // Nos creamos el builder
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        // Entidad a interrogar
        CriteriaQuery<Autor> criteriaQuery= cb.createQuery(Autor.class);
        // Craar root (From en mysql)
        Root<Autor> autor=criteriaQuery.from(Autor.class);
        // Construimod el select
        criteriaQuery.select(autor).where(cb.equal(autor.get("nacionalidad"),"Argentina"));
        // Crear y ejecutar la consulta
        TypedQuery<Autor> query= entityManager.createQuery(criteriaQuery);
        List<Autor> autores=query.getResultList();
        System.out.println("Resultados encontrados");
        for (Autor a:autores){
            System.out.println(a.getNombre());
        }
    }

    public void ConsultaConLike() {
        System.out.println("Consulta con Like");
        System.out.println("SELECT * FROM autores WHERE nombre like'%Jorge%'");
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        // Entidad a interrogar
        CriteriaQuery<Autor> criteriaQuery= cb.createQuery(Autor.class);
        // Craar root (From en mysql)
        Root<Autor> autor=criteriaQuery.from(Autor.class);
        // WHERE nombre like jorge
        criteriaQuery.select(autor).where(cb.like(autor.get("nombre"),"%Jorge%"));
        TypedQuery<Autor> query= entityManager.createQuery(criteriaQuery);
        List<Autor> autores=query.getResultList();
        autores.forEach(autor1 -> System.out.println(autor1.getNombre()+" "+autor1.getApellido()));
    }
    @Transactional
    public void ConsultaConJoin() {
        System.out.println("Consulta con Join");
        System.out.println("SELECT l FROM libro l JOIN l.autor a where a.nacionalidad='Colombia'");
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        // Entidad a interrogar
        CriteriaQuery<Libro> criteriaQuery= cb.createQuery(Libro.class);
        // Craar root (From en mysql)
        Root<Libro> libro=criteriaQuery.from(Libro.class);
        //
        Join<Libro,Autor> unionautorlibro=libro.join("autor");
        // JOIN l.autor a where a.nacionalidad='Colombia'
        criteriaQuery.select(libro).where(cb.equal(unionautorlibro.get("nacionalidad"),"Colombiana"));
        TypedQuery<Libro> query= entityManager.createQuery(criteriaQuery);
        List<Libro> libros=query.getResultList();
        System.out.println("Libros autores colombianos");
        for (Libro libro1:libros){
            System.out.println(libro1);
        }
    }

    public void ConsultaConMultiplesCondiciones() {
        System.out.println("Consulta con And,OR, ETC.....");
        System.out.println("SELECT * FROM libros where precio >15 and anio_publicacion");
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        // Entidad a interrogar
        CriteriaQuery<Libro> criteriaQuery= cb.createQuery(Libro.class);
        // Craar root (From en mysql)
        Root<Libro> libro=criteriaQuery.from(Libro.class);
        Predicate precioMayorA15=cb.gt(libro.get("precio"),15.0);
        Predicate publicacion=cb.gt(libro.get("anioPublicacion"),1960);
        // WHERE
        criteriaQuery.select(libro).where(cb.and(precioMayorA15,publicacion));
        TypedQuery<Libro> query= entityManager.createQuery(criteriaQuery);
        List<Libro> libros=query.getResultList();
        for (Libro libro1:libros){
            System.out.println(libro1.getTitulo());
            System.out.println(libro1.getAnioPublicacion());
            System.out.println(libro1.getPrecio());
        }
    }
    /*@Transactional
    public void ConsultaDinamica(String nacionalidad, Double precio1,Integer aniominimo) {
        System.out.println("Consulta con And,OR, ETC.....");
        System.out.println("SELECT l FROM libro l JOIN l.autor a where a.nacionalidad=parametro and precio > precio");
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        // Entidad a interrogar
        CriteriaQuery<Libro> criteriaQuery= cb.createQuery(Libro.class);
        // Craar root (From en mysql)
        Root<Libro> libro=criteriaQuery.from(Libro.class);
        Join<Libro,Autor> unionautorlibro=libro.join("autor");
        // Lista de predicados
        List <Predicate> predicates=new ArrayList<>();
        // Voy añadiendo condiciones
        if (nacionalidad!=null && nacionalidad.isEmpty()){
            predicates.add(cb.equal(unionautorlibro.get("nacionalidad"),nacionalidad));
        }
        if (precio1!=null){
            predicates.add(cb.gt(unionautorlibro.get("precio"),precio1));
        }
        if (aniominimo!=null){
            predicates.add(cb.gt(unionautorlibro.get("anioPublicacion"),aniominimo));
        }
        // Combinar
        if (!predicates.isEmpty()){
            criteriaQuery.select(libro).where(cb.and(predicates.toArray(new Predicate[0])));
        }else {
            criteriaQuery.select(libro);
        }
        TypedQuery<Libro> query= entityManager.createQuery(criteriaQuery);
        List<Libro> libros=query.getResultList();
        System.out.println("Libros con criterios dinamicos");
        for (Libro libro1:libros){
            System.out.println(libro1);
        }
    }*/
    @Transactional
    public void ConsultaConOrderBy() {
        // Order BY año de publicacion
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        // Entidad a interrogar
        CriteriaQuery<Libro> criteriaQuery= cb.createQuery(Libro.class);
        // Craar root (From en mysql)
        Root<Libro> libro=criteriaQuery.from(Libro.class);
        criteriaQuery.select(libro).orderBy(cb.desc(libro.get("anioPublicacion")));
        TypedQuery<Libro> query= entityManager.createQuery(criteriaQuery);
        List<Libro> libros=query.getResultList();
        for (Libro libro1:libros){
            System.out.println(libro1.getTitulo());
            System.out.println(libro1.getAnioPublicacion());
            System.out.println(libro1.getPrecio());
        }
    }
    public void ConsultaConAgregaciones() {
        System.out.println("Group By contando libros por autor");
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        // Entidad a interrogar
        CriteriaQuery<Object[]> criteriaContador= cb.createQuery(Object[].class);
        // Craar root (From en mysql)
        Root<Libro> libro=criteriaContador.from(Libro.class);
        Join<Libro,Autor> unionautorlibro=libro.join("autor");

        criteriaContador.multiselect(
                unionautorlibro.get("nombre"),
                unionautorlibro.get("apellido"),
                cb.count(libro)
        ).groupBy(
                unionautorlibro.get("id"),
                unionautorlibro.get("nombre"),
                unionautorlibro.get("apellido")
        );
        TypedQuery<Object[]> query= entityManager.createQuery(criteriaContador);
        List<Object[]> resultados=query.getResultList();
        System.out.println("Contamos: ");
        for (Object[] resultado : resultados){
            System.out.println(resultado[0]+" "+resultado[1]+" Libros publicados : "+resultado[2]);
        }
        // Buscar el libro por el precio maximo
        System.out.println("Precio maximo por autor");
        CriteriaQuery<Double> maxQuery=cb.createQuery(Double.class);
        Root<Libro> libroRoot2= maxQuery.from(Libro.class);
        maxQuery.select(cb.max(libroRoot2.get("precio")));
        Double preciomaximo=entityManager.createQuery(maxQuery).getSingleResult();
        System.out.println("Precio maximo: "+preciomaximo);
    }
}
