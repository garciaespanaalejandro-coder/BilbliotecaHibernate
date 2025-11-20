package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Usuario;

import java.util.Optional;

public class UsuariDAOHib implements  UsuarioDAO{

    private EntityManager entityManager;

    public UsuariDAOHib(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public boolean crearUsuario(Usuario u) {
        EntityTransaction tran= entityManager.getTransaction();
        try {
            tran.begin();
            entityManager.persist(u);//para guardar
            tran.commit();
            return true;
        }catch (Exception e){
            if (tran.isActive()){
                tran.rollback();
                return false;
            }
            throw new RuntimeException("Error al crear el usuario: "+e.getMessage());
        }
    }

    @Override
    public Optional<Usuario> buscarPorId(int id) {
        Usuario u= entityManager.find(Usuario.class, id);
        Optional<Usuario> usuarioRec= Optional.of(u);
        return  usuarioRec;
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) {
        EntityTransaction tran= entityManager.getTransaction();
        try {
            tran.begin();
            Usuario usuarioActualizado= entityManager.merge(u);
            tran.commit();
            return usuarioActualizado;
       }catch (Exception e){
            if (tran.isActive()){
                tran.rollback();
                return u;
            }
            throw new RuntimeException("Error al actualizar el usuario: "+e.getMessage());
        }
    }

    @Override
    public boolean borrarUsuario(Usuario u) {
        EntityTransaction tran= entityManager.getTransaction();
        try {
            tran.begin();
            Usuario usuarioEncontrado= entityManager.find(Usuario.class, u.getId());
            if (usuarioEncontrado!=null){
                entityManager.remove(usuarioEncontrado);
                tran.commit();
                return true;
            }
            return false;
        }catch (Exception e){
            if (tran.isActive()){
                tran.rollback();
                return false;
            }
            throw new RuntimeException("Error al borrar el usuario: "+e.getMessage());
        }
    }
}
