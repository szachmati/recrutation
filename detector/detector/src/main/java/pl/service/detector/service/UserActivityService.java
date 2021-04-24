package pl.service.detector.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import pl.service.detector.dto.StatisticDto;
import pl.service.detector.dto.UserActivityDto;
import pl.service.detector.exception.IncompleteUserActivityDataException;
import pl.service.detector.mapper.UserActivityMapper;
import pl.service.detector.repository.UserActivityRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserActivityService {

    private final UserActivityRepository userActivityRepository;
    private final UserActivityMapper userActivityMapper;

    public void saveActivity(UserActivityDto dto) {
        if (dto.getIpAddress() == null || dto.getVisitTime() == null) {
            throw new IncompleteUserActivityDataException("Ip address or visit time are empty!");
        }
        userActivityRepository.save(userActivityMapper.mapToEntity(dto));
    }

    public List<StatisticDto> getStatistics() {
        Map<Pair<String, LocalDate>, Long> statisticsMap = userActivityRepository.findAll().stream().collect(
                Collectors.groupingBy(userActivity -> Pair.of(userActivity.getIpAddress(), userActivity.getVisitTime().toLocalDate()),
                        Collectors.counting()));

        return statisticsMap.entrySet().stream()
                .map(entry -> userActivityMapper.mapToStatisticDto(entry.getKey().getSecond(), entry.getValue()))
                .collect(Collectors.toList());


    }
}
