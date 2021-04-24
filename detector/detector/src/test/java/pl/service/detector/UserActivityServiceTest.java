package pl.service.detector;


import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import pl.service.detector.dto.StatisticDto;
import pl.service.detector.dto.UserActivityDto;
import pl.service.detector.entity.UserActivity;
import pl.service.detector.exception.IncompleteUserActivityDataException;
import pl.service.detector.repository.UserActivityRepository;
import pl.service.detector.service.UserActivityService;
import pl.service.detector.utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureEmbeddedDatabase
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserActivityServiceTest {

    private static final String IP_ADDRESS = "192.169.128.13";
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final UserActivityService userActivityService;
    private final UserActivityRepository userActivityRepository;

    @Test
    public void shouldSaveUserActivity() {
        // given
        LocalDateTime visitTime = LocalDateTime.now();
        UserActivityDto dto = UserActivityDto.builder()
                .ipAddress(IP_ADDRESS)
                .visitTime(visitTime)
                .build();
        // when
        userActivityService.saveActivity(dto);
        Optional<UserActivity> optionalUserActivity =
                userActivityRepository.findByIpAddressAndVisitTime(dto.getIpAddress(), dto.getVisitTime());
        UserActivity activity = optionalUserActivity.get();
        // then
        assertNotNull(activity);
        assertEquals(dto.getIpAddress(), activity.getIpAddress());
        assertEquals(DateTimeUtils.parseDateWithPattern(dto.getVisitTime(), DATE_PATTERN),
                DateTimeUtils.parseDateWithPattern(activity.getVisitTime(), DATE_PATTERN));
    }

    @Test
    public void givenEmptyIpAddress_shouldThrowIncompleteUserActivityDataException() {
        // given
        UserActivityDto dto = UserActivityDto.builder()
                .visitTime(LocalDateTime.now())
                .build();

        assertThrows(IncompleteUserActivityDataException.class,
                () -> userActivityService.saveActivity(dto));

    }

    @Test
    public void givenEmptyVisitTime_shouldThrowIncompleteUserActivityDataException() {
        // given
        UserActivityDto dto = UserActivityDto.builder()
                .ipAddress(IP_ADDRESS)
                .build();

        assertThrows(IncompleteUserActivityDataException.class,
                () -> userActivityService.saveActivity(dto));
    }

    @Test
    @Sql(scripts = {"/user_activity.sql"}) // <-- given
    public void shouldReturnStatistics() {
        // when
        List<StatisticDto> statistics = userActivityService.getStatistics();
        // then
        assertTrue(!statistics.isEmpty());
        assertEquals(2, statistics.size());
        assertEquals(2l, statistics.stream().filter(Utils.distinctByKey(StatisticDto::getIpAddress)).count());
    }
}
