package com.ko.weaverloft.controller;


import com.ko.weaverloft.Service.UserService;
import com.ko.weaverloft.dto.EmailValidateDTO;
import com.ko.weaverloft.dto.UserDTO;
import com.ko.weaverloft.dto.UserParsingDTO;
import com.ko.weaverloft.entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final UserService userService;

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



}
