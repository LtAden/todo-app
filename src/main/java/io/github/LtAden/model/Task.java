/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.LtAden.model;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 *
 * @author Aden
 * TO-DO app
 */

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "its blank, dummy")
    private String description;
    private boolean done;
    
    Task(){
        
    }
    
    //-ets
    public void setID(final int i){
        this.id = i;
    }
    public int getId(){
        return this.id;
    }
    
    public void setDescription(String s){
        this.description = s;
    }
    public String getDescription(){
        return this.description;
    }
    
    public void setDone(boolean d){
        this.done = d;
    }
    public boolean getDone(){
        return this.done;
    }
    
    
}
