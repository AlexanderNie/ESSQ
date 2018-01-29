/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.welearn.domain.service;

import com.welearn.domain.entities.QAPair;
import com.welearn.domain.entities.QuestionCriteria;
import com.welearn.domain.entities.QuizResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Alexander
 */
public class SmartAgentService {
    
    // challenge mode says if mistake happen, priority will be give to same type same difficulty leve questions
    public static final String CHALLENGE_MODE = "challenge";
    // guidance mode says if mistake happen, priority will be give to same type less difficulty leve questions 
    // or other type of questions 
    public static final String GUIDANCE_MODE = "guide";
    
    private List<QuizResult> progress = new ArrayList<QuizResult>();
    private Map<String, Double> statstic = new HashMap<String, Double>();
    
    private QuestionLibService libservce = new QuestionLibService();
    
    
    
    public SmartAgentService()
    {
        libservce.load();
    }
    
    public void prepareTestProgress()
    {
        QuizResult qr1 = new QuizResult("math", 1, true);
        QuizResult qr2 = new QuizResult("math", 1, false);
        QuizResult qr3 = new QuizResult("math", 1, false);
        QuizResult qr4 = new QuizResult("math", 2, false);
        QuizResult qr5 = new QuizResult("math", 2, true);
        QuizResult qr6 = new QuizResult("math", 2, false);
        QuizResult qr7 = new QuizResult("math", 3, true);
        QuizResult qr8 = new QuizResult("math", 3, true);
        QuizResult qr9 = new QuizResult("math", 3, true);
        QuizResult qr10 = new QuizResult("math", 3, false);
        
        progress.add(qr1);
        progress.add(qr2);
        progress.add(qr3);
        progress.add(qr4);
        progress.add(qr5);
        progress.add(qr6);
        progress.add(qr7);
        progress.add(qr8);
        progress.add(qr9);
        progress.add(qr10);
        
    }
    
    public void updateProgress(QuizResult result)
    {
        progress.add(result);
    }
    
    private QuestionCriteria generateCriteriaByProgress()
    {
        System.out.println("============generating criterion by past progress ============");
        calculateProgress();
        return getWeakesSubject();
    }
    
    public QAPair getNextQuestion()
    {
        System.out.println("============generating question by criterion ============");
        QuestionCriteria qc = generateCriteriaByProgress();
        System.out.println(qc);
        return libservce.getQAPairByQuestionCriteria(qc);
    }

    private void calculateProgress() {
        Iterator<QuizResult>  itProg = progress.iterator();
        Map<String, Integer> quizCount = new HashMap<String, Integer>();
        while(itProg.hasNext())
        {
            QuizResult qr = itProg.next();
            // subject and level key
            String key = qr.getType()+"-"+qr.getLevel();
            quizCount.put(key, (quizCount.get(key)==null? 0 :quizCount.get(key)) +1 );
            statstic.put(key, (statstic.get(key) ==null? 0 :statstic.get(key)) + (qr.isPass()? 1:0));
        }
        for( Map.Entry<String, Integer> entryCount : quizCount.entrySet())
        {
            statstic.put(entryCount.getKey(), statstic.get(entryCount.getKey())/entryCount.getValue() );
        }
    }
    
    private QuestionCriteria getWeakesSubject()
    {
        Map.Entry<String, Double> minentry = null;
        List<Map.Entry<String, Double>> minEntryList = new ArrayList<Map.Entry<String, Double>>();
        
         for (Map.Entry<String, Double> entry : statstic.entrySet())
         {
             
             if (minentry == null)
             {
                 minentry = entry;
                 minEntryList.add(minentry);
             }
             else if (entry.getValue().equals( minentry.getValue()))
             {
                 minentry = entry;
                 minEntryList.add(minentry);
             }
             else if (entry.getValue() < minentry.getValue())
             {
                 minEntryList.clear();
                 minEntryList.add(entry);
                 minentry = entry;
             }
         }
         Random rand = new Random();
         if(minEntryList.size()== 0)
             return null;
         String[] pair = minEntryList.get(rand.nextInt(minEntryList.size())).getKey().split("-");
            return new QuestionCriteria(pair[0], Double.parseDouble(pair[1]));
    }
    
    public static void main (String[] args)
    {
        SmartAgentService sas = new SmartAgentService();
        sas.prepareTestProgress();
        System.out.println(sas.getNextQuestion());
    }
}
