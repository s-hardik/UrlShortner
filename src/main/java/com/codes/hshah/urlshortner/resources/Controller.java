package com.codes.hshah.urlshortner.resources;

import com.codes.hshah.urlshortner.manager.UrlManager;
import com.codes.hshah.urlshortner.model.ShortUrlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @Autowired
   private UrlManager urlManager;

    @PostMapping("/shortenUrl")
    ResponseEntity createShortUrl(@RequestBody ShortUrlRequest shortUrlRequest) {
       return ResponseEntity.ok(urlManager.createShortUrl(shortUrlRequest));

    }


    @GetMapping("/url/{encr_id}")
    ResponseEntity getUrl(@PathVariable("encr_id") String encrp_id) throws Exception {
        return ResponseEntity.ok(urlManager.getUrl(encrp_id));

    }
}