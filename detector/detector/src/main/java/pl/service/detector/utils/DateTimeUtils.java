package pl.service.detector.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtils {

    public static LocalDateTime parseDateWithPattern(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedDate = dateTime.format(DateTimeFormatter.ofPattern(pattern));
        return LocalDateTime.parse(formattedDate, formatter);
    }
}
