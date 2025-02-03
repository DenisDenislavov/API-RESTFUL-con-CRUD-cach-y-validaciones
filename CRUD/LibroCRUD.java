package p1.p1.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroCRUD {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroCRUD(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    public Optional<Libro> obtenerLibro(String isbn) {
        return libroRepository.findById(isbn);
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public void eliminarLibro(String isbn) {
        libroRepository.deleteById(isbn);
    }
}
