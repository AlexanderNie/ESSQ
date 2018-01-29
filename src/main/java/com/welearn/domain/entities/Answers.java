/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.welearn.domain.entities;

/**
 *
 * @author weiwei2017
 */
public class Answers implements Comparable {
    private int id;
    private Integer qId;
    private String description;
    private boolean right;

    
    public Answers(int id, int qid, String description, boolean right)
    {
        setId(id);
        setqId(qid);
        setDescription(description);
        setRight(right);
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the qId
     */
    public Integer getqId() {
        return qId;
    }

    /**
     * @param qId the qId to set
     */
    public void setqId(Integer qId) {
        this.qId = qId;
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
     * @return the right
     */
    public boolean isRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "id = > " + id + "questions => "+ qId + " description = >" + description + " is right? "+ right;
    }

    @Override
    public int compareTo(Object t) {
        Answers a = (Answers)t;
        return getqId().compareTo(a.getqId());
    }
    
    
}
