package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.model.ProjectorsVideos;

import java.util.List;

/**
 * Created by volkov on 20.04.2018.
 */
public interface ProjectorsVideosService {
    ProjectorsVideos addProjectorsVideos(ProjectorsVideos projectorsVideos);

    ProjectorsVideos getOneByProjectorIdWhereLastNum(Long projectorId);

    ProjectorsVideos getOneByProjectorIdByVideoId(Long projectorId, Long videoId);

    List<ProjectorsVideos> getProjectorVideos(Projector projector);


    void addProjectorsVideosList(List<String> projectorsId, List<String> videosId);

    void deleteByProjectorIdByVideoId(Long id, Long videoId);

    void updateNum(ProjectorsVideos projectorsVideos,Long num);
}
