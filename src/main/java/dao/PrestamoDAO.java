package dao;


import criteria.PrestamoCriteria;
import model.Prestamo;

import java.util.List;
import java.util.Optional;

public interface PrestamoDAO {
    boolean crearPrestamo(Prestamo prestamo);
    Optional<Prestamo> buscarPorId(int id);
    Prestamo actualizarPrestamo (Prestamo prestamo);
    boolean borrarPrestamo (Prestamo prestamo);
    public List<Prestamo> recuperarTodos();
    public List<Prestamo> getPrestamoCriteria(PrestamoCriteria prestamoCriteria);
    }
