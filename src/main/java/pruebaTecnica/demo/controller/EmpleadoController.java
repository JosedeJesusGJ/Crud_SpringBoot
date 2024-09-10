package pruebaTecnica.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebaTecnica.demo.entity.Empleados;
import pruebaTecnica.demo.service.EmpleadoService;

@RestController
@RequestMapping(path = "api/v1/empleados")
public class EmpleadoController {
    @Autowired
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<Empleados> getEmpleados() {
        return empleadoService.getEmpleados();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        empleadoService.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Empleados> getEmpleado(@PathVariable("id") Long id) {
        return empleadoService.getEmpleado(id);
    }

    @PostMapping("/crear")
    public ResponseEntity<Empleados> createEmpleado(@RequestBody Empleados empleado) {
        try {
            Empleados nuevoEmpleado = empleadoService.createEmpleado(empleado);
            return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Manejo de excepción si el cargo no se encuentra
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleados> updateEmpleado(@PathVariable Long id, @RequestBody Empleados empleado) {
        // Verificar si el empleado con el ID dado existe
        if (!empleadoService.getEmpleado(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Establecer el ID del empleado para la actualización
        empleado.setId(id);
        try {
            Empleados empleadoActualizado = empleadoService.createEmpleado(empleado);
            return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Manejo de excepción si el cargo no se encuentra
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/porCargo/{descripcionCargo}")
    public ResponseEntity<List<Empleados>> getEmpleadosPorCargo(@PathVariable String descripcionCargo) {
        List<Empleados> empleados = empleadoService.obtenerEmpleadosPorCargo(descripcionCargo);
        if (empleados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }
}
