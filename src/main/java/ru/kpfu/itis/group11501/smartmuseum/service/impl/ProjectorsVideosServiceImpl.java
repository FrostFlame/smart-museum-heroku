package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.model.ProjectorsVideos;
import ru.kpfu.itis.group11501.smartmuseum.model.Video;
import ru.kpfu.itis.group11501.smartmuseum.repository.ProjectorsVideosRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorsVideosService;

import java.util.List;

/**
 * Created by volkov on 20.04.2018.
 */
@Service
public class ProjectorsVideosServiceImpl implements ProjectorsVideosService{

    private ProjectorsVideosRepository projectorsVideosRepository;

    public ProjectorsVideosServiceImpl(ProjectorsVideosRepository projectorsVideosRepository) {
        this.projectorsVideosRepository = projectorsVideosRepository;
    }

    @Override
    public ProjectorsVideos addProjectorsVideos(ProjectorsVideos projectorsVideos){
        return projectorsVideosRepository.save(projectorsVideos);
    }
    @Override
    public ProjectorsVideos getProjectorsVideosByProjectorIdWhereLastNum(Long projectorId){
        return projectorsVideosRepository.getProjectorsVideosByProjectorIdWhereLastNum(projectorId);
    }

    @Override
    public ProjectorsVideos getProjectorsVideosByProjectorIdByVideoId(Long projectorId, Long videoId){
        return projectorsVideosRepository.getProjectorsVideosByProjectorIdByVideoID(projectorId,videoId);
    }

    @Override
    public List<ProjectorsVideos> getProjectorVideos(Projector projector) {
        return projectorsVideosRepository.findALlByProjector(projector);
    }
}
