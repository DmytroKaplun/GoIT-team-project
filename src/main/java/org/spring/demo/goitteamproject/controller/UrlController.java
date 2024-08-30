package org.spring.demo.goitteamproject.controller;

import lombok.RequiredArgsConstructor;
import org.spring.demo.goitteamproject.model.dto.UrlRequest;
import org.spring.demo.goitteamproject.service.UrlShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/create")
@RequiredArgsConstructor
public class UrlController {
    private final UrlShortenerService urlShortenerService;

    @PostMapping()
    public ResponseEntity<String> longUrl(@RequestBody UrlRequest request) {
        String shortenUrl = urlShortenerService.createShortLink(request.getLongUrl());
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.TEXT_PLAIN)
                .body(shortenUrl);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<String> redirect(@PathVariable("slug") String slug) {
        urlShortenerService.getDestinationLink(slug);
        return null;
    }
}
