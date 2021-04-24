package pl.service.detector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.service.detector.dto.UserActivityDto;
import pl.service.detector.service.UserActivityService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserActivityController {

    private final UserActivityService userActivityService;

    @PostMapping("/save")
    public void saveUserActivity(@RequestBody UserActivityDto dto) {
        userActivityService.saveActivity(dto);
    }
}
