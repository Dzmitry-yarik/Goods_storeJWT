package by.dzmitry.yarashevich.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequest {
    private String[] exbarBody;
    private BigDecimal[] quantity;
    private Boolean[] isDiscountProvided;
    private BigDecimal cashPrice;
    private BigDecimal cardPrice;
}