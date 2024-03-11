package by.dzmitry.yarashevich.repositories;

import by.dzmitry.yarashevich.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    Optional<Sale> deleteBySalesTime(LocalDateTime salesTime);
}
