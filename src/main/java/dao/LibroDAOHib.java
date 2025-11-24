package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Libro;

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
}
