package com.mongodb.tutorial.controller;

import com.mongodb.tutorial.entity.Tutorial;
import com.mongodb.tutorial.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tutorial/v1")
public class TutorialController {

    @Autowired
    private TutorialRepository tutorialRepository;

    @GetMapping
    public ResponseEntity<List<Tutorial>> getAll() {
        List<Tutorial> tutorials = tutorialRepository.findAll();
        if(tutorials.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(tutorials);
    }

    @PostMapping
    public ResponseEntity<Tutorial> addTutorial(@RequestBody Tutorial tutorial){
        tutorial.setIdTutorial(UUID.randomUUID().toString().split("-")[0]);
        Tutorial tutorialNew= tutorialRepository.save(tutorial);
        return ResponseEntity.ok(tutorialNew);
    }
}
