package by.dzmitry.yarashevich.services;

import by.dzmitry.yarashevich.models.Product;
import by.dzmitry.yarashevich.models.Sale;
import by.dzmitry.yarashevich.repositories.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SaleService {

    private SaleRepository saleRepository;

    public SaleService(SaleRepository salesRepository) {
        this.saleRepository = salesRepository;
    }

    public void deleteBySalesTime(LocalDateTime salesTime) {
        saleRepository.deleteBySalesTime(salesTime);
    }

    public BigDecimal createSale(List<Product> products, BigDecimal cashPrice, BigDecimal cardPrice) {
        List<Sale> sales = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal bonusAmount = BigDecimal.ZERO;
        if (cashPrice == null) {
            cashPrice = BigDecimal.valueOf(0);
        }
        if (cardPrice == null) {
            cardPrice = BigDecimal.valueOf(0);
        }

        for (Product product : products) {
            totalAmount = totalAmount.add(product.getTotalPrice().multiply(product.getQuantity()));
            bonusAmount = bonusAmount.add(product.getBonusPrice().multiply(product.getQuantity()));

            Sale sale = new Sale();
            sale.setSalesTag((byte) 0);
            sale.setExbarBody(product.getExbarBody());
            sale.setPackName(product.getPackName());
            sale.setUnitName(product.getUnitName());
            sale.setPrice(BigDecimal.valueOf(product.getTotalPrice().intValue()));
            sale.setBonusPrice(BigDecimal.valueOf(product.getBonusPrice().intValue()));
            sale.setQuantity(BigDecimal.valueOf(product.getQuantity().intValue()));

            sales.add(sale);
        }

        Sale sale1 = new Sale();
        sale1.setSalesTag((byte) 3);
        sale1.setPrice(totalAmount);
        sale1.setBonusPrice(bonusAmount);
        sales.add(sale1);

        Sale sale2 = new Sale();
        sale2.setSalesTag((byte) 2);
        sale2.setPrice(cashPrice);
        sales.add(sale2);

        Sale sale3 = new Sale();
        sale3.setSalesTag((byte) 1);
        sale3.setPrice(cardPrice);
        sales.add(sale3);

        saleRepository.saveAll(sales);

        return cashPrice.add(cardPrice).subtract(totalAmount);
    }

    public String processSale(List<Product> products, BigDecimal cashPrice, BigDecimal cardPrice, RedirectAttributes redirectAttributes) {
        BigDecimal change = createSale(products, cashPrice, cardPrice);
        String message;

        if (change.compareTo(BigDecimal.ZERO) < 0) {
            return message = "Недостаточно средств";
        } else if (change.compareTo(BigDecimal.ZERO) > 0) {
            return message = "Операция оплаты прошла успешно, сдача = " + change;
        } else {
            return message = "Операция оплаты прошла успешно";
        }
    }
}
