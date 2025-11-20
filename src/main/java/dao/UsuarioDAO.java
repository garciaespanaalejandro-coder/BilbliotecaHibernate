package dao;

import model.Usuario;

import java.util.Optional;

public interface UsuarioDAO {
    boolean crearUsuario(Usuario u);
    Optional<Usuario> buscarPorId(int id);
    Usuario actualizarUsuario(Usuario u);
    boolean borrarUsuario(Usuario u);
}
