package test.work.test.work.service;

import org.springframework.stereotype.Service;
import test.work.test.work.entity.Animal;
import test.work.test.work.exceptions.ExistsWithNameException;
import test.work.test.work.exceptions.NotExistsWithIdException;
import test.work.test.work.exceptions.NotExistsWithUsernameException;
import test.work.test.work.repository.AnimalRepository;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository repository;

    public AnimalServiceImpl(AnimalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Animal> getAllAnimals() {
        return repository.findAll();
    }

    @Override
    public boolean deleteAnimalById(long id) throws NotExistsWithIdException {
        if (!repository.existsAnimalById(id))
            throw new NotExistsWithIdException("With this id does not exist");
        repository.delete(repository.findAnimalById(id));
        return true;
    }

    @Override
    public Animal addAnimal(Animal animal) throws ExistsWithNameException {
        String name = animal.getName();
        if (repository.existsAnimalByName(name))
            throw new ExistsWithNameException("With the same name already exists");
        repository.save(animal);
        return animal;
    }

    @Override
    public Animal editAnimal(Long id, Animal newAnimal) throws ExistsWithNameException, NotExistsWithIdException {
        String name = newAnimal.getName();
        if (!repository.existsAnimalById(id))
            throw new NotExistsWithIdException("With this id does not exist");
        else if (repository.existsAnimalByName(name))
            throw new ExistsWithNameException("With the same name already exists");
        newAnimal.setId(id);
        repository.delete(repository.findAnimalById(id));
        repository.save(newAnimal);
        return newAnimal;
    }

    @Override
    public Animal getAnimalById(Long id) throws NotExistsWithIdException {
        if (!repository.existsAnimalById(id))
            throw new NotExistsWithIdException("With this id does not exist");
        return repository.findAnimalById(id);
    }
}
