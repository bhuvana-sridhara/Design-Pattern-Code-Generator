package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.TypeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Builder extends DesignPatternBuilder{
    /**
     *  Concrete class Builder that extends DesignPattern abstract class
     *  Builder constructs complex objects by separating construction and representation
     */
   // @Override
//
//        for(int i = 0; i< userInput.numberOfConcreteClasses; i++){
//            TypeSpec element = TypeSpec
//                    .classBuilder(userInput.concreteClassNames.get(i))
//                    .addSuperinterface(TypeVariableName.get(userInput.interfaceName))
//                    //concrete classes of factory method design pattern.
//                    .addModifiers(Modifier.PUBLIC) // all other concrete classes will extend this
//                    .addMethod(MethodSpec
//                            //overriding method in the main abstract class of factory
//                            .methodBuilder(userInput.overridingMethodNames)
//                            .addModifiers(Modifier.PUBLIC)
//                            .returns(TypeVariableName.get(product))
//                            .build())
//                    .build();
//            concreteClassdesign.add(element);
//        }
    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
    WriteToJavaFiles writeToJavaFiles = new WriteToJavaFiles();
    TypeSpec design;
    ArrayList<TypeSpec> multiDesign;

    public void getMoreInputs(UserInput userInput){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name of the product you want to build using "+userInput.interfaceName);
        userInput.product = in.nextLine();
        //TODO do something with product
    }

    @Override
    public void getCode(UserInput userInput) throws IOException {
        if(userInput.inputMode!="Plugin"){
            getMoreInputs(userInput);
        }

        buildMainInterface(userInput);
        buildConcreteClasses(userInput);
        buildProductClass(userInput);

    }

    @Override
    public void buildMainInterface(UserInput userInput) throws IOException {
        MainInterfaceBuilder mainInterfaceBuilder = new MainInterfaceBuilder();
        design = mainInterfaceBuilder.build(userInput);
        writeToJavaFiles.write(design);
        //TODO implemented method names?
    }

    @Override
    public void buildConcreteClasses(UserInput userInput) throws IOException {
        ConcreteClassesBuilder concreteClassesBuilder = new ConcreteClassesBuilder();
        multiDesign = concreteClassesBuilder.build(userInput);
        for (TypeSpec concreteClass: multiDesign) {
            writeToJavaFiles.write(concreteClass);
        }
    }

    public void buildProductClass(UserInput userInput) throws IOException {
        MainInterfaceBuilder mainInterfaceBuilder = new MainInterfaceBuilder();
        design = mainInterfaceBuilder.build(userInput);
        writeToJavaFiles.write(design);
        //TODO implemented method names?
    }

    @Override
    public void buildOverridingMethods(UserInput userInput) throws IOException {

    }

    @Override
    public void buildFactory(UserInput userInput) throws IOException {

    }

    @Override
    public void buildMainFactory(UserInput userInput) throws IOException {

    }

    @Override
    public void buildClientClass(UserInput userInput) throws IOException {

    }

}
