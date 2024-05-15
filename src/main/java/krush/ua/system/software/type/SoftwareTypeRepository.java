package krush.ua.system.software.type;


import krush.ua.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SoftwareTypeRepository extends JpaRepository<SoftwareType, Integer> {
    Optional<SoftwareType> findByName(String name);
}
