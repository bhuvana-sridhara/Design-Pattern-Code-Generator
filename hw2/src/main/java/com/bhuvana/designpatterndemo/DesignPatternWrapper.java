package com.bhuvana.designpatterndemo;

import bhuvana_sridhara_hw1.src.main.java.InputHelperFacade;
import bhuvana_sridhara_hw1.src.main.java.InvalidPatternException;
import com.bhuvana.designpatterndemo.ParseConfig;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBLabel;
import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.util.ui.GridBag;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import icons.ActionBasicsIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DesignPatternWrapper extends DialogWrapper {

    //Config config = ConfigFactory.parseResources("Config.conf");

//    List listOfDesignPatterns = config.getList("conf.available_design_patterns").unwrapped();
//    int numberOfDesignPatterns = config.getInt("conf.number_of_design_patterns");


    public String title;
    public String text;
    JPanel panel = new JPanel(new GridBagLayout());
    JTextField textMode;
    public String returnInputString;
    public String[] labels;

    protected DesignPatternWrapper(@Nullable Project project, boolean canBeParent,String[] textToDisplay) {
        super(project, canBeParent);
        labels = new String[textToDisplay.length];
        System.arraycopy(textToDisplay,0,labels,0,textToDisplay.length);
        init();
    }

    protected void init() {


        //TODO display title
        title = "Input Data";

//        System.out.println("initializing Jtextfield");
        textMode = new JTextField();
        returnInputString = new String();
        super.init();
    }

    @Override
    protected void doOKAction(){
        returnInputString = textMode.getText();
        super.doOKAction();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        GridBag gridBag = new GridBag();
        gridBag.setDefaultInsets(new Insets(0,0, AbstractLayout.DEFAULT_VGAP,AbstractLayout.DEFAULT_HGAP));
        gridBag.setDefaultWeightX(1.0);
        gridBag.setDefaultFill(GridBagConstraints.HORIZONTAL);

        panel.setPreferredSize(new Dimension(400,200));
        //panel.add(title)

        //add more labels here

        for(int i = 0; i< labels.length; i++){
            panel.add(label(labels[i]), gridBag.nextLine().next().weightx(0.2));
        }
        panel.add(textMode , gridBag.nextLine().next().weightx(0.5));


        return panel;
    }

    private JComponent label(String text){
        JBLabel label = new JBLabel(text);
        label.setComponentStyle(UIUtil.ComponentStyle.REGULAR);
        label.setFontColor(UIUtil.FontColor.BRIGHTER);
        label.setBorder(JBUI.Borders.empty(0,5,2,0));
        return label;
    }
}
