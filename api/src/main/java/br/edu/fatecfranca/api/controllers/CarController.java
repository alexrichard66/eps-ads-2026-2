package br.edu.fatecfranca.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.fatecfranca.api.dtos.CarRequest;
import br.edu.fatecfranca.api.entities.Car;
import br.edu.fatecfranca.api.services.CarService;
import jakarta.validation.Valid;

// Controller responsável pelas requisições HTTP de carros
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

  // POST /cars
// Valida os dados antes de cadastrar o carro.
@PostMapping
public ResponseEntity<Car> create(
        @Valid @RequestBody CarRequest request) {

    Car savedCar = service.create(request);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(savedCar);
}

    // GET: lista todos os carros
    @GetMapping
    public List<Car> findAll() {
        return service.findAll();
    }

    // GET: procura um carro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable Long id) {

        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /cars/{id}
// Valida os dados antes de atualizar o carro.
@PutMapping("/{id}")
public ResponseEntity<Car> update(
        @PathVariable Long id,
        @Valid @RequestBody CarRequest request) {

    if (!service.existsById(id)) {
        return ResponseEntity.notFound().build();
    }

    Car car = service.update(id, request);

    return ResponseEntity.ok(car);
}

    // DELETE: remove um carro pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}