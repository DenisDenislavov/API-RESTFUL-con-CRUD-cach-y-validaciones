package p1.p1.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CacheConfig(cacheNames = {"cacheLibro"})
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroCRUD libroCRUD;

    @GetMapping
    public List<Libro> listarLibros() {
        return libroCRUD.listarLibros();
    }

    @Cacheable
    @GetMapping("/{isbn}")
    public ResponseEntity<Libro> obtenerLibro(@PathVariable String isbn) {
        Optional<Libro> libro = libroCRUD.obtenerLibro(isbn);
        return libro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crearLibro(@Valid @RequestBody Libro libro, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(obtenerErrores(bindingResult));
        }
        Libro nuevoLibro = libroCRUD.guardarLibro(libro);
        return ResponseEntity.ok(nuevoLibro);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<?> actualizarLibro(@PathVariable String isbn, @Valid @RequestBody Libro libro, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(obtenerErrores(bindingResult));
        }
        if (!libroCRUD.obtenerLibro(isbn).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        libro.setIsbn(isbn);
        return ResponseEntity.ok(libroCRUD.guardarLibro(libro));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable String isbn) {
        if (!libroCRUD.obtenerLibro(isbn).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        libroCRUD.eliminarLibro(isbn);
        return ResponseEntity.noContent().build();
    }

    private List<String> obtenerErrores(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(error -> "Campo '" + error.getField() + "': " + error.getDefaultMessage())
                .collect(Collectors.toList());
    }
}