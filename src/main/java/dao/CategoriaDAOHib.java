package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Categoria;

import java.util.Optional;

public class CategoriaDAOHib implements CategoriaDAO{

    private EntityManager entityManager;

    public CategoriaDAOHib(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    public boolean crearUsuario(Categoria categoria) {
        EntityTransaction tran= entityManager.getTransaction();
        try {
            tran.begin();
            entityManager.persist(categoria);
            tran.commit();
            return true;
        } catch (Exception e) {
            //AÑADIR IF
            tran.rollback();
            return false;
        }
    }

    @Override
    public Optional<Categoria> buscarPorId(int id) {
        Categoria cat= entityManager.find(Categoria.class, id);
        Optional<Categoria> categoriaRecuperada= Optional.of(cat);
        return categoriaRecuperada;
    }

    @Override
    public Categoria actualizarUsuario(Categoria u) {
        EntityTransaction tran= entityManager.getTransaction();
        try{
            tran.begin();
            Categoria categoria= entityManager.merge(u);
            tran.commit();
            return categoria;
        } catch (Exception e) {
            tran.rollback();
            throw new RuntimeException("Error al actualizar la categoria: "+e.getMessage());
        }
    }

    @Override
    public boolean borrarUsuario(Categoria u) {
        EntityTransaction tran= entityManager.getTransaction();
        try{
            tran.begin();
            Categoria categoria= entityManager.find(Categoria.class, u.getId());
            if (categoria!=null){
                entityManager.remove(categoria);
                tran.commit();
                return true;
            }
            return false;
        }catch (Exception e){
            tran.rollback();
            return false;
        }
    }
}
