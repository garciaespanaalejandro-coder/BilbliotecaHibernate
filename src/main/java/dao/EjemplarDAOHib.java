package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Ejemplar;

import java.util.Optional;

public class EjemplarDAOHib implements EjemplarDAO{

    EntityManager entityManager;

    public EjemplarDAOHib(EntityManager entityManager){
        this.entityManager= entityManager;
    }

    @Override
    public boolean crearEjemplar(Ejemplar e) {
        EntityTransaction tran= entityManager.getTransaction();
        try{
            tran.begin();
            entityManager.persist(e);
            tran.commit();
            return true;
        } catch (Exception ex) {
            tran.rollback();
            return false;
        }
    }

    @Override
    public Optional<Ejemplar> buscarPorId(int id) {
        Ejemplar ejemplar= entityManager.find(Ejemplar.class, id);
        Optional<Ejemplar> ejemplarOptional= Optional.of(ejemplar);
        return ejemplarOptional;
    }

    @Override
    public Ejemplar actualizarEjemplar(Ejemplar e) {
        EntityTransaction tran= entityManager.getTransaction();
        try{
            tran.begin();
            Ejemplar ejemplar=entityManager.merge(e);
            tran.commit();
            return ejemplar;
        } catch (Exception ex) {
            tran.rollback();
            throw new RuntimeException("Error al actualizar u");
        }
    }

    @Override
    public boolean borrarEjemplar(Ejemplar e) {
        EntityTransaction tran= entityManager.getTransaction();
        try{
            tran.begin();
            Ejemplar ejemplarEcontrado=entityManager.find(Ejemplar.class, e.getId());
            if (ejemplarEcontrado!=null){
                entityManager.merge(e);
                tran.commit();
                return true;
            }return false;
        } catch (Exception ex) {
            tran.rollback();
            return false;
        }
    }
}
