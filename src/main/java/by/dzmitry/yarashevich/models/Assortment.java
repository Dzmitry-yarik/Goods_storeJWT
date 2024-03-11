package by.dzmitry.yarashevich.models;

import jakarta.persistence.*;
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
@Table(name = "assortment")
public class Assortment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assortId;
    @Column(name = "exbar_body")
    private String exbarBody;
    @Column(name = "pack_name")
    private String packName;
    @Column(name = "pack_quant")
    private BigDecimal packQuant;
    @Column(name = "pack_type")
    private Boolean packType;
    @Column(name = "pack_price")
    private BigDecimal packPrice;
    @Column(name = "pack_bonus")
    private BigDecimal packBonus;
    @Column(name = "unit_name")
    private String unitName;
}