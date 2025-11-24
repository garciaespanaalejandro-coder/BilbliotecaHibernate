package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Autor;

import java.util.Optional;

public class AutorDAOHib implements AutorDAO{

    private EntityManager entityManager;

    public AutorDAOHib(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public boolean crearAutor(Autor autor) {
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try{
            entityTransaction.begin();
            entityManager.persist(autor);
            entityTransaction.commit();
            return true;
        }catch (Exception e){
            entityTransaction.rollback();
            return  false;
        }
    }

    @Override
    public Optional<Autor> buscarPorId(int id) {
        Autor autor= entityManager.find(Autor.class, id);
        Optional<Autor> recuperado= Optional.of(autor);
        return recuperado;
    }

    @Override
    public Autor actualizarAutor(Autor autor) {
        EntityTransaction tran= entityManager.getTransaction();
        try{
            tran.begin();
            Autor autorActualizado= entityManager.merge(autor);
            tran.commit();
            return autorActualizado;
        } catch (Exception e) {
            tran.rollback();
            throw new RuntimeException("Error al actualizar el autor: "+e.getMessage());
        }
    }

    @Override
    public boolean borrarAutor(Autor autor) {
        EntityTransaction tran= entityManager.getTransaction();
        try{
            tran.begin();
            Autor autorEncontrado= entityManager.find(Autor.class, autor.getId());
            if (autorEncontrado!=null){
                entityManager.remove(autorEncontrado);
                tran.commit();
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            tran.rollback();
            return false;
        }
    }
}
