package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.ActionType;
import ru.kpfu.itis.group11501.smartmuseum.repository.ActionTypeRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.ActionTypeService;

import java.util.List;

/**
 * Created by volkov on 25.04.2018.
 */
@Service
public class ActionTypeServiceImpl implements ActionTypeService {

    @Autowired
    private ActionTypeRepository actionTypeRepository;

    @Override
    public ActionType getOneByName(String name){
        return  actionTypeRepository.findOneByName(name);
    }

    @Override
    public List<ActionType> findAll(){
        return actionTypeRepository.findAll();
    }
}
