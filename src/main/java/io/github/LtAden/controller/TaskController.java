/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.LtAden.controller;

import io.github.LtAden.model.Task;
import java.awt.print.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.LtAden.model.TaskRepository;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Aden
 */

@RestController
public class TaskController {
    
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    
    private final TaskRepository repository;
    
    
    /*since both repo and controller are Spring-controlled, spring knows that 
    repository has to be made first, then injects it here. THats all dude has to 
    do */
    TaskController(final TaskRepository repository){
        this.repository = repository;
    }
    
    /*in either case, it will return JSON through the miracle of Spirng. Return 
    should be whats in brackets after ok. Response Entity allows us to give status
    codes */
    /*Request mapping will send requests to read all tasks method. Here, only get 
    requests will go there, and only if they came from /tasks path. 
    Alternatively, you can use GetMapping instead of Request Mapping*/
        //    @RequestMapping(method = RequestMethod.GET, path="/tasks")
        //    ResponseEntity<?> /*List<Task>*/ readAllTasks(){
        //        logger.warn("Exposing all the tasks");
        //        return ResponseEntity.ok(repository.findAll());
    
    //Same as above, get methods will be directed here, when path is /tasks, and parameters parsed arent sort,page or size
    @GetMapping(value = "/tasks", params={"!sort","!page", "!size"})
    ResponseEntity<?> readAllTasks(){
        logger.warn("Exposing all the tasks");
        return ResponseEntity.ok(repository.findAll());
    }
        
    @GetMapping("/tasks")
    ResponseEntity<?> readAllTasks(Pageable page){
        logger.info("Custome Page");
        return ResponseEntity.ok(repository.findAll());
    }
        
        
   @PutMapping("/tasks/{id}")
   ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody Task toUpdate){
       if(!repository.existsById(id))
           return ResponseEntity.notFound().build();
       
        toUpdate.setID(id);
        repository.save(toUpdate);
        return ResponseEntity.noContent().build();
    }
   
    @GetMapping("/tasks/{id}")
    ResponseEntity<Task> readTask(@PathVariable int id){
        return repository.findById(id).map(task -> ResponseEntity.ok(task)).orElse(ResponseEntity.notFound().build());
        }
    
    @PostMapping("/tasks")
    ResponseEntity<Task> createTask(@RequestBody @Valid Task toCreate){
        Task result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
        }
    }
  