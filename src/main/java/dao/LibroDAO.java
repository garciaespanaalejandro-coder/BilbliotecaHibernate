package dao;

import model.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroDAO {
    boolean crearLibro(Libro libro);
    Optional<Libro> buscarPorId(int id);
    Libro actualizarLibro (Libro libro);
    boolean borrarLibro (Libro libro);
    public List<Libro> recuperarTodos();
    public Optional<Libro> libroPorNombre(String nombre);
}
