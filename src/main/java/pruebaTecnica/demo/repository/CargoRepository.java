package pruebaTecnica.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pruebaTecnica.demo.entity.Cargos;

@Repository
public interface CargoRepository extends JpaRepository<Cargos, Long> {
    Cargos findByDescripcion(String descripcion);
}