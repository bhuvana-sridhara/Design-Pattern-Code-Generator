package com.bhuvana.designpatterndemo;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.project.*;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;

import bhuvana_sridhara_hw1.src.main.java.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GetInputAction extends AnAction {


    UserInput userInput = new UserInput();
    InputHelperFacade inputHelperFacade = new InputHelperFacade();
    String textToDisplay[];
    List listOfDesignPatterns;
    ParseConfig parse = new ParseConfig();
    String designPattern;
    String path;
    String packageName;
    public static String newline = System.getProperty("line.separator");
    int i;
    boolean set = false;
    boolean mismatch = false;
    PsiFile[] files;


    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        //String projectName = project.getName();
        String projectName = e.getProject().getName();
        VirtualFile[] vFiles = ProjectRootManager.getInstance(e.getProject()).getContentSourceRoots();
        String sourceRootsList = Arrays.stream(vFiles).map(VirtualFile::getUrl).collect(Collectors.joining("\n"));
        Messages.showInfoMessage("Source roots for the " + projectName + " plugin:\n" + sourceRootsList, "Project Properties");


//        ProjectFileIndex projectFileIndex = ProjectRootManager.getInstance(e.getProject()).getFileIndex();
//        VirtualFile vFile = LocalFileSystem.getInstance().findFileByPath(path);
//
//        VirtualFile moduleContentRoot = ProjectRootManager.getInstance(e.getProject()).getFileIndex().isLibraryClassFile();
//        VirtualFile moduleSourceRoot = ProjectRootManager.getInstance(e.getProject()).getFileIndex().getSourceRootForFile();

//        FilenameIndex.processFilesByName("test",true,);
        String listofPatterns = parse.parseConfig("available_design_patterns");
        WriteToJavaFiles write = new WriteToJavaFiles();
        System.out.println("setting path");

        path = getEventProject(e).getBasePath();

        //TODO remove hardcode
        listofPatterns = "AbstractFactory \n" +
                "Builder \n" +
                "FactoryMethod \n" +
                "Facade \n" +
                "ChainOfResponsibility \n" +
                "Mediator \n" +
                "Visitor \n" +
                "TemplateMethod \n" +
                "Prototype \n" +
                "Adapter \n" +
                "Bridge \n" +
                "Composite \n" +
                "Decorator \n" +
                "Flyweight \n" +
                "Proxy \n" +
                "Command \n" +
                "Interpreter \n" +
                "Iterator \n" +
                "Memento \n" +
                "Observer \n" +
                "State \n" +
                "Strategy \n";

        listOfDesignPatterns = Collections.singletonList(parse.parseConfig("conf.available_design_patterns"));
        System.out.println("listOfDesignPatterns" + listOfDesignPatterns);
        listOfDesignPatterns = Arrays.asList(listofPatterns.split("\\s* \\s*"));


        /**
         * display all options for patterns
         * Input dialog takes 1-22
         */


        textToDisplay = new String[listOfDesignPatterns.size() + 2];
        textToDisplay[0] = "List of design patterns:";
        for (int i = 0; i < listOfDesignPatterns.size(); i++) {
            textToDisplay[i + 1] = i + 1 + ". " + (String) listOfDesignPatterns.get(i);
        }
        textToDisplay[listOfDesignPatterns.size() + 1] = "Choose your pattern (1-22):";
        DesignPatternWrapper designPatternWrapper = new DesignPatternWrapper(e.getProject(), false, textToDisplay); //TODO true vs false
        if (designPatternWrapper.showAndGet()) {
            try {
                inputHelperFacade.setSelectedPattern(Integer.parseInt(designPatternWrapper.returnInputString));
            } catch (InvalidPatternException ex) {
                ex.printStackTrace();
            }
        }

        designPattern = listOfDesignPatterns.get(inputHelperFacade.returnValue().selectedPattern - 1).toString();
        Messages.showMessageDialog(e.getProject(), "You have selected " + designPattern + " pattern", "Pattern selected!", Messages.getInformationIcon());

        textToDisplay = new String[]{"Enter package name for generated files"};
        InputDialogWrapper inputDialogWrapper = new InputDialogWrapper(e.getProject(), false, textToDisplay);
        if (inputDialogWrapper.showAndGet()) {
            packageName = inputDialogWrapper.returnInputString[0];
        }

        //setting values for writing java files
        write.setValues(path, packageName);
        packageName = sourceRootsList + "/" + packageName;

        // PsiFile[] file = FilenameIndex.getFilesByName(e.getProject(), "Car.java",GlobalSearchScope.moduleScope(module));
//        if (file != null && file.length > 0) {
//            System.out.println(GlobalSearchScope.allScope(e.getProject()));
//        }

        /**
         * Main interface
         */

        set = false;

        while (set == false) {

            mismatch = false;

            textToDisplay = new String[]{"Enter name for main " + designPattern + " class/interface"};
            inputDialogWrapper = new InputDialogWrapper(e.getProject(), false, textToDisplay); //TODO true vs false

            if (inputDialogWrapper.showAndGet()) {
                //user pressed ok

                files = FilenameIndex.getFilesByName(e.getProject(), inputDialogWrapper.returnInputString[0] + ".java", GlobalSearchScope.allScope(e.getProject()));
                if (files != null && files.length > 0) {
                    //some files exisy. module still unknown
                    for (PsiFile f : files) {

                        //iterate through each file and compare paths
                        String directoryPath = "file://" + f.getContainingDirectory();
                        directoryPath = directoryPath.replaceAll("PsiDirectory:", "");
                        System.out.println(directoryPath);
                        System.out.println(packageName);
                        if (directoryPath.equalsIgnoreCase(packageName)) {
                            //Duplicate. don't let them create
                            Messages.showMessageDialog(e.getProject(), "Oops! Class " + inputDialogWrapper.returnInputString[0] + ".java already exists in module" + packageName + "Try again with a different name.", "File already exists!!", Messages.getInformationIcon());
                            mismatch = true;
                        } else
                            //should continue with comparision of other files
                            continue;
                    }
                }
                if (!mismatch) {
                    inputHelperFacade.setMainInterfaceName(inputDialogWrapper.returnInputString[0]); //will always be just 1 main name
                    set = true;
                }
            }
        }


        /**
         * number of classes implementing main interface
         */


        textToDisplay = new String[]{"Enter number of concrete classes implementing " + designPattern + " class/interface"};

        inputDialogWrapper = new InputDialogWrapper(e.getProject(), false, textToDisplay); //TODO true vs false
        if (inputDialogWrapper.showAndGet()) {
            //user pressed ok
            i = Integer.parseInt(inputDialogWrapper.returnInputString[0]); //will always be just 1 int field
            inputHelperFacade.setNumberOfConcreteClasses(i);
        }

        /**
         * classname for each of concrete classes implementing main interface
         */

        set = false;

        while (set == false) {

            mismatch = false;

            textToDisplay = new String[inputHelperFacade.returnValue().numberOfConcreteClasses];
            //textToDisplay[0]="Enter name for "+inputHelperFacade.returnValue().numberOfConcreteClasses+" class(es) implementing "+inputHelperFacade.returnValue().interfaceName;
            for (int i = 1; i <= inputHelperFacade.returnValue().numberOfConcreteClasses; i++) {
                textToDisplay[i - 1] = "Class " + i;
            }
//          textToDisplay = "Enter name for "+inputHelperFacade.returnValue().numberOfConcreteClasses+" class(es) implementing "+inputHelperFacade.returnValue().interfaceName;
            for (String t : textToDisplay) {
                System.out.println("textToDisplay" + t);
            }
            inputDialogWrapper = new InputDialogWrapper(e.getProject(), false, textToDisplay); //TODO true vs false
            if (inputDialogWrapper.showAndGet()) {
                //user pressed ok
                for (String s : inputDialogWrapper.returnInputString) {
                    files = FilenameIndex.getFilesByName(e.getProject(), s + ".java", GlobalSearchScope.allScope(e.getProject()));
                    if (files != null && files.length > 0) {
                        //some files exisy. module still unknown
                        for (PsiFile f : files) {

                            //iterate through each file and compare paths
                            String directoryPath = "file://" + f.getContainingDirectory();
                            directoryPath = directoryPath.replaceAll("PsiDirectory:", "");
                            System.out.println(directoryPath);
                            System.out.println(packageName);
                            if (directoryPath.equalsIgnoreCase(packageName)) {
                                //Duplicate. don't let them create
                                Messages.showMessageDialog(e.getProject(), "Oops! Class " + s + ".java already exists in module" + packageName + "Try again with a different name.", "File already exists!!", Messages.getInformationIcon());
                                mismatch = true;
                            } else
                                //should continue with comparision of other files
                                continue;
                        }
                    }
                }

                if (!mismatch) {
                    for (String s : inputDialogWrapper.returnInputString) {
                        inputHelperFacade.returnValue().concreteClassNames.add(s);
                    }
                    set = true;
                }
            }
        }
            /**
             * number of abstract methods
             */
            textToDisplay = new String[]{"Enter number of abstract methods in " + inputHelperFacade.returnValue().interfaceName + " class"};

            inputDialogWrapper = new InputDialogWrapper(e.getProject(), false, textToDisplay); //TODO true vs false
            if (inputDialogWrapper.showAndGet()) {
                //user pressed ok
                i = Integer.parseInt(inputDialogWrapper.returnInputString[0]); //will always be just 1 int field
                inputHelperFacade.setNumberOfAbstractMethods(i);
            }

            /**
             * abstract classes
             */
        set = false;

        while (set == false) {

            mismatch = false;

            textToDisplay = new String[inputHelperFacade.returnValue().numberOfAbstractClasses];
            //textToDisplay[0]="Enter name for "+inputHelperFacade.returnValue().numberOfConcreteClasses+" class(es) implementing "+inputHelperFacade.returnValue().interfaceName;
            for (int i = 1; i <= inputHelperFacade.returnValue().numberOfAbstractClasses; i++) {
                textToDisplay[i - 1] = "Method " + i;
            }
//        textToDisplay = "Enter name for "+inputHelperFacade.returnValue().numberOfConcreteClasses+" class(es) implementing "+inputHelperFacade.returnValue().interfaceName;
            for (String t : textToDisplay) {
                System.out.println("textToDisplay" + t);
            }
            inputDialogWrapper = new InputDialogWrapper(e.getProject(), false, textToDisplay); //TODO true vs false
            if (inputDialogWrapper.showAndGet()) {
                //user pressed ok
                for (String s : inputDialogWrapper.returnInputString) {
                    files = FilenameIndex.getFilesByName(e.getProject(), s + ".java", GlobalSearchScope.allScope(e.getProject()));
                    if (files != null && files.length > 0) {
                        //some files exisy. module still unknown
                        for (PsiFile f : files) {

                            //iterate through each file and compare paths
                            String directoryPath = "file://" + f.getContainingDirectory();
                            directoryPath = directoryPath.replaceAll("PsiDirectory:", "");
                            System.out.println(directoryPath);
                            System.out.println(packageName);
                            if (directoryPath.equalsIgnoreCase(packageName)) {
                                //Duplicate. don't let them create
                                Messages.showMessageDialog(e.getProject(), "Oops! Class " + s + ".java already exists in module" + packageName + "Try again with a different name.", "File already exists!!", Messages.getInformationIcon());
                                mismatch = true;
                            } else
                                //should continue with comparision of other files
                                continue;
                        }
                    }
                }

                if (!mismatch) {
                    for (String s : inputDialogWrapper.returnInputString) {
                        inputHelperFacade.returnValue().overridingMethodNames.add(s);
                    }
                    set = true;
                }
            }
        }

            GetCodeFactory getCodeFactory = new GetCodeFactory();
            UserInput userInput;
            userInput = inputHelperFacade.returnValue();
            userInput.inputMode = "Plugin";
            DesignPatternBuilder designPatternBuilder = getCodeFactory.getDesignPattern(userInput.selectedPattern);


            /**
             * Get more inputs of abstract factory
             */
        if(designPatternBuilder.getClass().equals(AbstractFactory.class)){
            //get more inputs for abstract factory
            textToDisplay = new String[]{"Enter number of factory classes you need"};
            inputDialogWrapper = new InputDialogWrapper(e.getProject(),false,textToDisplay);
            if(inputDialogWrapper.showAndGet()){
                //user pressed ok
                i = Integer.parseInt(inputDialogWrapper.returnInputString[0]); //will always be just 1 int field
                userInput.numberOfFactories = i;
            }

            userInput.implementedMethodName = "get"+userInput.interfaceName;

            textToDisplay = new String[inputHelperFacade.returnValue().numberOfFactories];
            //textToDisplay = new String[]{"Enter name for "+userInput.numberOfFactories+" " +userInput.interfaceName+" factory classes"};
            for(int i = 1; i <= inputHelperFacade.returnValue().numberOfFactories;i++){
                textToDisplay[i-1] = "Factory class "+i;
            }
            inputDialogWrapper = new InputDialogWrapper(e.getProject(),false,textToDisplay);
            if(inputDialogWrapper.showAndGet()){
                //user pressed ok
                for(String s: inputDialogWrapper.returnInputString){
                    String classname = s;
                    userInput.factoryClasses.add(classname);
                }
            }
        }


            /**
             * Get more inputs if Builder
             */
            else if (designPatternBuilder.getClass().equals(Builder.class)) {
                textToDisplay = new String[]{"Enter name of the product you want to build using " + userInput.interfaceName};
                inputDialogWrapper = new InputDialogWrapper(e.getProject(), false, textToDisplay);
                if (inputDialogWrapper.showAndGet()) {
                    userInput.product = inputDialogWrapper.returnInputString[0];
                }
            }

            try {
                designPatternBuilder.getCode(userInput);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
}