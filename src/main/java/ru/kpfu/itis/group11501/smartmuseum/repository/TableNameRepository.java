package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.smartmuseum.model.TableName;

/**
 * Created by volkov on 25.04.2018.
 */
public interface TableNameRepository extends JpaRepository<TableName, Long> {

    TableName findOneByName(String name);
}
