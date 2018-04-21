package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.group11501.smartmuseum.model.ProjectorsVideos;

/**
 * Created by volkov on 20.04.2018.
 */
public interface ProjectorsVideosRepository extends JpaRepository<ProjectorsVideos, Long> {

    @Query("select p from ProjectorsVideos as p where p.projector.id=?1 and p.num = (select max(p2.num) from ProjectorsVideos p2 where p2.projector.id=?1 ) ")
    ProjectorsVideos getProjectorsVideosByProjectorIdWhereLastNum(Long projectorId);

    @Query("select p from ProjectorsVideos as p where p.projector.id=?1 and p.video.id=?2")
    ProjectorsVideos getProjectorsVideosByProjectorIdByVideoID(Long projectorId, Long videoId);
}
