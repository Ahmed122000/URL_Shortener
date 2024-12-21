package com.example.Server.repository;

import com.example.Server.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UrlRepository extends JpaRepository<Url, String> {
    @Query(value = "SELECT * FROM url WHERE short_url = :shortUrl", nativeQuery = true)
    Url getOriginalUrl(@Param("shortUrl") String shortUrl);

    @Query(value = "SELECT full_url FROM url WHERE full_url = :fullUrl", nativeQuery = true)
    String findFullUrl(@Param("fullUrl") String fullUrl);

}

