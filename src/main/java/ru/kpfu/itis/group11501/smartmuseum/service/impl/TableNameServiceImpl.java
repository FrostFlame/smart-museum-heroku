package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.TableName;
import ru.kpfu.itis.group11501.smartmuseum.repository.TableNameRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.TableNameService;

/**
 * Created by volkov on 25.04.2018.
 */
@Service
public class TableNameServiceImpl implements TableNameService {

    @Autowired
    private TableNameRepository tableNameRepository;

    @Override
    public TableName getOneByName(String name){
        return tableNameRepository.getOneByName(name);
    }
}
