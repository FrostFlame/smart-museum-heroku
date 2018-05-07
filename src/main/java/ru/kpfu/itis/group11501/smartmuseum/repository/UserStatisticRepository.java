package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.group11501.smartmuseum.model.UserStatistic;

import java.util.List;

/**
 * Created by volkov on 25.04.2018.
 */
public interface UserStatisticRepository extends JpaRepository<UserStatistic, Long> {

    @Query("select u from UserStatistic as u "+
            "where u.user.id in ?1 and u.actionType.id in ?2 and u.tableName.id in ?3 "+
            "and (LOWER(concat(u.user.login, ' ', u.datetime)) like LOWER(concat('%', ?4, '%')) )")
    List<UserStatistic> findByParameter(List<Long> users, List<Long> actions, List<Long> entities, String searchField, Pageable pageRequest);

    @Query("select count(u.id) from UserStatistic as u "+
            "where u.user.id in ?1 and u.actionType.id in ?2 and u.tableName.id in ?3 "+
            "and (LOWER(concat(u.user.login, ' ', u.datetime)) like LOWER(concat('%', ?4, '%')) )")
    Long getCountRow(List<Long> users, List<Long> actions, List<Long> entities, String searchField);



}
