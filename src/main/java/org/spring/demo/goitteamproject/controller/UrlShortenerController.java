package org.spring.demo.goitteamproject.controller;

import lombok.RequiredArgsConstructor;
import org.spring.demo.goitteamproject.service.UrlShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UrlShortenerController {
    private final UrlShortenerService urlShortenerService;

//    @GetMapping("/{shortCode}")
//    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
//        String longUrl = urlShortenerService.getLongUrl(shortCode);
//        if (longUrl != null) {
//            return ResponseEntity.status(HttpStatus.FOUND)
//                    .header("Location", longUrl)
//                    .build();
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
}
