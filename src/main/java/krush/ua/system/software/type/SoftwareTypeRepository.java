package krush.ua.system.software.type;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareTypeRepository extends JpaRepository<SoftwareType, Integer> {
}
