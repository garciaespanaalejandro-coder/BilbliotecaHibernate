package dao;

import model.Autor;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AutorDAO {
    boolean crearAutor(Autor autor);
    Optional<Autor> buscarPorId(int id);
    Autor actualizarAutor (Autor autor);
    boolean borrarAutor (Autor autor);
}
