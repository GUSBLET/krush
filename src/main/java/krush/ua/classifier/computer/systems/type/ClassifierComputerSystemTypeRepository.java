package krush.ua.classifier.computer.systems.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassifierComputerSystemTypeRepository extends JpaRepository<ClassifierComputerSystemType, Integer> {
}
