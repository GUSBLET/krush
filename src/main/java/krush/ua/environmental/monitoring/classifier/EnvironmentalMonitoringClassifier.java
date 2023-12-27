package krush.ua.environmental.monitoring.classifier;

import jakarta.persistence.*;
import krush.ua.system.System;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "environmental_monitoring_classifiers", schema = "monitoring_systems")
public class EnvironmentalMonitoringClassifier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(150) not null unique")
    private String name;

    @ManyToMany(mappedBy = "environmentalMonitoringClassifiers", cascade = CascadeType.PERSIST)
    private Set<System> systems = new HashSet<>();
}
