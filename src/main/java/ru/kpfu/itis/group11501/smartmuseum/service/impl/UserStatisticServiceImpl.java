package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.UserStatistic;
import ru.kpfu.itis.group11501.smartmuseum.repository.UserStatisticRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.UserStatisticService;

/**
 * Created by volkov on 25.04.2018.
 */
@Service
public class UserStatisticServiceImpl implements UserStatisticService {

    @Autowired
    private UserStatisticRepository userStatisticRepository;

    @Override
    public UserStatistic addUserStatistic(UserStatistic userStatistic){
        return userStatisticRepository.save(userStatistic);
    }
}
