/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.welearn.domain.service;

import com.welearn.domain.entities.Answers;
import com.welearn.domain.entities.QAPair;
import com.welearn.domain.entities.QuestionCriteria;
import com.welearn.domain.entities.Questions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author weiwei2017
 */
public class QuestionLibService {

    private List<Questions> questions = new ArrayList<Questions>();
    private Map<Integer, List<Answers>> answers = new HashMap<Integer, List<Answers>>();
    private List<String> quizType = new ArrayList<String>();

    public static final String FILE_NAME = "src/main/resources/questions.xlsx";

    // question sheet sequence  and column sequence 
    private static final Integer QUESTION = 0;
    private static final int Q_ID = 0;
    private static final int Q_DESCRIPTION = 1;
    private static final int Q_HINT = 2;
    private static final int Q_WEIGHT = 3;
    private static final int Q_LEVEL = 4;
    private static final int Q_TYPE = 5;

    // answer sheet sequence  and column sequence 
    private static final Integer ANSWER = 1;
    private static final int A_ID = 0;
    private static final int A_QID = 1;
    private static final int A_DESCRIPTION = 2;
    private static final int A_RIGHT = 3;

    private static final Integer QUIZ_TYPE = 2;
    private static final int QT_TYPE_NAME = 0;

    private Sheet questionSheet;
    private Sheet answerSheet;
    private Sheet quizTypeSheet;

    public QuestionLibService() {

        FileInputStream excelFile;
        try {
            excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            questionSheet = workbook.getSheetAt(QUESTION);
            answerSheet = workbook.getSheetAt(ANSWER);
            quizTypeSheet = workbook.getSheetAt(QUIZ_TYPE);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QuestionLibService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QuestionLibService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadType() {
        Iterator<Row> iterator = quizTypeSheet.iterator();
        iterator.next();;
        while (iterator.hasNext()) {
            Row questionRow = iterator.next();
            getQuizType().add(questionRow.getCell(QT_TYPE_NAME).getStringCellValue());
        }
    }

    private void LoadQuestions() {
        Iterator<Row> iterator = questionSheet.iterator();
        iterator.next();;
        while (iterator.hasNext()) {

            Row questionRow = iterator.next();
            Questions q = new Questions((int) questionRow.getCell(Q_ID).getNumericCellValue(),
                    questionRow.getCell(Q_DESCRIPTION).getStringCellValue(),
                    questionRow.getCell(Q_HINT).getStringCellValue(),
                    (int) questionRow.getCell(Q_WEIGHT).getNumericCellValue(),
                    (int) questionRow.getCell(Q_LEVEL).getNumericCellValue(),
                    questionRow.getCell(Q_TYPE).getStringCellValue());
            questions.add(q);
        }

    }

    private void loadAnswers() {
        Iterator<Row> iterator = answerSheet.iterator();
        iterator.next();
        List<Answers> tempAnaswerLib = new ArrayList<Answers>();
        while (iterator.hasNext()) {
            Row answerRow = iterator.next();
            Answers a = new Answers((int) answerRow.getCell(A_ID).getNumericCellValue(),
                    (int) answerRow.getCell(A_QID).getNumericCellValue(),
                    answerRow.getCell(A_DESCRIPTION).getStringCellValue(),
                    answerRow.getCell(A_RIGHT).getBooleanCellValue());
            tempAnaswerLib.add(a);
        }

        Collections.sort(tempAnaswerLib);
        Iterator<Answers> iterQ = tempAnaswerLib.iterator();
        while (iterQ.hasNext()) {
            List<Answers> subAnswerList = new ArrayList<Answers>();
            for (int i = 0; i < 4; i++) {
                Answers a = iterQ.next();
                subAnswerList.add(a);
            }
            answers.put(subAnswerList.get(0).getqId(), subAnswerList);
        }

    }

    private void printQuestion() {
        Iterator<Questions> iterQ = questions.iterator();
        while (iterQ.hasNext()) {
            Questions q = iterQ.next();
            System.out.println(q);
        }
    }

    private void printAnswers() {
        Iterator<Entry<Integer, List<Answers>>> iterAMap = answers.entrySet().iterator();
        while (iterAMap.hasNext()) {
            Map.Entry pair = (Map.Entry) iterAMap.next();
            List<Answers> answers = (List<Answers>) pair.getValue();
            Iterator<Answers> iterAList = answers.iterator();
            while (iterAList.hasNext()) {
                Answers a = iterAList.next();
                System.out.println(a);
            }

        }
    }

    private void clearData() {
        answers.clear();
        questions.clear();
        getQuizType().clear();

    }

    public void load() {
        if (questions.isEmpty()) {
            System.out.println("==============loading question===============");
            LoadQuestions();
        }
        if (answers.isEmpty()) {
            System.out.println("==============loading answers===============");
            loadAnswers();
        }
        if (getQuizType().isEmpty()) {
            System.out.println("==============loading types===============");
            loadType();
        }
    }

    /**
     * @return the quizType
     */
    public List<String> getQuizType() {
        return quizType;
    }

    // this is randome method to generate a question by criterion
    QAPair getQAPairByQuestionCriteria(QuestionCriteria qc) {

            List<Questions> ques =null;        
        if (qc==null)
            ques = questions;
        else
            ques = questions.stream()
                .filter(q -> q.getLevel() == qc.getLevel())
                .filter(q -> q.getType().equals(qc.getType()))
                .collect(Collectors.toList());
        Random rand = new Random();
        Questions qObj = ques.get(rand.nextInt(ques.size()));
        List<Answers> qAnswer = answers.get(qObj.getqId());
        QAPair qapari = new QAPair(qObj, qAnswer);
        System.out.println(qapari);
        return qapari;
    }
}
