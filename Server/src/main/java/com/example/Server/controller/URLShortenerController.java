package com.example.Server.controller;


import com.example.Server.entity.Url;
import com.example.Server.service.UrlService;
import com.example.Server.utility.Encoder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashSet;

@RestController
@RequestMapping("api/")
@CrossOrigin(origins = "*")
public class URLShortenerController {

    //fixed value for the domain
    private final static String CURRENT_DOMAIN = "http://localhost:8080/api/";
    private int keyLength = 8;

    private final UrlService urlService;
    private final Encoder encoder;

    private HashSet<String> keys;

    @Autowired
    public URLShortenerController(UrlService urlService, Encoder encoder) {
        this.urlService = urlService;
        this.encoder = encoder;
    }


    @PostConstruct
    private void getAllKeys(){
        keys = new HashSet<>(urlService.getAllKeys());
    }


    /**
     * Shorten the provided URL
     * @param fullUrl the long url provided by the user
     * @return Http Response containing new Json file holding the (full url, short url, and the key)
     */
    @PostMapping("/url")
    public ResponseEntity<Url> shortenUrl(@RequestBody String fullUrl){

        //Check if the URL is already shortened
        Url url = urlService.getUrlInfoWithFullUrl(fullUrl);
        if(url != null)
            return new ResponseEntity<>(url, HttpStatus.OK);

        //Encode the input URL
        String key = encoder.hashUrl(fullUrl, keyLength);

        //Avoid key collision by checking if the key already exist
        while(keys.contains(key)){
            keyLength+= 2;
            key = encoder.hashUrl(fullUrl, keyLength);
        }
        keys.add(key);

        String shortUrl = CURRENT_DOMAIN + key;
        Url tempUrl = new Url(key, fullUrl, shortUrl);
        urlService.saveUrl(tempUrl);

        //return OK response with the new created URL
        return new ResponseEntity<>(tempUrl, HttpStatus.CREATED);
    }

    /**
     * Redirects to the original URL using the short URL key.
     * @param key the key represents the short url
     * @return a RedirectionView to the original full URL
     */
    @GetMapping("/{key}")
    public ResponseEntity<RedirectView> redirect(@PathVariable String key){
        Url originalUrl = urlService.findById(key);

        if(originalUrl != null)
            return new ResponseEntity<>(new RedirectView(originalUrl.getFullUrl()), HttpStatus.FOUND);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<String> deleteUrl(@PathVariable String key){
        if(keys.contains(key) || urlService.findById(key) != null){
            urlService.deleteUrl(key);
            keys.remove(key);
            return new ResponseEntity<>("Url is deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("URL not found", HttpStatus.NOT_FOUND);
    }
}
