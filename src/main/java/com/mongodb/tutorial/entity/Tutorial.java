package com.mongodb.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tutorial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tutorial {
   @Id
   private String idTutorial;
   private String name;
   private String description;
}
