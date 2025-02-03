package p1.p1.CRUD;

import jakarta.validation.constraints.Pattern;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Libro")
public class Libro {
    @Id
    @Pattern(regexp = "^\\d{3}-\\d-\\d{2}-\\d{6}-\\d$", message = "ERROR! El ISBN debe tener el formato correcto. Ejemplo: 978-0-12-345678-9")
    private String isbn;

    @NotBlank(message = "ERROR! El titulo no puede estar en blanco/vacio")
    @Size(max = 255)
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "ERROR! El titulo solo puede tener caracteres alfanumericos")
    private String titulo;

    @NotBlank(message = "ERROR! El autor no puede estar en blanco/vacio")
    @Size(max = 255)
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "ERROR! El nombre del autor solo puede tener caracteres alfanumericos")
    private String autor;

    public Libro() {}

    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
}

