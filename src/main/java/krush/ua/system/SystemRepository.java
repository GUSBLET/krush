package krush.ua.system;

import jakarta.transaction.Transactional;
import krush.ua.country.Country;
import krush.ua.system.expert.function.ExpertFunction;
import krush.ua.system.literature.Literature;
import krush.ua.system.software.type.SoftwareType;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SystemRepository extends  PagingAndSortingRepository<System, Integer>, JpaRepository<System, Integer> {

    @Query("SELECT s FROM System s " +
            "LEFT JOIN FETCH s.type " +
            "LEFT JOIN FETCH s.country " +
            "LEFT JOIN FETCH s.expertFunctions " +
            "LEFT JOIN FETCH s.environmentalMonitoringClassifiers " +
            "LEFT JOIN FETCH s.literatures " +
            "LEFT JOIN FETCH s.classifierComputerSystems " +
            "WHERE s.id = :id")
    Optional<System> findByIdWithAssociations(@Param("id") Integer id);

    @Query("SELECT s FROM System s LEFT JOIN FETCH s.country LEFT JOIN FETCH s.type")
    Page<System> findAllWithCountryAndType(Pageable pageable);

//    @Modifying
//    @Query("INSERT INTO System (name, developer, availabilityOfDecisionMakingClassifier, releaseDate, description, type, country) " +
//            "VALUES (:name, :developer, :availabilityOfDecisionMakingClassifier, :releaseDate, :description, :type, :country)")
//    void saveSystem(String name, String developer, boolean availabilityOfDecisionMakingClassifier, Integer releaseDate,
//                    String description, SoftwareType type);

    Optional<System> findByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE System s " +
            "SET s.name = :name, " +
            "s.developer = :developer, " +
            "s.availabilityOfDecisionMakingClassifier = :availabilityOfDecisionMakingClassifier, " +
            "s.releaseDate = :releaseDate, " +
            "s.description = :description, " +
            "s.type = :type, " +
            "s.country = :country " +
            "WHERE s.id = :id")
    void updateSystemById(@Param("id") Integer id,
                         @Param("name") String name,
                         @Param("developer") String developer,
                         @Param("availabilityOfDecisionMakingClassifier") boolean availabilityOfDecisionMakingClassifier,
                         @Param("releaseDate") Integer releaseDate,
                         @Param("description") String description,
                         @Param("type") SoftwareType type,
                         @Param("country") Country country);



    @Transactional
    @Modifying
    @Query(value = "INSERT INTO monitoring_systems.system_literature (system_id, literature_id) " +
            "VALUES (:systemId, :literatureId)", nativeQuery = true)
    void addLiteratureToSystem(@Param("systemId") Integer systemId, @Param("literatureId") Integer literatureId);

    @Transactional
    @Modifying
    @Query("UPDATE System s " +
            "SET s.expertFunctions = :expertFunctions " +
            "WHERE s.id = :id")
    void updateSystemExpertById(@Param("id") Integer id,
                          @Param("expertFunctions")Set<ExpertFunction> expertFunctions);


}
