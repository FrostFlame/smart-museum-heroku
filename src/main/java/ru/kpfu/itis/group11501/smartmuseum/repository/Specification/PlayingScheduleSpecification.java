package ru.kpfu.itis.group11501.smartmuseum.repository.Specification;

import org.springframework.data.jpa.domain.Specification;
import ru.kpfu.itis.group11501.smartmuseum.model.PlayingSchedule;

import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by volkov on 12.05.2018.
 */
public class PlayingScheduleSpecification {
    public static Specification<PlayingSchedule> allByProjectorsByWeekDays(final List<Long> projectorsId, final List<Long> weekDaysId) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Predicate p, p2;
            p = criteriaBuilder.conjunction();
            if (projectorsId != null && !projectorsId.isEmpty()) {
                p2 = root.get("projector").get("id").in(projectorsId);
                p = criteriaBuilder.and(p, p2);
            }

            if (weekDaysId != null && !weekDaysId.isEmpty()) {
                p2 = root.get("weekDay").get("id").in(weekDaysId);
                p = criteriaBuilder.and(p, p2);
            }

            return p;
        };
    }
}
