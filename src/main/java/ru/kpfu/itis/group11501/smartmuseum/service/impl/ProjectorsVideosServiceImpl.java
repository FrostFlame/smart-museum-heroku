package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.model.ProjectorsVideos;
import ru.kpfu.itis.group11501.smartmuseum.model.Video;
import ru.kpfu.itis.group11501.smartmuseum.repository.ProjectorsVideosRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorService;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorsVideosService;
import ru.kpfu.itis.group11501.smartmuseum.service.VideoService;

import java.util.List;

/**
 * Created by volkov on 20.04.2018.
 */
@Service
public class ProjectorsVideosServiceImpl implements ProjectorsVideosService{

    @Autowired
    private ProjectorsVideosRepository projectorsVideosRepository;

    @Autowired
    private ProjectorService projectorService;

    @Autowired
    private VideoService videoService;


    @Override
    public ProjectorsVideos addProjectorsVideos(ProjectorsVideos projectorsVideos){
        return projectorsVideosRepository.save(projectorsVideos);
    }
    @Override
    public ProjectorsVideos getOneByProjectorIdWhereLastNum(Long projectorId){
        return projectorsVideosRepository.getOneByProjectorIdWhereLastNum(projectorId);
    }

    @Override
    public ProjectorsVideos getOneByProjectorIdByVideoId(Long projectorId, Long videoId){
        return projectorsVideosRepository.getOneByProjectorIdByVideoID(projectorId,videoId);
    }

    @Override
    public List<ProjectorsVideos> getProjectorVideos(Projector projector) {
        return projectorsVideosRepository.findALlByProjector(projector);
    }

    @Override
    public void addProjectorsVideosList(List<String> projectorsId, List<String> videosId) {
        for(String projectorId: projectorsId){
            for (String videoId: videosId){
                if(getOneByProjectorIdByVideoId(Long.decode(projectorId),Long.decode(videoId)) == null){
                    Projector projector = projectorService.getOneById(Long.decode(projectorId));
                    Video video = videoService.findOneById(Long.decode(videoId));
                    Long lastNum =0L;
                    ProjectorsVideos hasLastNum = getOneByProjectorIdWhereLastNum(projector.getId());
                    if (hasLastNum != null) lastNum = hasLastNum.getNum();
                    ProjectorsVideos newProjectorsVideos = new ProjectorsVideos(video,projector,++lastNum);
                    addProjectorsVideos(newProjectorsVideos);
                }
            }
        }
    }

    @Override
    public void deleteByProjectorIdByVideoId(Long id, Long videoId) {
        projectorsVideosRepository.deleteByProjectorIdByVideoId(id, videoId);
    }

    @Override
    public void updateNum(ProjectorsVideos projectorsVideos,Long num) {
        Long currentNum = projectorsVideos.getNum();
        if (currentNum<num){
            projectorsVideosRepository.updateAllBetweenMinus(projectorsVideos.getProjector().getId(),currentNum,num);
        }
        if (currentNum>num){
            projectorsVideosRepository.updateAllBetweenPlus(projectorsVideos.getProjector().getId(),num,currentNum);
        }
        projectorsVideos.setNum(num);
        projectorsVideosRepository.save(projectorsVideos);
    }
}
