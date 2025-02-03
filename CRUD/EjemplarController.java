package p1.p1.CRUD;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CacheConfig(cacheNames = {"cacheEjemplar"})
@RequestMapping("/api/ejemplares")
public class EjemplarController {

    @Autowired
    private EjemplarCRUD ejemplarCRUD;

    @GetMapping
    public List<Ejemplar> listarEjemplares() {
        return ejemplarCRUD.listarEjemplares();
    }

    @Cacheable
    @GetMapping("/{id}")
    public ResponseEntity<Ejemplar> obtenerEjemplar(@PathVariable Long id) {
        Optional<Ejemplar> ejemplar = ejemplarCRUD.obtenerEjemplar(id);
        return ejemplar.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crearEjemplar(@Valid @RequestBody Ejemplar ejemplar, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(obtenerErrores(bindingResult));
        }
        Ejemplar nuevoEjemplar = ejemplarCRUD.guardarEjemplar(ejemplar);
        return ResponseEntity.ok(nuevoEjemplar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEjemplar(@PathVariable Long id, @Valid @RequestBody Ejemplar ejemplar, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(obtenerErrores(bindingResult));
        }
        if (!ejemplarCRUD.obtenerEjemplar(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ejemplar.setId(id);
        return ResponseEntity.ok(ejemplarCRUD.guardarEjemplar(ejemplar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEjemplar(@PathVariable Long id) {
        if (!ejemplarCRUD.obtenerEjemplar(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ejemplarCRUD.eliminarEjemplar(id);
        return ResponseEntity.noContent().build();
    }

    private List<String> obtenerErrores(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(error -> "Campo '" + error.getField() + "': " + error.getDefaultMessage())
                .collect(Collectors.toList());
    }
}