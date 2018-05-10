package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.ActionType;
import ru.kpfu.itis.group11501.smartmuseum.model.TableName;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.model.UserStatistic;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.ActionTypeName;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.EntityName;
import ru.kpfu.itis.group11501.smartmuseum.repository.UserStatisticRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.ActionTypeService;
import ru.kpfu.itis.group11501.smartmuseum.service.TableNameService;
import ru.kpfu.itis.group11501.smartmuseum.service.UserService;
import ru.kpfu.itis.group11501.smartmuseum.service.UserStatisticService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by volkov on 25.04.2018.
 */
@Service
public class UserStatisticServiceImpl implements UserStatisticService {


    private UserStatisticRepository userStatisticRepository;
    private UserService userService;
    private ActionTypeService actionTypeService;
    private TableNameService tableNameService;
    private static int size = 15;

    public UserStatisticServiceImpl(UserStatisticRepository userStatisticRepository, UserService userService, ActionTypeService actionTypeService, TableNameService tableNameService) {
        this.userStatisticRepository = userStatisticRepository;
        this.userService = userService;
        this.actionTypeService = actionTypeService;
        this.tableNameService = tableNameService;
    }

    @Override
    public UserStatistic addUserStatistic(UserStatistic userStatistic) {
        return userStatisticRepository.save(userStatistic);
    }


    @Override
    public List<UserStatistic> setRussianNames(List<UserStatistic> userStatistics) {
        for (UserStatistic userStatistic : userStatistics) {
            userStatistic.setLink(EntityName.valueOf(userStatistic.getTableName().getName()).getLink());

            ActionTypeName action = ActionTypeName.valueOf(userStatistic.getActionType().getName());
            userStatistic.setActionType(new ActionType(action.getRusName()));

            EntityName entityName = EntityName.valueOf(userStatistic.getTableName().getName());
            userStatistic.setTableName(new TableName(entityName.getRusName()));
        }
        return userStatistics;
    }

    @Override
    public List<UserStatistic> findByParameter(List<Long> users, List<Long> actions, List<Long> entities, String searchField, Long page) {
        if (users == null) {
            users = userService.getAllUsers().stream().map(User::getId).collect(Collectors.toList());
        }
        if (actions == null) {
            actions = actionTypeService.findAll().stream().map(ActionType::getId).collect(Collectors.toList());
        }
        if (entities == null) {
            entities = tableNameService.findAll().stream().map(TableName::getId).collect(Collectors.toList());
        }
        if (searchField == null) {
            searchField = "";
        }

        Pageable pageRequest = new PageRequest(page.intValue(), size);
        return userStatisticRepository.findByParameter(users, actions, entities, searchField, pageRequest);
    }

    @Override
    public Long getLastPage(List<Long> users, List<Long> actions, List<Long> entities, String searchField) {
        if (users == null) {
            users = userService.getAllUsers().stream().map(User::getId).collect(Collectors.toList());
        }
        if (actions == null) {
            actions = actionTypeService.findAll().stream().map(ActionType::getId).collect(Collectors.toList());
        }
        if (entities == null) {
            entities = tableNameService.findAll().stream().map(TableName::getId).collect(Collectors.toList());
        }
        if (searchField == null) {
            searchField = "";
        }
        Long rows = userStatisticRepository.getCountRow(users, actions, entities, searchField);
        if (rows % size == 0) return rows / size - 1;
        else return rows / size;
    }

}
