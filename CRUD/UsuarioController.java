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
@CacheConfig(cacheNames = {"cacheUsuario"})
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioCRUD usuarioCRUD;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioCRUD.listarUsuarios();
    }

    @Cacheable
    @GetMapping("/{dni}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String dni) {
        Optional<Usuario> usuario = usuarioCRUD.obtenerUsuario(dni);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(obtenerErrores(bindingResult));
        }
        Usuario nuevoUsuario = usuarioCRUD.guardarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PutMapping("/{dni}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable String dni, @Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(obtenerErrores(bindingResult));
        }
        if (!usuarioCRUD.obtenerUsuario(dni).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuario.setDni(dni);
        return ResponseEntity.ok(usuarioCRUD.guardarUsuario(usuario));
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable String dni) {
        if (!usuarioCRUD.obtenerUsuario(dni).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuarioCRUD.eliminarUsuario(dni);
        return ResponseEntity.noContent().build();
    }

    private List<String> obtenerErrores(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(error -> "Campo '" + error.getField() + "': " + error.getDefaultMessage())
                .collect(Collectors.toList());
    }
}