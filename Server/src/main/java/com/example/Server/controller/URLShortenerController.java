package com.example.Server.controller;


import com.example.Server.entity.Url;
import com.example.Server.service.UrlService;
import com.example.Server.utility.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("api/")
public class URLShortenerController {

    UrlService urlService;
    Encoder encoder;

    @Autowired
    public URLShortenerController(UrlService urlService, Encoder encoder) {
        this.urlService = urlService;
        this.encoder = encoder;
    }


    @PostMapping("/url")
    public ResponseEntity<String> shortenUrl(@RequestBody String fullUrl){
        Url url = urlService.getUrl(fullUrl);
        if(url != null)
            return new ResponseEntity<>("localhost:8080/api/"+url.getShortUrl(), HttpStatus.OK);

        System.out.println("starting from beginning");
        String shortUrl = encoder.hashUrl(fullUrl, 8);
        Url tempUrl = new Url(fullUrl, shortUrl);
        urlService.saveUrl(tempUrl);
        return new ResponseEntity<>("localhost:8080/api/"+shortUrl, HttpStatus.OK);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirect(@PathVariable String shortUrl){
        Url originalUrl = urlService.getOriginalUrl(shortUrl);
        return new RedirectView(originalUrl.getRealUrl());
    }

}
