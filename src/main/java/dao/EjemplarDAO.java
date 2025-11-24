package dao;

import model.Ejemplar;

import java.util.Optional;

public interface EjemplarDAO {
    boolean crearEjemplar(Ejemplar e);
    Optional<Ejemplar> buscarPorId(int id);
    Ejemplar actualizarEjemplar(Ejemplar e);
    boolean borrarEjemplar(Ejemplar e);
}
