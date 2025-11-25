package model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name ="prestamo" )
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fecha_inicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fecha_fin;

    @Column(name ="fecha_devolucion")
    private LocalDate fecha_devolucion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoPrestamo estado;

    //@Column(name = "usuario_id", nullable = false)
    //private int usuario_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "ejemplar_id", nullable = false)
    private int ejemplar_id;

    public Prestamo() {
        this.estado=EstadoPrestamo.ACTIVO;
    }

    public Prestamo(int id, int ejemplar_id, EstadoPrestamo estado, Usuario usuario, LocalDate fecha_devolucion, LocalDate fecha_fin, LocalDate fecha_inicio) {
        this.id = id;
        this.ejemplar_id = ejemplar_id;
        this.estado = estado;
        this.usuario = usuario;
        this.fecha_devolucion = fecha_devolucion;
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = fecha_inicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public LocalDate getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(LocalDate fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getEjemplar_id() {
        return ejemplar_id;
    }

    public void setEjemplar_id(int ejemplar_id) {
        this.ejemplar_id = ejemplar_id;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", fecha_devolucion=" + fecha_devolucion +
                ", estado=" + estado +
                ", usuario=" + usuario +
                ", ejemplar_id=" + ejemplar_id +
                '}';
    }
}
