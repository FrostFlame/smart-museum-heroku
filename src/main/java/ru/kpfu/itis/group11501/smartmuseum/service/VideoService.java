package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.Video;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.Action;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.CoherentEntity;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.ActionTypeName;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.EntityName;

import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
@CoherentEntity(name = EntityName.VIDEO)
public interface VideoService {
    List<Video> getAllVideo();

    @Action(name = ActionTypeName.ADD)
    Video addVideo(Video video);

    Video findOneById(Long videoId);

    Video findOneByName(String name);

    @Action(name = ActionTypeName.DELETE)
    void deleteById(Long videoId);
}
