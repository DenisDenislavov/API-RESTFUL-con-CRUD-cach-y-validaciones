package p1.p1.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EjemplarCRUD {

    @Autowired
    private EjemplarRepository ejemplarRepository;

    public List<Ejemplar> listarEjemplares() {
        return ejemplarRepository.findAll();
    }

    @Cacheable("ejemplares")
    public Optional<Ejemplar> obtenerEjemplar(Long id) {
        return ejemplarRepository.findById(id);
    }

    public Ejemplar guardarEjemplar(Ejemplar ejemplar) {
        return ejemplarRepository.save(ejemplar);
    }

    public void eliminarEjemplar(Long id) {
        ejemplarRepository.deleteById(id);
    }
}
