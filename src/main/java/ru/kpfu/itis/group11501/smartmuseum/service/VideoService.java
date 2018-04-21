package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.Video;

import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
public interface VideoService {
    List<Video> getAllVideo();

    Video addVideo(Video video);

    Video findOneById(Long videoId);

    Video findOneByName(String name);

    void deleteById(Long videoId);
}
