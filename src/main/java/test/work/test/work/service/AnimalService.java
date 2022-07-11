package test.work.test.work.service;

import test.work.test.work.entity.Animal;
import test.work.test.work.exceptions.ExistsWithNameException;
import test.work.test.work.exceptions.NotExistsWithIdException;
import test.work.test.work.exceptions.NotExistsWithUsernameException;

import java.util.List;

public interface AnimalService {
    List<Animal> getAllAnimals();

    boolean deleteAnimalById(long id) throws NotExistsWithIdException;

    Animal addAnimal(Animal animal) throws ExistsWithNameException;

    Animal editAnimal(Long id,Animal newAnimal) throws NotExistsWithIdException, ExistsWithNameException;

    Animal getAnimalById(Long id) throws NotExistsWithIdException;
}
