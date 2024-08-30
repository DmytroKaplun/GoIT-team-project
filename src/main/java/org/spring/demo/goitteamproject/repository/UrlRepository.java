package org.spring.demo.goitteamproject.repository;

import org.spring.demo.goitteamproject.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {

    @Query("SELECT u.destination FROM Url u WHERE u.slug = :slug")
    Optional<String> findDestinationBySlug(String url);
}
