package com.example.groupservice.service;

import com.example.groupservice.model.Group;
import com.example.groupservice.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    // Création d'un groupe
    public Group createGroup(Group group) {
        // Sauvegarde le groupe dans la base de données MongoDB
        return groupRepository.save(group);
    }

    // Récupère tous les groupes
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    // Récupère un groupe par son ID
    public Group getGroupById(String id) {
        // Retourne le groupe si trouvé, sinon retourne null
        Optional<Group> group = groupRepository.findById(id);
        return group.orElse(null);
    }
}
