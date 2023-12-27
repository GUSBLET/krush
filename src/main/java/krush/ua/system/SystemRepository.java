package krush.ua.system;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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


}
