package by.dzmitry.yarashevich.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String exbarBody;
    private String packName;
    private BigDecimal quantity;
    private BigDecimal totalPrice;
    private BigDecimal bonusPrice;
    private String unitName;
    private Boolean packType;

    public Product(String exbarBody, String packName, BigDecimal quantity, BigDecimal totalPrice, BigDecimal bonusPrice, String unitName, boolean packType) {
        this.exbarBody = exbarBody;
        this.packName = packName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.bonusPrice = bonusPrice;
        this.unitName = unitName;
        this.packType = packType;
    }
}