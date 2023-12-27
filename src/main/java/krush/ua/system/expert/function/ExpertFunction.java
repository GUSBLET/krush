package krush.ua.system.expert.function;

import jakarta.persistence.*;
import krush.ua.system.System;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "expert_functions", schema = "monitoring_systems")
public class ExpertFunction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(50) not null unique")
    private String name;

    @ManyToMany(mappedBy = "expertFunctions", cascade = CascadeType.PERSIST)
    private Set<System> systems = new HashSet<>();
}
