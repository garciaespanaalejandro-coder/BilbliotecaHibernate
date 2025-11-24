import dao.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.Autor;
import model.Categoria;
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
            UsuarioDAO usuarioDAO = new UsuarioDAOHib(em);
            AutorDAO autorDAO= new AutorDAOHib(em);
            CategoriaDAO categoriaDAO= new CategoriaDAOHib(em);

            Optional<Usuario> usuarioOptional= usuarioDAO.buscarPorId(1);
            Optional<Categoria> categoriaOptional= categoriaDAO.buscarPorId(1);
            Optional<Autor> autorOptional= autorDAO.buscarPorId(1);

            if (usuarioOptional.isPresent()){
                System.out.println("===USUARIO ENCONTRADO===");
                System.out.println(usuarioOptional.get());
            }else {
                System.out.println("Usuario no econtrado");
            }

           if (categoriaOptional.isPresent()){
               System.out.println("===CATEGORIA ENCONTRADO===");
               System.out.println(categoriaOptional.get());
           }else {
               System.out.println("categoria no econtrado");
           }

           if (autorOptional.isPresent()){
               System.out.println("===AUTOR ENCONTRADO===");
               System.out.println(autorOptional.get());
           }else {
               System.out.println("autor no econtrado");
           }

           System.out.println("Programa de prueba finalizado");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
