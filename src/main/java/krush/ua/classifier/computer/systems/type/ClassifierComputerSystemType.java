package krush.ua.classifier.computer.systems.type;

import jakarta.persistence.*;
import krush.ua.classifier.computer.systems.ClassifierComputerSystem;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "classifier_computer_system_types")
public class ClassifierComputerSystemType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(50) not null, unique")
    private String name;

    @OneToMany(mappedBy = "classifierComputerSystemType", cascade = CascadeType.PERSIST)
    private Set<ClassifierComputerSystem> yachts = new HashSet<>();
}
