package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.UserStatistic;

import java.util.List;

/**
 * Created by volkov on 25.04.2018.
 */
public interface UserStatisticService {

    UserStatistic addUserStatistic(UserStatistic userStatistic);


    List<UserStatistic> setRussianNames(List<UserStatistic> userStatistics);

    List<UserStatistic> findByParameter(List<Long> users, List<Long> actions, List<Long> entities, String searchField, Long page);

    Long getLastPage(List<Long> users, List<Long> actions, List<Long> entities, String searchField);
}
