package model;

import jakarta.persistence.*;

@Entity
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

}
