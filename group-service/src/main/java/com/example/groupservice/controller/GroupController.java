package com.example.groupservice.controller;

import com.example.groupservice.model.Group;
import com.example.groupservice.model.Task;
import com.example.groupservice.repository.TaskRepository;
import com.example.groupservice.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = "*")
public class GroupController {

    private final GroupService groupService;
    private final TaskRepository taskRepository;

    @Autowired
    public GroupController(GroupService groupService, TaskRepository taskRepository) {
        this.groupService = groupService;
        this.taskRepository = taskRepository;
    }

    // Group endpoints
    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        try {
            List<Group> groups = groupService.getAllGroups();
            return ResponseEntity.ok(groups);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Group group = groupService.getGroupById(id);
            if (group != null) {
                return ResponseEntity.ok(group);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        if (group == null || group.getName() == null || group.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Group createdGroup = groupService.createGroup(group);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Task endpoints
    @PostMapping("/{groupId}/tasks")
    public ResponseEntity<Task> addTask(@PathVariable String groupId, @RequestBody Task task) {
        if (groupId == null || task == null || task.getDescription() == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            task.setGroupId(groupId);
            Task savedTask = taskRepository.save(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{groupId}/tasks")
    public ResponseEntity<List<Task>> getGroupTasks(@PathVariable String groupId) {
        if (groupId == null || groupId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            List<Task> tasks = taskRepository.findByGroupId(groupId);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{groupId}/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String groupId, @PathVariable String taskId) {
        if (groupId == null || taskId == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Task task = taskRepository.findById(taskId).orElse(null);
            if (task != null && task.getGroupId().equals(groupId)) {
                taskRepository.deleteById(taskId);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{groupId}/tasks/{taskId}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable String groupId, @PathVariable String taskId) {
        if (groupId == null || taskId == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            // First check if group exists
            Group group = groupService.getGroupById(groupId);
            if (group == null) {
                return ResponseEntity.notFound().build();
            }

            // Then check if task exists and belongs to the group
            Task task = taskRepository.findById(taskId).orElse(null);
            if (task == null || !task.getGroupId().equals(groupId)) {
                return ResponseEntity.notFound().build();
            }

            // Update task
            task.setCompleted(true);
            Task updatedTask = taskRepository.save(task);
            return ResponseEntity.ok(updatedTask);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}