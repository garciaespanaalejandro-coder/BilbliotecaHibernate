package model;

import jakarta.persistence.*;

@Entity
@Table(name = "ejemplar")
public class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "codigo", length = 50, unique = true, nullable = false)
    private String codigo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoENUM estado=  EstadoENUM.DISPONIBLE;

    @Column(name = "ubicacion", length = 100)
    private String ubicacion;

    @OneToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @OneToOne(mappedBy = "ejemplar", cascade = CascadeType.ALL)
    private Prestamo prestamo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public EstadoENUM getEstado() {
        return estado;
    }

    public void setEstado(EstadoENUM estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Ejemplar{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", estado=" + estado +
                ", ubicacion='" + ubicacion + '\'' +
                ", libro_id=" + libro +
                '}';
    }
}
