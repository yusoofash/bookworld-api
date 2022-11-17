package com.bookworld.api.publisher;

import java.util.List;

public interface PublisherService {
    Publisher createPublisher(Publisher publisher);

    Publisher updatePublisher(Long publisherId, Publisher publisher);

    List<Publisher> getAllPublishers();

    void deletePublisher(Long publisherId);
}
