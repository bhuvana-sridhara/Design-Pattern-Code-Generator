package com.bhuvana.designpatterndemo;

import bhuvana_sridhara_hw1.src.main.java.UserInput;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBLabel;
import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.util.ui.GridBag;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.Nullable;
import org.junit.AfterClass;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InputDialogWrapper extends DialogWrapper {

    public String title;
    public String text;
    JPanel panel = new JPanel(new GridBagLayout());
    JTextField[] textMode;
    public List<String> listOfInputs = new ArrayList<>();
    public String[] returnInputString;
    public String[] labels;


    protected InputDialogWrapper(@Nullable Project project, boolean canBeParent, String[] inputLabels) {
        super(project, canBeParent);
        labels = new String[inputLabels.length];
        System.arraycopy(inputLabels,0,labels,0,inputLabels.length);
//        for(String s: labels){
//            System.out.println("labels"+s);
//        }
        init();
    }

    private JComponent label(String text){
        JBLabel label = new JBLabel(text);
        label.setComponentStyle(UIUtil.ComponentStyle.REGULAR);
        label.setFontColor(UIUtil.FontColor.BRIGHTER);
        label.setBorder(JBUI.Borders.empty(0,5,2,0));
        return label;
    }

    protected void init() {


        //TODO display title
        title = "Input Data";

//        System.out.println("initializing Jtextfield");
        textMode = new JTextField[labels.length];
        returnInputString = new String[labels.length];
        super.init();
    }

    @Override
    protected void doOKAction(){
        int i = 0;
        for(JTextField text: textMode){
            System.out.println("text.getText() "+text.getText());
            returnInputString[i] = text.getText();
            i++;
        }
        super.doOKAction();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {

        //Creating panel
        GridBag gridBag = new GridBag();
        gridBag.setDefaultInsets(new Insets(0,0, AbstractLayout.DEFAULT_VGAP,AbstractLayout.DEFAULT_HGAP));
        gridBag.setDefaultWeightX(1.0);
        gridBag.setDefaultFill(GridBagConstraints.HORIZONTAL);

        panel.setPreferredSize(new Dimension(400,200));
        //panel.add(title)

        //add more labels here

        for(int i = 0; i< labels.length; i++){
            textMode[i] = new JTextField();
            panel.add(label(labels[i]), gridBag.nextLine().next().weightx(0.2));
            panel.add(textMode[i] , gridBag.nextLine().next().weightx(0.5));
        }

        return panel;

    }
}