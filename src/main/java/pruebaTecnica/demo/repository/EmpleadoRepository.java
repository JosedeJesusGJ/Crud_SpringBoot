package pruebaTecnica.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pruebaTecnica.demo.entity.Empleados;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleados, Long> {

    @Query("SELECT e FROM Empleados e INNER JOIN e.cargo c WHERE c.descripcion = :descripcion")
    List<Empleados> findByCargoDescripcion(@Param("descripcion") String descripcion);
    // List<Empleados> findByCargoDescripcion(String descripcion);
}
