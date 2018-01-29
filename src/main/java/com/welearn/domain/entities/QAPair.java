/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.welearn.domain.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Alexander
 */
public class QAPair {
    private Questions question;
    private List<Answers> anwers = new ArrayList<Answers>();

    public QAPair(Questions q, List<Answers> answers)
    {
        setQuestion(q);
        Iterator<Answers> iterAns = answers.iterator();
        while(iterAns.hasNext())
        {
            this.anwers.add(iterAns.next());
        }
    }
    /**
     * @return the question
     */
    public Questions getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(Questions question) {
        this.question = question;
    }

    /**
     * @return the anwers
     */
    public List<Answers> getAnwers() {
        return anwers;
    }

    /**
     * @param anwers the anwers to set
     */
    public void setAnwers(List<Answers> anwers) {
        this.anwers = anwers;
    }

    @Override
    public String toString() {
        String s = "qid  =>" + question.getqId() +"\n" +
                "description  =>" + question.getDescription() + "\n";
        Iterator<Answers> iterAns = anwers.iterator();
        while(iterAns.hasNext())
        {
            Answers ans = iterAns.next();
            s += "ans id =>" + ans.getId() + "\n" + "ans desc =>" + ans.getDescription() + "\n" ;
        }
        
        return s;
    }
    
}
