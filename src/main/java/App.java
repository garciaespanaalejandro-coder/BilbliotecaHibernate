import dao.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.*;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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

//            System.out.println("entra aquí2");
            UsuarioDAO usuarioDAO = new UsuarioDAOHib(em);
            LibroDAO libroDAO= new LibroDAOHib(em);
            AutorDAO autorDAO= new AutorDAOHib(em);
            CategoriaDAO categoriaDAO= new CategoriaDAOHib(em);
            PrestamoDAO prestamoDAO= new PrestamoDAOHib(em);
//            List<Prestamo> listaPrestamo= prestamoDAO.recuperarTodos();
//            for (Prestamo p: listaPrestamo){
//                System.out.println(p);
//            }

           System.out.println("=================RECUPERAR TODOS LOS LIBROS====================");
            List<Libro> listaLibro= libroDAO.recuperarTodos();
            for (Libro l: listaLibro){
                System.out.println(l);
            }
            Optional<Libro> libro= libroDAO.libroPorNombre("Código Limpio: Manual de artesanía software ágil");
            if (libro.isPresent()){
                System.out.println(libro.get());
            }else System.out.println("Libro no encontrado");

////todo PROBAMOS PARA ENCONTRAR
//
//            System.out.println("======COMIENZO DE BUSCAR POR ID====");
//            Optional<Usuario> usuarioOptional= usuarioDAO.buscarPorId(1);
//            Optional<Categoria> categoriaOptional= categoriaDAO.buscarPorId(1);
//            Optional<Autor> autorOptional= autorDAO.buscarPorId(1);
//            Optional<Prestamo> prestamoOptional= prestamoDAO.buscarPorId(1);

//            if (usuarioOptional.isPresent()){
//                System.out.println("===USUARIO ENCONTRADO===");
//                System.out.println(usuarioOptional.get());
//            }else {
//                System.out.println("Usuario no econtrado");
//            }
//
//           if (categoriaOptional.isPresent()){
//               System.out.println("===CATEGORIA ENCONTRADA===");
//               System.out.println(categoriaOptional.get());
//           }else {
//               System.out.println("categoria no econtrada");
//           }
//
//           if (autorOptional.isPresent()){
//               System.out.println("===AUTOR ENCONTRADO===");
//               System.out.println(autorOptional.get());
//           }else {
//               System.out.println("autor no econtrado");
//           }


//           System.out.println("======BUSCAR PRESTAMO POR ID=====");
//
//           if (prestamoOptional.isPresent()){
//               System.out.println("===PRESTAMO ENCONTRADO===");
//               System.out.println("Antes de acceder al prestamo");
//               System.out.println("Clase: "+prestamoOptional.get().getUsuario().getClass());
//               System.out.println(prestamoOptional.get());
//               System.out.println("Despues de acceder al prestamo: ");
//               System.out.println("Uduario ID: "+prestamoOptional.get().getUsuario().getId());
//           }else {
//               System.out.println("PRESTAMO no econtrado");
//           }
//
//           System.out.println();
//
////       todo    System.out.println("======ACTUALIZAR PRESTAMO=====");
//
////           if (prestamoOptional.isPresent()){
////               Prestamo p= prestamoOptional.get();
////
////               p.setEstado(EstadoPrestamo.RETRASADO);
////               p.setFecha_devolucion(LocalDate.now());
////
////               Prestamo actualizado = prestamoDAO.actualizarPrestamo(p);
////
////               System.out.println("Préstamo actualizado: "+actualizado);
////           }else{
////               System.out.println("No se ha podido actualizar el prestamo.");
////           }
////
//           EjemplarDAO ejemplarDAO= new EjemplarDAOHib(em);
//
//           Optional<Ejemplar> ejemplarOptional= ejemplarDAO.buscarPorId(1);
//           if (ejemplarOptional.isPresent()){
//               System.out.println("===PRESTAMO ENCONTRADO===");
//               System.out.println("Antes de acceder al prestamo");
//               System.out.println("Clase: "+ejemplarOptional.get().getLibro().getClass());
//               System.out.println(ejemplarOptional.get());
//               System.out.println("Despues de acceder al prestamo: ");
//               System.out.println("Libro ID: "+ejemplarOptional.get().getLibro().getId());
//           }else {
//               System.out.println("PRESTAMO no econtrado");
//           }
//
//
//           Optional <Usuario> usr= usuarioDAO.findByDni("12345678A");
//            if (usr.isPresent()){
//                System.out.println(usr.get());
//            }else System.out.println("Usuario no encontrado. ");

           System.out.println("Programa de prueba finalizado");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
