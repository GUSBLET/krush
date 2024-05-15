package krush.ua.system;

import jakarta.persistence.*;
import krush.ua.classifier.computer.systems.ClassifierComputerSystem;
import krush.ua.classifier.computer.systems.type.ClassifierComputerSystemType;
import krush.ua.country.Country;
import krush.ua.environmental.monitoring.classifier.EnvironmentalMonitoringClassifier;
import krush.ua.system.expert.function.ExpertFunction;
import krush.ua.system.literature.Literature;
import krush.ua.system.software.type.SoftwareType;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "systems", schema = "monitoring_systems")
public class System {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(150) not null unique")
    private String name;

    @Column(name = "developer", columnDefinition = "varchar(300) not null")
    private String developer;

    @Column(name = "availability_of_decision_making_classifiers", columnDefinition = "boolean")
    private boolean availabilityOfDecisionMakingClassifier;

    @Column(name = "release_date", columnDefinition = "int")
    private Integer releaseDate;

    @Column(name = "description", columnDefinition = "varchar(1000)")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "software_type_id")
    private SoftwareType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            schema = "monitoring_systems",
            name = "system_expert_function",
            joinColumns = @JoinColumn(name = "system_id"),
            inverseJoinColumns = @JoinColumn(name = "expert_function_id")
    )
    private Set<ExpertFunction> expertFunctions = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            schema = "monitoring_systems",
            name = "system_environmental_monitoring_classifier",
            joinColumns = @JoinColumn(name = "system_id"),
            inverseJoinColumns = @JoinColumn(name = "environmental_monitoring_classifier_id")
    )
    private Set<EnvironmentalMonitoringClassifier> environmentalMonitoringClassifiers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            schema = "monitoring_systems",
            name = "system_literature",
            joinColumns = @JoinColumn(name = "system_id"),
            inverseJoinColumns = @JoinColumn(name = "literature_id")
    )
    private Set<Literature> literatures = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            schema = "monitoring_systems",
            name = "system_classifier_computer_system",
            joinColumns = @JoinColumn(name = "system_id"),
            inverseJoinColumns = @JoinColumn(name = "classifier_computer_system_id")
    )
    private Set<ClassifierComputerSystem> classifierComputerSystems = new HashSet<>();
}
