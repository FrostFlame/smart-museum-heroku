package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.Video;
import ru.kpfu.itis.group11501.smartmuseum.repository.VideoRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.VideoService;

import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
@Service
public class VideoServiceImpl implements VideoService {


    private VideoRepository videoRepository;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public List<Video> getAllVideo() {
        return videoRepository.findAll();
    }

    @Override
    public Video addVideo(Video video) {
        return videoRepository.save(video);
    }
}
