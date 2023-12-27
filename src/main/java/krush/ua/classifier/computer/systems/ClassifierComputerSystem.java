package krush.ua.classifier.computer.systems;


import jakarta.persistence.*;
import krush.ua.classifier.computer.systems.type.ClassifierComputerSystemType;
import krush.ua.system.System;
import krush.ua.system.literature.Literature;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "monitoring_systems", name = "classifier_computer_systems")
public class ClassifierComputerSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(150) not null unique")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ClassifierComputerSystemType classifierComputerSystemType;

    @ManyToMany(mappedBy = "classifierComputerSystems", cascade = CascadeType.PERSIST)
    private Set<System> systems = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            schema = "monitoring_systems",
            name = "literature_classifier_computer_system",
            joinColumns = @JoinColumn(name = "classifier_computer_system_id"),
            inverseJoinColumns = @JoinColumn(name = "literature_id")
    )
    private Set<Literature> literatures = new HashSet<>();
}
