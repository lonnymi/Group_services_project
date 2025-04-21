package com.example.groupservice.repository;

import com.example.groupservice.model.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {
    // Tu peux ajouter des méthodes personnalisées ici si besoin
}
