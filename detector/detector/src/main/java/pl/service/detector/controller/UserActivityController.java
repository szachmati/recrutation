package pl.service.detector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.service.detector.dto.StatisticDto;
import pl.service.detector.dto.UserActivityDto;
import pl.service.detector.service.UserActivityService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserActivityController {

    private final UserActivityService userActivityService;

    @PostMapping("/save")
    public void saveUserActivity(@RequestBody UserActivityDto dto) {
        userActivityService.saveActivity(dto);
    }

    @GetMapping("/statistics")
    public List<StatisticDto> getStatistics() {
        return userActivityService.getStatistics();
    }
}
