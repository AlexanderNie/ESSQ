/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.welearn;

import com.welearn.app.forms.QImageFrame;
import com.welearn.domain.entities.QAPair;
import com.welearn.domain.entities.QuizResult;
import com.welearn.domain.service.SmartAgentService;

/**
 *
 * @author weiwei2017
 */
public class ESS {
    
    private QAPair qaPair;
    private QImageFrame frame;
    SmartAgentService agent = new SmartAgentService();
    public ESS()
    {
        qaPair = agent.getNextQuestion();
        frame = new QImageFrame();
        frame.updateQuestion(qaPair);
        frame.updateUser("Alexander Nie");
        frame.startTimer(SmartAgentService.MAX_QUIZ_TIME);
        frame.setVisible(true);
    }
    /**
     * @param args the command line arguments
     *
     */
    public static void main(String[] args) {
        ESS controller = new ESS();
        controller.getFrame().setObserver(controller);
    }

    /**
     * @return the frame
     */
    public QImageFrame getFrame() {
        return frame;
    }

    /**
     * @param frame the frame to set
     */
    public void setFrame(QImageFrame frame) {
        this.frame = frame;
    }
    
    public void updateProgress(QuizResult result)
    {
        agent.updateProgress(result);
    }

    public void moveToNext() {
        qaPair = agent.getNextQuestion();
        frame.updateQuestion(qaPair);
    }

}
