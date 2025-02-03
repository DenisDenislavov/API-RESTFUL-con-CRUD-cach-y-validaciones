package p1.p1.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioCRUD {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Cacheable("usuarios")
    public Optional<Usuario> obtenerUsuario(String dni) {
        return usuarioRepository.findById(dni);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(String dni) {
        usuarioRepository.deleteById(dni);
    }
}
