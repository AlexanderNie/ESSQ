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
public class QuizResult {
    private String type;
    private Integer level;
    private boolean pass;

    public QuizResult(String type, Integer level, boolean pass)
    {
        setType(type);
        setLevel(level);
        setPass(pass);
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
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return the pass
     */
    public boolean isPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(boolean pass) {
        this.pass = pass;
    }
}
