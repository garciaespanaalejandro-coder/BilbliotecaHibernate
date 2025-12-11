package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Usuario;

import java.util.List;
import java.util.Optional;

public class UsuarioDAOHib implements  UsuarioDAO{

    private EntityManager entityManager;

    public UsuarioDAOHib(EntityManager entityManager){
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
    public Optional<Usuario> findByDni(String dniPar){
        String jpql= "SELECT u FROM Usuario u WHERE "+
                "u.dni= :dniPar";
        TypedQuery<Usuario> query= entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("dniPar",dniPar);
        Usuario user = query.getSingleResult(); //Ejecutar la consulta como Prepared Statement
        return Optional.of(user);
    }

    public List<Object []> favoritosUsuarios(){
        String jpql= "SELECT u, count(l) FROM Usuario u LEFT JOIN u.librosFavoritos GROUP BY u ";
        TypedQuery<Object[]> query= entityManager.createQuery(jpql, Object[].class);
        return query.getResultList();
    }

}
