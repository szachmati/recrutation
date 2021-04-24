package pl.service.detector.mapper;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import pl.service.detector.dto.StatisticDto;
import pl.service.detector.dto.UserActivityDto;
import pl.service.detector.entity.UserActivity;

import java.time.LocalDate;


@Component
public class UserActivityMapper {

    public UserActivity mapToEntity(UserActivityDto dto) {
        return UserActivity.builder()
                .ipAddress(dto.getIpAddress())
                .visitTime(dto.getVisitTime())
                .build();
    }

    public StatisticDto mapToStatisticDto(Pair<String, LocalDate> pair, Long count) {
        return StatisticDto.builder()
                .count(count)
                .ipAddress(pair.getFirst())
                .date(pair.getSecond())
                .build();
    }
}
