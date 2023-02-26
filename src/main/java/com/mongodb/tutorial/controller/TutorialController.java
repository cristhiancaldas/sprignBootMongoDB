package com.mongodb.tutorial.controller;

import com.mongodb.tutorial.entity.Tutorial;
import com.mongodb.tutorial.repository.TutorialRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tutorial/v1")
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    @Operation(summary = "Get all Tutorial")
    @GetMapping
    public ResponseEntity<List<Tutorial>> getAll() {
        List<Tutorial> tutorials = tutorialRepository.findAll();
        if(tutorials.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(tutorials);
    }

    @Operation(summary = "Add new Tutorial")
    @PostMapping
    public ResponseEntity<Tutorial> addTutorial(@RequestBody Tutorial tutorial){
        tutorial.setIdTutorial(UUID.randomUUID().toString().split("-")[0]);
        Tutorial tutorialNew= tutorialRepository.save(tutorial);
        return ResponseEntity.ok(tutorialNew);
    }
}
