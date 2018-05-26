package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;

import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
public interface ProjectorRepository  extends JpaRepository<Projector, Long> {
    @Query("select p from Projector p where NOT EXISTS(select exp_p FROM ExpositionProjector exp_p " +
            "where p.id = exp_p.projector.id) order by p.name asc")
    List<Projector> findAllFree();

    @Transactional
    @Modifying
    @Query("update Projector set status=:status where id=:id")
    void changeStatus(@Param("id") Long id, @Param("status") Character status);

    Projector findOneByName(String name);

    List<Projector> findAllByOrderByNameAsc();
}
