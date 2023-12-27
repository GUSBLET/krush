package krush.ua.classifier.computer.systems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassifierComputerSystemRepository extends JpaRepository<ClassifierComputerSystem, Integer> {
}
