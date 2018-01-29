/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.welearn.domain.entities;

import java.util.List;

/**
 *
 * @author weiwei2017
 */
public class Questions {
    private int qId;
    private String description;
    private String hint;
    private int weight;
    private int level;
    private String type;

    
    public Questions(int id, String description, String hint, int weight, int level, String type)
    {
           setqId(id);
           setDescription(description);
           setHint(hint);
           setWeight(weight);
           setLevel(level);
           setType(type);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the hint
     */
    public String getHint() {
        return hint;
    }

    /**
     * @param hint the hint to set
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
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
     * @return the qId
     */
    public int getqId() {
        return qId;
    }

    /**
     * @param qId the qId to set
     */
    public void setqId(int qId) {
        this.qId = qId;
    }

    @Override
    public String toString() {
        return "question id = > " +  qId
                +", description = >" + description
        +", hint = >" + hint
        +", level = >" + level
        +", type = >" + type;
    }
   
  
    
}
