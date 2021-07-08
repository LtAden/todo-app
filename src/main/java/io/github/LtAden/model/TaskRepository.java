/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.LtAden.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Aden
 */

public interface TaskRepository {
    List<Task> findAll();
    
    Optional<Task> findById(Integer Id); // Optional returns list, but opional handles case of not finding it
    
    Task save(Task entity);
    
    boolean existsById(Integer id);
    
    Page<Task> findAll(Pageable page);
    
   List<Task> findByDone(@Param("state") boolean done);
}
