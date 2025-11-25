import dao.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.*;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
            EjemplarDAO ejemplarDAO=new EjemplarDAOHib(em);
            LibroDAO libroDAO= new LibroDAOHib(em);
            PrestamoDAO prestamoDAO= new PrestamoDAOHib(em);

            //PROBAMOS PARA ENCONTRAR

            System.out.println("======COMIENZO DE BUSCAR POR ID====");
            Optional<Usuario> usuarioOptional= usuarioDAO.buscarPorId(1);
            Optional<Categoria> categoriaOptional= categoriaDAO.buscarPorId(1);
            Optional<Autor> autorOptional= autorDAO.buscarPorId(1);
            Optional<Prestamo> prestamoOptional= prestamoDAO.buscarPorId(1);

            if (usuarioOptional.isPresent()){
                System.out.println("===USUARIO ENCONTRADO===");
                System.out.println(usuarioOptional.get());
            }else {
                System.out.println("Usuario no econtrado");
            }

           if (categoriaOptional.isPresent()){
               System.out.println("===CATEGORIA ENCONTRADA===");
               System.out.println(categoriaOptional.get());
           }else {
               System.out.println("categoria no econtrada");
           }

           if (autorOptional.isPresent()){
               System.out.println("===AUTOR ENCONTRADO===");
               System.out.println(autorOptional.get());
           }else {
               System.out.println("autor no econtrado");
           }

           System.out.println("====FIN DE ENCONTRAR POR ID===");

           System.out.println("======COMIENZO DE CREAR PRESTAMO====");

           Prestamo prestamo = new Prestamo();
           prestamo.setFecha_inicio(LocalDate.now());
           prestamo.setFecha_fin(LocalDate.now().plusDays(15));
           prestamo.setFecha_devolucion(null);  // aún no devuelto
           prestamo.setEstado(EstadoPrestamo.ACTIVO);
           prestamo.setUsuario_id(1);      // asegúrate de que exista en tu BD
           prestamo.setEjemplar_id(1);     // asegúrate de que exista en tu BD

           boolean creado = prestamoDAO.crearPrestamo(prestamo);

           if (creado) {
               System.out.println("=== PRESTAMO CREADO ===");
               System.out.println(prestamo);
           } else {
               System.out.println("Error al crear el préstamo");
           }

           System.out.println("======FIN DE CREAR PRESTAMO====");

           System.out.println("======BUSCAR PRESTAMO POR ID=====");

           Optional<Prestamo> prestamoBuscado = prestamoDAO.buscarPorId(prestamo.getId());

           if (prestamoBuscado.isPresent()) {
               Prestamo p = prestamoBuscado.get();
               System.out.println("Préstamo encontrado: " + p);
           } else {
               System.out.println("No se encontró el préstamo");
           }
           System.out.println();

           System.out.println("======ACTUALIZAR PRESTAMO=====");
           if (prestamoBuscado.isPresent()){
               Prestamo p= prestamoBuscado.get();

               p.setEstado(EstadoPrestamo.RETRASADO);
               p.setFecha_devolucion(LocalDate.now());

               Prestamo actualizado = prestamoDAO.actualizarPrestamo(p);

               System.out.println("Préstamo actualizado: "+actualizado);
           }else{
               System.out.println("No se ha podido actualizar el prestamo.");
           }



           System.out.println("Programa de prueba finalizado");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
