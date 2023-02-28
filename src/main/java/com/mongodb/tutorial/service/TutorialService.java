package com.mongodb.tutorial.service;

import com.mongodb.tutorial.entity.Tutorial;
import com.mongodb.tutorial.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;

    public Tutorial addTutorial(Tutorial tutorial){
        tutorial.setIdTutorial(UUID.randomUUID().toString().split("-")[0]);
        return tutorialRepository.save(tutorial);
    }

    public List<Tutorial> getAllTutorial(){
        return tutorialRepository.findAll();
    }

    public Optional<Tutorial> getTutorialById(String idTutorial){
        return tutorialRepository.findById(idTutorial);
    }

    public void deleteTutorial(String idTutorial){
        tutorialRepository.deleteById(idTutorial);
    }
}
