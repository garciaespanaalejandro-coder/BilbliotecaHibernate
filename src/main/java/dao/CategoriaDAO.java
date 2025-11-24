package dao;

import model.Categoria;

import java.util.Optional;

public interface CategoriaDAO {
    boolean crearUsuario(Categoria u);
    Optional<Categoria> buscarPorId(int id);
    Categoria actualizarUsuario(Categoria u);
    boolean borrarUsuario(Categoria u);
}
