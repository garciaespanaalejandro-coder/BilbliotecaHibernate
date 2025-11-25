package dao;


import model.Prestamo;

import java.util.Optional;

public interface PrestamoDAO {
    boolean crearPrestamo(Prestamo prestamo);
    Optional<Prestamo> buscarPorId(int id);
    Prestamo actualizarPrestamo (Prestamo prestamo);
    boolean borrarPrestamo (Prestamo prestamo);
}
