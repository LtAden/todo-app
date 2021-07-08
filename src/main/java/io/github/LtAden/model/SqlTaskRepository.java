/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.LtAden.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aden
 */

//path will change address where resources will be, defaultly, address is key in plural, here tasks
//collectionResourceRel will change collection elements names
@Repository //(path = "todos", collectionResourceRel = "todos")
interface SqlTaskRepository extends TaskRepository, JpaRepository<Task, Integer>{
    
    //these two block delete functions from being inherited, making deletes impossible
//    @Override
//    @RestResource(exported = false)
//    void deleteById(Integer interger);
//    
//    @Override
//    @RestResource(exported = false)
//    void delete(Task task);
    
//    this is something called DSL. this is a valid function that will filter 
//    tasks dy done = true 
//    @RestResource(path = "done", rel = "done")
//    List<Task> findByDoneIsTrue();
    
    
    /* this will make it necessary to say within address bar whether we want
    tasks that are done or not. sending get command for non-done tasks will be
    <address>/done?state=false; here: "localhost:8080/tasks/search/done?state=false"*/
//    @RestResource(path = "done", rel = "done")
    
    
}
