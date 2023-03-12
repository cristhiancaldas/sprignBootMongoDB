package com.mongodb.tutorial.service;

import com.mongodb.tutorial.entity.Tutorial;
import com.mongodb.tutorial.repository.TutorialRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TutorialServiceTest {

    @Mock
    private TutorialRepository tutorialRepository;
    @InjectMocks
    private TutorialService tutorialService;
    @Autowired
    private MongoTemplate mongoTemplate;

    private Tutorial tutorial;

    @BeforeEach
    void setUp() {
        openMocks(this);
        tutorial= tutorial();
    }

   @AfterEach
    void cleanUpDatabase() {
        mongoTemplate.getDb().drop();
    }

    @Test
    void addTutorial() {
        tutorialService.addTutorial(tutorial);
        when(tutorialRepository.save(tutorial)).thenReturn(tutorial);
        verify(tutorialRepository, times(1)).save(tutorial);
    }

    @Test
    void getAllTutorial() {
        tutorialService.getAllTutorial();
        when(tutorialRepository.findAll()).thenReturn(getTutoriales());
        verify(tutorialRepository, times(1)).findAll();
        assertEquals(2,getTutoriales().size());
    }

    @Test
    void getTutorialById() {
        String idTutorial="123ABC";
        tutorialService.getTutorialById(idTutorial);
        when(tutorialRepository.findById(idTutorial)).thenReturn(Optional.of(tutorial()));
        verify(tutorialRepository,times(1)).findById(idTutorial);

    }

    @Test
    void deleteTutorial() {
        String idTutorial="123ABC";
        tutorialService.deleteTutorial(idTutorial);
        doNothing().when(tutorialRepository).deleteById(idTutorial);
        verify(tutorialRepository,times(1)).deleteById(idTutorial);
    }

    private Tutorial tutorial(){
        Tutorial tuto = new Tutorial();
        tuto.setName("Test 01");
        tuto.setDescription("Test de Prueba 01");
        return tuto;
    }

    private List<Tutorial> getTutoriales(){
        List<Tutorial> lstTutorial= new ArrayList<>();
        Tutorial tuto01 = new Tutorial();
        tuto01.setName("Test 01");
        tuto01.setDescription("Test de Prueba 01");

        Tutorial tuto02 = new Tutorial();
        tuto02.setName("Test 01");
        tuto02.setDescription("Test de Prueba 01");

        lstTutorial.add(tuto01);
        lstTutorial.add(tuto02);
        return lstTutorial;
    }
}