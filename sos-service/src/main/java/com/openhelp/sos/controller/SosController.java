package com.openhelp.sos.controller;

import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
public class SosController {

    @GetMapping(path = "/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }
}
