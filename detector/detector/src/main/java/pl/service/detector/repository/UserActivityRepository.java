package pl.service.detector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.service.detector.entity.UserActivity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    Optional<UserActivity> findByIpAddressAndVisitTime(String ipAddress, LocalDateTime visitTime);

    @Query("SELECT u.ipAddress, u.visitTime, count(u) from UserActivity u " +
            "GROUP BY u.ipAddress, u.visitTime")
    List<Object[]> findAllGroupByIpAddressAndVisitDate();
}
