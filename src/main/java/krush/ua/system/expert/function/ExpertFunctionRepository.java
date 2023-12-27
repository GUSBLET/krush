package krush.ua.system.expert.function;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertFunctionRepository extends JpaRepository<ExpertFunction, Integer> {
}
