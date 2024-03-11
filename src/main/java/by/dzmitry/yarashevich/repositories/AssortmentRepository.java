package by.dzmitry.yarashevich.repositories;

import by.dzmitry.yarashevich.models.Assortment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssortmentRepository extends JpaRepository<Assortment, Long> {
    Optional<Assortment> getAssortmentByExbarBody(String exbarBody);
}
