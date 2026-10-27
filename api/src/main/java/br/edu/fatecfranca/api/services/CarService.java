package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.fatecfranca.api.dtos.CarRequest;
import br.edu.fatecfranca.api.entities.Car;
import br.edu.fatecfranca.api.entities.Customer;
import br.edu.fatecfranca.api.repositories.CarRepository;
import br.edu.fatecfranca.api.repositories.CustomerRepository;

@Service
public class CarService {

    private final CarRepository repository;
    private final CustomerRepository customerRepository;

    // Injeta os repositories para acessar carros e clientes
    public CarService(
            CarRepository repository,
            CustomerRepository customerRepository) {

        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    // NOVO: copia os dados do DTO para a entidade Car
   
    private void copyToEntity(CarRequest request, Car car) {

        car.setBrand(request.brand());
        car.setModel(request.model());
        car.setColor(request.color());
        car.setYearManufacture(request.yearManufacture());
        car.setImported(request.imported());
        car.setPlates(request.plates());
        car.setSellingDate(request.sellingDate());
        car.setSellingPrice(request.sellingPrice());

        // Localiza o cliente associado ao carro pelo ID
        if (request.customerId() != null) {

            Customer customer = customerRepository
                    .findById(request.customerId())
                    .orElseThrow(() ->
                        new IllegalArgumentException(
                            "Cliente não encontrado: "
                            + request.customerId()
                        )
                    );

            car.setCustomer(customer);

        } else {
            car.setCustomer(null);
        }
    }

    // ALTERADO: recebe CarRequest em vez de Car
    public Car create(CarRequest request) {

        Car car = new Car();

        copyToEntity(request, car);

        return repository.save(car);
    }

    // Lista todos os carros cadastrados
    public List<Car> findAll() {
        return repository.findAll();
    }

    // Busca um carro pelo ID
    public Optional<Car> findById(Long id) {
        return repository.findById(id);
    }

    // Verifica se um carro existe
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    // ALTERADO: atualiza o carro usando os dados do DTO
    public Car update(Long id, CarRequest request) {

        Car car = repository.findById(id)
                .orElseThrow(() ->
                    new IllegalArgumentException(
                        "Carro não encontrado: " + id
                    )
                );

        copyToEntity(request, car);

        return repository.save(car);
    }

    // Exclui um carro pelo ID
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}