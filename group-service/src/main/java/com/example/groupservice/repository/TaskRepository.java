package com.example.groupservice.repository;

import com.example.groupservice.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByGroupId(String groupId);
}