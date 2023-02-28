package com.mongodb.tutorial.controller;

import com.mongodb.tutorial.entity.Tutorial;
import com.mongodb.tutorial.service.TutorialService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tutorial/v1")
public class TutorialController {

    @Autowired
    TutorialService tutorialService;

    @Operation(summary = "Get all Tutorial")
    @GetMapping
    public ResponseEntity<List<Tutorial>> getAll() {
        List<Tutorial> tutorials = tutorialService.getAllTutorial();
        if (tutorials.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(tutorials);
    }

    @Operation(summary = "Add new Tutorial")
    @PostMapping
    public ResponseEntity<Tutorial> addTutorial(@RequestBody Tutorial tutorial) {
        Tutorial tutorialNew = tutorialService.addTutorial(tutorial);
        return ResponseEntity.ok(tutorialNew);
    }

    @Operation(summary = "Get Tutorial by ID")
    @GetMapping("/{idTutorial}")
    public ResponseEntity<Tutorial> getTutorialByID(@PathVariable String idTutorial) {
        Optional<Tutorial> tutorial = tutorialService.getTutorialById(idTutorial);
        if (tutorial.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(tutorial.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{idTutorial}")
    public ResponseEntity<HttpStatus> deleteTutorialByID(@PathVariable String idTutorial){
        tutorialService.deleteTutorial(idTutorial);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
