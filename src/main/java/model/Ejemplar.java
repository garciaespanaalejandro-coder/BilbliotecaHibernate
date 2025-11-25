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

    @Column(name = "libro_id")
    private int libro_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getLibro_id() {
        return libro_id;
    }

    public void setLibro_id(int libro_id) {
        this.libro_id = libro_id;
    }

    public EstadoENUM getEstado() {
        return estado;
    }

    public void setEstado(EstadoENUM estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Ejemplar{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", estado=" + estado +
                ", ubicacion='" + ubicacion + '\'' +
                ", libro_id=" + libro_id +
                '}';
    }
}
