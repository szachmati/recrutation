package pl.service.detector.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class StatisticDto {

    private final LocalDate date;
    private final Long count;
    private final String ipAddress;
}
