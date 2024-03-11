package by.dzmitry.yarashevich.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "exbarc")
public class Exbarc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exbarcId;
    private Integer packId;
    private String exbarBody;
}