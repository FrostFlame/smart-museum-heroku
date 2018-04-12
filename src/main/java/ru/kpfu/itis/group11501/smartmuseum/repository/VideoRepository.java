package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.smartmuseum.model.Video;

/**
 * Created by volkov on 12.04.2018.
 */
public interface VideoRepository  extends JpaRepository<Video, Long> {
}
