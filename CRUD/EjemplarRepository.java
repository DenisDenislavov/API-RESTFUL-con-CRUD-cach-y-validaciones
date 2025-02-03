package p1.p1.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {}
