package test.work.test.work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import test.work.test.work.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal findAnimalByName(String name);

    Animal findAnimalById(Long id);
    boolean existsAnimalById(Long id);

    boolean existsAnimalByName(String name);

}
