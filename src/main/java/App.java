import dao.UsuariDAOHib;
import dao.UsuarioDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Usuario;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.util.Optional;

public class App {
    public static void main(String[] args) {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        System.out.println("entra aquí");
       try(EntityManager em= Persistence
                .createEntityManagerFactory("biblioteca")
                .createEntityManager()
        ){
            System.out.println("entra aquí2");
            UsuarioDAO usuarioDAO = new UsuariDAOHib(em);

            Optional<Usuario> usuarioOptional= usuarioDAO.buscarPorId(1);

            if (usuarioOptional.isPresent()){
                System.out.println("===USUARIO ENCONTRADO===");
                System.out.println(usuarioOptional.get());
            }else {
                System.out.println("Usuario no econtrado");
            }
            System.out.println("Programa de prueba finalizado");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
