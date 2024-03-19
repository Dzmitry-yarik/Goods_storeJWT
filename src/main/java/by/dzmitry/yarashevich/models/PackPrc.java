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
@Table(name = "packprc")
public class PackPrc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packprcId;
    private BigDecimal packPrice;
    private BigDecimal packBonus;
}