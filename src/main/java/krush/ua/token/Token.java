package krush.ua.token;


import jakarta.persistence.*;
import krush.ua.account.Account;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tokens", schema = "monitoring_systems")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "token", columnDefinition = "varchar(100) not null")
    private String token;

    @Column(name = "time_of_creation", updatable = false)
    private LocalDateTime timeOfCreation;

    @Column(name = "lifetime", updatable = false)
    private LocalDateTime lifetime;

    @Column(name = "operation_type", columnDefinition = "VARCHAR(50) NOT NULL")
    private String operationType;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
