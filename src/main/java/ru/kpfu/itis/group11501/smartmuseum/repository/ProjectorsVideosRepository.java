package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.model.ProjectorsVideos;

import java.util.List;

/**
 * Created by volkov on 20.04.2018.
 */
public interface ProjectorsVideosRepository extends JpaRepository<ProjectorsVideos, Long> {

    @Query("select p from ProjectorsVideos as p where p.projector.id=?1 and p.num = (select max(p2.num) from ProjectorsVideos p2 where p2.projector.id=?1 ) ")
    ProjectorsVideos getOneByProjectorIdWhereLastNum(Long projectorId);

    @Query("select p from ProjectorsVideos as p where p.projector.id=?1 and p.video.id=?2")
    ProjectorsVideos getOneByProjectorIdByVideoID(Long projectorId, Long videoId);

    @Query("select new ProjectorsVideos(p.id, p.video, p.num) from ProjectorsVideos as p where p.projector=?1 order by p.num asc")
    List<ProjectorsVideos> findALlByProjector(Projector projector);
    @Transactional
    @Modifying
    @Query("delete from ProjectorsVideos as p "+
            "where p.projector.id = ?1 and p.video.id = ?2")
    void deleteByProjectorIdByVideoId(Long id, Long videoId);

    @Transactional
    @Modifying
    @Query("update ProjectorsVideos as p  set p.num = p.num -1 "+
            "where p.projector.id = ?1 and p.num > ?2 and p.num <= ?3 ")
    void updateAllBetweenMinus(Long projectorId,Long currentNum, Long num);

    @Transactional
    @Modifying
    @Query("update ProjectorsVideos as p  set p.num = p.num +1 "+
            "where p.projector.id = ?1 and p.num >= ?2 and p.num < ?3 ")
    void updateAllBetweenPlus(Long projectorId, Long num, Long currentNum);
}
