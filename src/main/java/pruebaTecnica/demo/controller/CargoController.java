package pruebaTecnica.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pruebaTecnica.demo.entity.Cargos;
import pruebaTecnica.demo.service.CargoService;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping("/{id}")
    public ResponseEntity<Cargos> getCargoById(@PathVariable Long id) {
        Cargos cargo = cargoService.getCargoById(id);
        return new ResponseEntity<>(cargo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cargos> createCargo(@RequestBody Cargos cargo) {
        Cargos savedCargo = cargoService.saveCargo(cargo);
        return new ResponseEntity<>(savedCargo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cargos> updateCargo(@PathVariable Long id, @RequestBody Cargos cargoDetails) {
        Cargos updatedCargo = cargoService.updateCargo(id, cargoDetails);
        return new ResponseEntity<>(updatedCargo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCargo(@PathVariable Long id) {
        cargoService.deleteCargo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
