package pruebaTecnica.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pruebaTecnica.demo.entity.Cargos;
import pruebaTecnica.demo.repository.CargoRepository;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    // Método para obtener un cargo por su ID
    public Cargos getCargoById(Long id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cargo no encontrado"));
    }

    // Método para obtener un cargo por su descripción
    public Cargos getCargoByDescripcion(String descripcion) {
        return cargoRepository.findByDescripcion(descripcion);
    }

    // Método para guardar un nuevo cargo
    public Cargos saveCargo(Cargos cargo) {
        return cargoRepository.save(cargo);
    }

    // Método para actualizar un cargo existente
    public Cargos updateCargo(Long id, Cargos cargoDetails) {
        Cargos cargo = getCargoById(id);
        cargo.setDescripcion(cargoDetails.getDescripcion());
        cargo.setSalario(cargoDetails.getSalario());
        return cargoRepository.save(cargo);
    }

    // Método para eliminar un cargo
    public void deleteCargo(Long id) {
        cargoRepository.deleteById(id);
    }
}