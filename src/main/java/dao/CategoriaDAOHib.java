package dao;

import jakarta.persistence.EntityManager;
import model.Categoria;

import java.util.Optional;

public class CategoriaDAOHib implements CategoriaDAO{

    private EntityManager entityManager;

    public CategoriaDAOHib(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    public boolean crearUsuario(Categoria u) {
        return false;
    }

    @Override
    public Optional<Categoria> buscarPorId(int id) {
        return Optional.empty();
    }

    @Override
    public Categoria actualizarUsuario(Categoria u) {
        return null;
    }

    @Override
    public boolean borrarUsuario(Categoria u) {
        return false;
    }
}
