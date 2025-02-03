package p1.p1.CRUD;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Ejemplar")
class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "isbn", nullable = false)
    private Libro libro;

    // @Enumerated(EnumType.STRING)
    @NotNull(message = "ERROR! El estado no puede ser nulo")
    @Pattern(regexp = "Disponible|Prestado|Danado", message = "ERROR! El ejemplar solo puede ser: Disponible | Prestado | Danado")
    private String estado;

    public Ejemplar(Libro libro, String estado) {
        this.libro = libro;
        this.estado = estado;
    }

    public Ejemplar() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public enum Estado {
        Disponible,
        Prestado,
        Danado
    }


}
