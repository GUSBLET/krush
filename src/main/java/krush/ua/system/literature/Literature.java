package krush.ua.system.literature;

import jakarta.persistence.*;
import krush.ua.classifier.computer.systems.ClassifierComputerSystem;
import krush.ua.system.System;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "monitoring_systems",name = "literatures")
public class Literature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(255) not null unique")
    private String name;

    @Column(name = "author", columnDefinition = "varchar(150)")
    private String author;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "url", columnDefinition = "text")
    private String url;

    @Column(name = "date_of_last_access", columnDefinition = "date")
    private Date dateOfLastAccess;

    @ManyToMany(mappedBy = "literatures", cascade = CascadeType.PERSIST)
    private Set<System> systems = new HashSet<>();

    @ManyToMany(mappedBy = "literatures", cascade = CascadeType.PERSIST)
    private Set<ClassifierComputerSystem> classifierComputerSystems = new HashSet<>();
}
