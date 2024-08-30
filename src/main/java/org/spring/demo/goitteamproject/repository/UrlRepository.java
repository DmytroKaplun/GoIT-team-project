package org.spring.demo.goitteamproject.repository;

import org.spring.demo.goitteamproject.model.Url;
import org.spring.demo.goitteamproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {

    @Query("SELECT u.destination FROM Url u WHERE u.slug = :slug")
    Optional<String> findDestinationBySlug(@Param("slug") String slug);

    @Query("SELECT u.slug FROM Url u WHERE u.user = :user")
    Optional<List<String>> findAllSlugsByUserId(@Param("user") User user);

    Optional<Url> findBySlug(String slug);

}
