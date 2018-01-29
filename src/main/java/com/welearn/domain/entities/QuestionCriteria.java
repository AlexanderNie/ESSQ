/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.welearn.domain.entities;

/**
 *
 * @author Alexander
 */
public class QuestionCriteria {
    private String type;
    private Double level;
    
    public QuestionCriteria(String type, Double level)
    {
        setType(type);
        setLevel(level);
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the level
     */
    public Double getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Double level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "crietrion type=> " +  type + "  level =>" + level;
    }
    
    
}
