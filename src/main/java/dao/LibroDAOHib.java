package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Libro;
import model.Prestamo;
import model.Usuario;

import java.util.List;
import java.util.Optional;

public class LibroDAOHib implements LibroDAO{

    EntityManager entityManager;
    public LibroDAOHib(EntityManager entityManager){
        this.entityManager= entityManager;
    }

    @Override
    public boolean crearLibro(Libro libro) {
        EntityTransaction tran= entityManager.getTransaction();
        try {
            tran.begin();
            entityManager.persist(libro);
            tran.commit();
            return true;
        } catch (Exception e) {
            tran.rollback();
            return false;
        }
    }

    @Override
    public Optional<Libro> buscarPorId(int id) {
        Libro libro= entityManager.find(Libro.class, id);
        Optional<Libro> libroEncontrado= Optional.of(libro);
        return libroEncontrado;
    }

    @Override
    public Libro actualizarLibro(Libro libro) {
        EntityTransaction tran= entityManager.getTransaction();
        try {
            tran.begin();
            Libro libro1= entityManager.merge(libro);
            tran.commit();
            return libro1;
        } catch (Exception e) {
            tran.rollback();
            throw new RuntimeException("Error al actualizar el libro: "+e.getMessage());
        }
    }

    @Override
    public boolean borrarLibro(Libro libro) {
        EntityTransaction tran= entityManager.getTransaction();
        try{
            tran.begin();
            Libro libro1= entityManager.find(Libro.class,libro.getId());
            if (libro1!=null){
                entityManager.remove(libro1);
                tran.commit();
                return true;
            } else return false;
        } catch (Exception e) {
            tran.rollback();
            return false;
        }
    }

    public List<Libro> recuperarTodos(){
        String jpql= "SELECT l FROM Libro l"; //p es all, utilizamos un alias
        TypedQuery<Libro> query= entityManager.createQuery(jpql, Libro.class);
        return query.getResultList();
    }

    public Optional<Libro> libroPorNombre(String nombre){
        String jpql="SELECT l FROM Libro l WHERE l.titulo= :nombre";
        TypedQuery<Libro> query= entityManager.createQuery(jpql, Libro.class);
        query.setParameter("nombre", nombre);
        Libro libro= query.getSingleResult();
        return Optional.of(libro);
    }
}
