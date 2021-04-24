package pl.service.detector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.service.detector.entity.UserActivity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    Optional<UserActivity> findByIpAddressAndVisitTime(String ipAddress, LocalDateTime visitTime);
}
