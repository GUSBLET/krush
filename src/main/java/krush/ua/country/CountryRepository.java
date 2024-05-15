package krush.ua.country;

import jakarta.persistence.criteria.CriteriaBuilder;
import krush.ua.system.literature.Literature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    Optional<Country> findByName(String name);
}
