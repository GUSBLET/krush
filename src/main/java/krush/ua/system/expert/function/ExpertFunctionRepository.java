package krush.ua.system.expert.function;

import krush.ua.system.literature.Literature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpertFunctionRepository extends JpaRepository<ExpertFunction, Integer> {
    Optional<ExpertFunction> findByName(String name);
}
