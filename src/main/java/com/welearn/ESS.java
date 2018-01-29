/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.welearn;

import com.welearn.app.forms.QImageFrame;
import com.welearn.domain.entities.QAPair;
import com.welearn.domain.service.SmartAgentService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author weiwei2017
 */
public class ESS {

    /**
     * @param args the command line arguments
     *
     */
    public static void main(String[] args) {
        QAPair qaPair = new SmartAgentService().getNextQuestion();
        QImageFrame frame = new QImageFrame();
        frame.setVisible(true);
    }

}
