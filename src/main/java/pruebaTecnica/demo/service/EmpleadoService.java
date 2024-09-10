package pruebaTecnica.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pruebaTecnica.demo.entity.Cargos;
import pruebaTecnica.demo.entity.Empleados;
import pruebaTecnica.demo.repository.CargoRepository;
import pruebaTecnica.demo.repository.EmpleadoRepository;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    public List<Empleados> getEmpleados() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleados> getEmpleado(Long id) {
        return empleadoRepository.findById(id);
    }

    public void saveOrUpdate(Empleados empleado) {
        empleadoRepository.save(empleado);
    }

    public void delete(Long id) {
        empleadoRepository.deleteById(id);
    }

    public List<Empleados> obtenerEmpleadosPorCargo(String nombreCargo) {
        return empleadoRepository.findByCargoDescripcion(nombreCargo);
    }

    @Autowired
    private CargoRepository cargoRepository;

    // Método para crear un nuevo empleado
    public Empleados createEmpleado(Empleados empleado) {
        // Buscar el cargo por ID
        Cargos cargo = cargoRepository.findById(empleado.getCargo().getId_cargo())
                .orElseThrow(() -> new RuntimeException("Cargo no encontrado"));
        // Asociar el cargo al empleado
        empleado.setCargo(cargo);
        // Guardar el empleado
        return empleadoRepository.save(empleado);
    }

}
