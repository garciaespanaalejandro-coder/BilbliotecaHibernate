package model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "isbn", nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(name= "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @Column(name = "paginas")
    private int paginas;

    @Column(name="editorial", length = 100)
    private String editorial;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy por defecto
    @JoinColumn(name = "autor_id") // FK en tabla libro
    private Autor autor;;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id") // FK en tabla libro
    private Categoria categoria;

    public Libro(int id, String isbn, String titulo, LocalDate fechaPublicacion, int paginas, String editorial, Categoria categoria, Autor autor) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
        this.paginas = paginas;
        this.editorial = editorial;
        this.categoria = categoria;
        this.autor = autor;
    }

    public Libro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "\n Libro{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", paginas=" + paginas +
                ", editorial='" + editorial + '\'' +
                ", autorId=" + this.autor.getId() + ", autorNombre= " + this.autor.getNombre() +
                ", categoriaId=" + this.categoria +
                '}';
    }
}