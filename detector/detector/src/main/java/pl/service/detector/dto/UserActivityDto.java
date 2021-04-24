package pl.service.detector.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserActivityDto {

    private final String ipAddress;
    private final LocalDateTime visitTime;
}
