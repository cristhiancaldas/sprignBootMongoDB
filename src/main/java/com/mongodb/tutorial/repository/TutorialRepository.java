package com.mongodb.tutorial.repository;

import com.mongodb.tutorial.entity.Tutorial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends MongoRepository<Tutorial,String> {

}
