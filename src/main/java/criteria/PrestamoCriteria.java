package criteria;

import model.EstadoPrestamo;

import java.time.LocalDate;

public class PrestamoCriteria {
    private EstadoPrestamo estadoPrestamo;
    private LocalDate iniFechaInicio;
    private LocalDate iniFechaFin;

    public EstadoPrestamo getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public LocalDate getIniFechaFin() {
        return iniFechaFin;
    }

    public void setIniFechaFin(LocalDate iniFechaFin) {
        this.iniFechaFin = iniFechaFin;
    }

    public LocalDate getIniFechaInicio() {
        return iniFechaInicio;
    }

    public void setIniFechaInicio(LocalDate iniFechaInicio) {
        this.iniFechaInicio = iniFechaInicio;
    }

    public boolean isPresentEstadoprestamo(){
        return this.estadoPrestamo!=null;
    }

    public boolean isPresentFechaInicio(){
        return ((this.iniFechaInicio!=null) && (this.iniFechaFin!=null));
    }
}
