package ru.kpfu.itis.group11501.smartmuseum.repository.Specification;

import org.springframework.data.jpa.domain.Specification;
import ru.kpfu.itis.group11501.smartmuseum.model.UserStatistic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by volkov on 12.05.2018.
 */
public class UserStatisticSpecification {
    public static Specification<UserStatistic> allByParameter(List<Long> users, List<Long> actions, List<Long> entities, String searchField) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Predicate p, p2;
            p = criteriaBuilder.conjunction();
            System.out.println(p.toString());
            if (users != null && !users.isEmpty()) {
                p2 = root.get("user").get("id").in(users);
                p = criteriaBuilder.and(p, p2);
            }

            if (actions != null && !actions.isEmpty()) {
                p2 = root.get("actionType").get("id").in(actions);
                p = criteriaBuilder.and(p, p2);
            }

            if (entities != null && !entities.isEmpty()) {
                p2 = root.get("tableName").get("id").in(entities);
                p = criteriaBuilder.and(p, p2);
            }

            if (searchField != null) {
                Predicate login = criteriaBuilder.like(criteriaBuilder.lower(root.get("user").get("login")), "%" + searchField.toLowerCase() + "%");
                Predicate datetime = criteriaBuilder.like(criteriaBuilder.lower(criteriaBuilder.concat(root.get("datetime"), "")), "%" + searchField.toLowerCase() + "%");
                p2 = criteriaBuilder.or(login, datetime);
                p = criteriaBuilder.and(p, p2);
            }
            return p;
        };
    }
}