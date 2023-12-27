package krush.ua.country;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "countries", schema = "monitoring_systems")
public class Country {
    @Id
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(50) not null unique")
    private String name;
}
