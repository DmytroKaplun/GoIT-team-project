package org.spring.demo.goitteamproject.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.spring.demo.goitteamproject.Base62Encoder;
import org.spring.demo.goitteamproject.model.Url;
import org.spring.demo.goitteamproject.model.User;
import org.spring.demo.goitteamproject.repository.UrlRepository;
import org.spring.demo.goitteamproject.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {
    private final UserRepository userRepository;
    private final UrlRepository urlRepository;


    @Transactional
    public String createShortLink(String longUrl) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User authUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username " + username));

        Url url = Url.builder()
                .destination(longUrl)
                .slug("") // Temporary value
                .clickCount(0L)
                .createdAt(LocalDateTime.now())
                .expiredTime(LocalDateTime.now().plusMonths(1))
                .user(authUser)
                .build();

        Url saved = urlRepository.save(url);
        String slugStr = Base62Encoder.encode(saved.getId());
        saved.setSlug(slugStr);
        return "https://localhost:9999/" + slugStr;
    }

    public List<String> findAllActiveUrls(String url) {

        return List.of();
    }

    public String getDestinationLink(String slug) {
        return urlRepository.findDestinationBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Url not found with slug " + slug));
    }

//    @Transactional
//    public String shortenUrl(String longUrl) {
//        // Create a new Url entity
//        Url urlEntity = new Url();
//        urlEntity.setNativeLink(longUrl);
//
//        // Persist the entity to generate the ID
//        urlRepository.save(urlEntity);
//
//        // Generate the short link using the generated ID
//        String shortLink = Base62Encoder.encode(urlEntity.getId());
//        urlEntity.setShortLink(shortLink);
//
//        // Update the entity with the short link
//        urlRepository.save(urlEntity);
//
//        return "https://localhost:9999/" + shortLink;
//    }

    //single table approach
    
//    public String shortenUrl(String longUrl) {
//        // Check if the long URL already exists in the database
//        Url existingUrl = urlRepository.findByNativeLink(longUrl);
//        if (existingUrl != null) {
//            // Return the existing short URL if the long URL is already stored
//            return "https://localhost:9999/" + existingUrl.getShortLink();
//        }
//
//        // Create a new Url entity
//        Url urlEntity = new Url();
//        urlEntity.setNativeLink(longUrl);
//
//        // Save the long URL to the database
//        Url savedUrlEntity = urlRepository.save(urlEntity);
//
//        // Generate a unique short code using the ID
//        String shortCode = Base62Encoder.encode(savedUrlEntity.getId());
//
//        // Set the short code in the entity and update the record
//        savedUrlEntity.setShortLink(shortCode);
//        urlRepository.save(savedUrlEntity);
//
//        // Return the full short URL
//        return "https://localhost:9999/" + shortCode;
//    }


//    public String generateShortCode(String longUrl) {
//        String hash = DigestUtils.md5DigestAsHex(longUrl.getBytes());
//        String randomPart = generateRandomString(3);
//        return hash.substring(0, 3) + randomPart;
//    }
//
//    private String generateRandomString(int length) {
//        //https://short.ly/dnh domen
//        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        StringBuilder randomString = new StringBuilder();
//        Random random = new Random();
//        for (int i = 0; i < length; i++) {
//            randomString.append(characters.charAt(random.nextInt(characters.length())));
//        }
//        return randomString.toString();
//    }
}
