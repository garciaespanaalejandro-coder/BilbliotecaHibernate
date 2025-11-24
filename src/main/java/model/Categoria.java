package model;


import jakarta.persistence.*;

@Entity
@Table(name="categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", unique = true,nullable = true)
    private String name;

    @Column(name = "descripcion")
    private String descripcion;
}
