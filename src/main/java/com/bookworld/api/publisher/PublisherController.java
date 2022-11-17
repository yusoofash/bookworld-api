package com.bookworld.api.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @PostMapping
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        return ResponseEntity.ok(publisherService.createPublisher(publisher));
    }

    @PutMapping("/{publisherId}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long publisherId, @RequestBody Publisher publisher) {
        return ResponseEntity.ok(publisherService.updatePublisher(publisherId, publisher));
    }

    @DeleteMapping("/{publisherId}")
    public HttpStatus deletePublisher(@PathVariable Long publisherId) {
        publisherService.deletePublisher(publisherId);
        return HttpStatus.OK;
    }

    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAllPublishers());
    }

}
