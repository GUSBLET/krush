package krush.ua.system.literature;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface LiteratureRepository extends JpaRepository<Literature, Integer> {
    Optional<Literature> findByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Literature l SET l.name = :name, l.author = :author, l.description = :description, l.url = :url, l.dateOfLastAccess = :dateOfLastAccess WHERE l.id = :id")
    void updateLiterature(@Param("id") Integer id, @Param("name") String name, @Param("author") String author, @Param("description") String description, @Param("url") String url, @Param("dateOfLastAccess") Date dateOfLastAccess);
}
