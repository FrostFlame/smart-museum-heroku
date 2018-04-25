package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.model.ProjectorsVideos;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.Action;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.CoherentEntity;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.ActionTypeName;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.EntityName;

import java.util.List;

/**
 * Created by volkov on 20.04.2018.
 */
@CoherentEntity(name = EntityName.PROJECTORSVIDEOS)
public interface ProjectorsVideosService {

    @Action(name = ActionTypeName.ADD)
    ProjectorsVideos addProjectorsVideos(ProjectorsVideos projectorsVideos);

    ProjectorsVideos getOneByProjectorIdWhereLastNum(Long projectorId);

    ProjectorsVideos getOneByProjectorIdByVideoId(Long projectorId, Long videoId);

    List<ProjectorsVideos> getProjectorVideos(Projector projector);


    void addProjectorsVideosList(List<String> projectorsId, List<String> videosId);

    void deleteByProjectorIdByVideoId(Long id, Long videoId);

    void updateNum(ProjectorsVideos projectorsVideos,Long num);
}
