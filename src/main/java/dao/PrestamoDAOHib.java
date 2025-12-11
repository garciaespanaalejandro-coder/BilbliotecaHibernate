package dao;


import criteria.PrestamoCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Prestamo;

import java.util.List;
import java.util.Optional;

public class PrestamoDAOHib implements PrestamoDAO {

    EntityManager entityManager;

    public PrestamoDAOHib(EntityManager entityManager){
        this.entityManager= entityManager;
    }

    @Override
    public boolean crearPrestamo(Prestamo prestamo) {
        EntityTransaction tran= entityManager.getTransaction();
        try{
            tran.begin();
            entityManager.persist(prestamo);
            tran.commit();
            return true;
        } catch (Exception e) {
            tran.rollback();
            return false;
        }
    }

    @Override
    public Optional<Prestamo> buscarPorId(int id) {
        Prestamo prestamo= entityManager.find(Prestamo.class, id);
        Optional<Prestamo> prestamoOptional= Optional.of(prestamo);
        return prestamoOptional;
    }

    @Override
    public Prestamo actualizarPrestamo(Prestamo prestamo) {
        EntityTransaction tran= entityManager.getTransaction();
        try {
            tran.begin();
            Prestamo prestamo1=entityManager.merge(prestamo);
            tran.commit();
            return prestamo1;
        } catch (Exception e) {
            tran.rollback();
            throw new RuntimeException("Error al actualizar un prestamo: "+e.getMessage());
        }
    }

    @Override
    public boolean borrarPrestamo(Prestamo prestamo) {
        EntityTransaction tran= entityManager.getTransaction();
        try{
            tran.begin();
            Prestamo prestamo1= entityManager.find(Prestamo.class, prestamo.getId());
            if (prestamo1!=null){
                entityManager.remove(prestamo);
                tran.commit();
                return true;
            }return false;
        } catch (Exception e) {
            tran.rollback();
            return false;
        }
    }

    public List<Prestamo> recuperarTodos(){
        String jpql= "SELECT p FROM Prestamo p"; //p es all, utilizamos un alias
        TypedQuery<Prestamo> query= entityManager.createQuery(jpql, Prestamo.class);
        return query.getResultList();
    }

    public List<Prestamo> getPrestamoCriteria(PrestamoCriteria prestamoCriteria){
        String jpql="SELECT p FROM Prestamo p WHERE 1=1 ";
        if (prestamoCriteria.isPresentEstadoprestamo()){
            jpql +=" AND p.estado =: estadoPrestamo";
        }
        if (prestamoCriteria.isPresentFechaInicio()){
            jpql+= " AND p.fechaInicio = BETWEEN :fecaInicio AND fechaFin ";
        }
        TypedQuery<Prestamo> query= entityManager.createQuery(jpql, Prestamo.class);
        if (prestamoCriteria.isPresentEstadoprestamo()){
            query.setParameter("estadoPrestamo",
                    prestamoCriteria.getEstadoPrestamo());
        }
        if (prestamoCriteria.isPresentFechaInicio()){
            query.setParameter("fechaInicio",
                    prestamoCriteria.getIniFechaInicio());
            query.setParameter("fechaFin",
                    prestamoCriteria.getIniFechaFin());
        }
        return query.getResultList();
    }

}
