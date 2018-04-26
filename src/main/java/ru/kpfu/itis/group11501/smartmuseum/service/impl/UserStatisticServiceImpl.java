package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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

    public UserStatisticServiceImpl(UserStatisticRepository userStatisticRepository, UserService userService, ActionTypeService actionTypeService, TableNameService tableNameService) {
        this.userStatisticRepository = userStatisticRepository;
        this.userService = userService;
        this.actionTypeService = actionTypeService;
        this.tableNameService = tableNameService;
    }

    @Override
    public UserStatistic addUserStatistic(UserStatistic userStatistic){
        return userStatisticRepository.save(userStatistic);
    }

    @Override
    public List<UserStatistic> findAll() {
        return userStatisticRepository.findAll();
    }

    @Override
    public List<UserStatistic> setRussianNames(List<UserStatistic> userStatistics) {
        for (UserStatistic userStatistic : userStatistics){
            userStatistic.setLink(EntityName.valueOf(userStatistic.getTableName().getName()).getLink());

            ActionTypeName action = ActionTypeName.valueOf(userStatistic.getActionType().getName());
            userStatistic.setActionType(new ActionType(action.getRusName()));

            EntityName entityName = EntityName.valueOf(userStatistic.getTableName().getName());
            userStatistic.setTableName(new TableName(entityName.getRusName()));
        }
        return userStatistics;
    }

    @Override
    public List<UserStatistic> findByParameter(List<Long> users, List<Long> actions, List<Long> entities) {
        if (users == null){
            users = userService.getAllUsers().stream().map(User::getId).collect(Collectors.toList());
        }
        if (actions == null){
            actions= actionTypeService.findAll().stream().map(ActionType::getId).collect(Collectors.toList());
        }
        if (entities == null){
            entities = tableNameService.findAll().stream().map(TableName::getId).collect(Collectors.toList());
        }
        return userStatisticRepository.findByParameter(users,actions,entities);
    }
}
