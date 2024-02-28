package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collections;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {
    @PostMapping(value = "/upload")
    ResponseEntity upload(MultipartFile file) throws Exception {
        String path = "/tmp/" + file.getOriginalFilename();
        if (!file.isEmpty()) {
            file.transferTo(new File(path));
        }

        log.info("upload : {}", path);
        return ResponseEntity.ok(Collections.singletonMap("result", "ok"));
    }
}
