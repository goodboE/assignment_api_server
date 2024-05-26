package com.ko.weaverloft.controller;


import com.ko.weaverloft.Service.DownloadService;
import com.ko.weaverloft.Service.ScrapingService;
import com.ko.weaverloft.Service.UserService;
import com.ko.weaverloft.dto.*;
import com.ko.weaverloft.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final UserService userService;
    private final DownloadService downloadService;
    private final ScrapingService scrapingService;

    /**
     * [Group 1-1] 1. REST API 호출 및 응답
     */
    @GetMapping("/api/{userId}")
    public ResponseEntity getUser(@PathVariable(name = "userId") Long userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(new UserDTO(user.getId(), user.getTitle()));
    }

    /**
     * [Group 1-2] 3. 사용자 입력 검증
     */
    @PostMapping("/api/validate")
    public ResponseEntity validateEmail(@Validated @RequestBody EmailValidateDTO emailValidateDTO, Errors errors) {
        if (errors.hasErrors()) return new ResponseEntity("invalid email", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok("Valid email");
    }

    /**
     * [Group 1-3] 6. JSON 파싱 및 객체 매핑
     */
    @PostMapping("/api/add")
    public String addUser(@RequestBody UserParsingDTO userParsingDTO) {
        User user = userService.save(userParsingDTO.getName(), userParsingDTO.getAge(), userParsingDTO.getCity());
        return "Name: " + user.getName() + ", " + "Age: " + user.getAge();
    }

    /**
     * [Group 2-1] 1. 멀티스레딩을 이용한 파일 다운로드
     */
    @PostMapping("/api/download")
    public ResponseEntity downloadFiles(@RequestBody DownloadFilesDTO downloadFilesDTO) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(downloadService.downloadStart(downloadFilesDTO));
    }

    /**
     * [Group 2-2] 3. 웹 페이지 스크래핑 및 데이터 추출
     */
    @GetMapping("/api/scraping")
    public ResponseEntity extractLinks(@RequestBody UrlDTO urlDTO) {
        return ResponseEntity.ok(scrapingService.extractLinks(urlDTO.getUrl()));
    }

}
