package com.bookworld.api.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    @Override
    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher updatePublisher(Long publisherId, Publisher publisher) {
        Optional<Publisher> existingPublisher = publisherRepository.findById(publisherId);

        if (existingPublisher.isPresent()) {
            Publisher publisherUpdate = existingPublisher.get();
            publisherUpdate.setName(publisher.getName());

            return publisherRepository.save(publisherUpdate);
        }

        return null;
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public void deletePublisher(Long publisherId) {
        publisherRepository.deleteById(publisherId);
    }
}
