package test.work.test.work.controller;

import org.apache.catalina.LifecycleState;
import org.hibernate.hql.internal.ast.tree.AbstractNullnessCheckNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.work.test.work.entity.Animal;
import test.work.test.work.exceptions.ExistsWithNameException;
import test.work.test.work.exceptions.NotExistsWithIdException;
import test.work.test.work.exceptions.NotExistsWithUsernameException;
import test.work.test.work.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        List<Animal> animalList = animalService.getAllAnimals();
        return new ResponseEntity<>(animalList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable Long id) {
        Animal getAnimal = null;
        try {
            getAnimal = animalService.getAnimalById(id);
        } catch (NotExistsWithIdException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(getAnimal, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Long> addAnimal(@RequestBody Animal animal) {
        try {
            animalService.addAnimal(animal);
        } catch (ExistsWithNameException e) {
            return new ResponseEntity<>(-1L, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(animal.getId(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteAnimal(@PathVariable Long id) {
        try {
            animalService.deleteAnimalById(id);
        } catch (NotExistsWithIdException e) {
            return new ResponseEntity<>(-1L, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Long> editAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        try {
            animalService.editAnimal(id, animal);
        }  catch (NotExistsWithIdException e) {
            return new ResponseEntity<>(-1L, HttpStatus.BAD_REQUEST);
        } catch (ExistsWithNameException e) {
            return new ResponseEntity<>(-1L, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
