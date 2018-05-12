package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.group11501.smartmuseum.model.UserStatistic;
import ru.kpfu.itis.group11501.smartmuseum.repository.Specification.UserStatisticSpecification;

import java.util.List;

/**
 * Created by volkov on 25.04.2018.
 */
public interface UserStatisticRepository extends JpaRepository<UserStatistic, Long>, JpaSpecificationExecutor<UserStatistic> {


    default List<UserStatistic> findByParameter(List<Long> users, List<Long> actions, List<Long> entities, String searchField, Pageable pageRequest) {
        return (findAll(UserStatisticSpecification.allByParameter(users, actions, entities, searchField), pageRequest)).getContent();
    }

    default Long getCountRow(List<Long> users, List<Long> actions, List<Long> entities, String searchField) {
        return (count(UserStatisticSpecification.allByParameter(users, actions, entities, searchField)));
    }


}
