package by.dzmitry.yarashevich.services;

import by.dzmitry.yarashevich.models.Assortment;
import by.dzmitry.yarashevich.models.Product;
import by.dzmitry.yarashevich.repositories.AssortmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AssortmentService {

    private final AssortmentRepository assortmentRepository;

    public AssortmentService(AssortmentRepository assortmentRepository) {
        this.assortmentRepository = assortmentRepository;
    }

    public List<Assortment> getAllAssortment() {
        return assortmentRepository.findAll();
    }

    public Product getAssortmentForProduct(String exbar_body, BigDecimal quantity, boolean isDiscountProvided) {
        Optional<Assortment> assortment = assortmentRepository.getAssortmentByExbarBody(exbar_body);

        if (assortment.isPresent()) {
            BigDecimal totalPrice = BigDecimal.ZERO;
            BigDecimal bonusPrice = BigDecimal.ZERO;
            if (isDiscountProvided) {
                bonusPrice = assortment.get().getPackBonus().multiply(quantity).divide(BigDecimal.valueOf(100));
                totalPrice = assortment.get().getPackPrice().multiply(quantity).divide(BigDecimal.valueOf(100)).subtract(bonusPrice);
            } else {
                totalPrice = assortment.get().getPackPrice().multiply(quantity).divide(BigDecimal.valueOf(100));
            }

            return new Product(
                    assortment.get().getExbarBody(),
                    assortment.get().getPackName(),
                    quantity,
                    totalPrice,
                    bonusPrice,
                    assortment.get().getUnitName(),
                    assortment.get().getPackType()
            );
        } else return new Product();
    }

}