package com.example.Server.repository;

import com.example.Server.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UrlRepository extends JpaRepository<Url, String> {

    Url findByFullUrl(String fullUrl);

    @Query(value = "SELECT url_key FROM url;", nativeQuery = true)
    List<String> findAllUrlKeys();
}

