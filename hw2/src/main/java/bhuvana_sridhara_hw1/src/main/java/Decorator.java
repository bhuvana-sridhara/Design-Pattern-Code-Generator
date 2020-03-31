package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.TypeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

public class Decorator extends DesignPatternBuilder {
    /**
     * Decorator dynamically adds/overrides behaviour in an existing method of an object.
     */
    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
    WriteToJavaFiles writeToJavaFiles = new WriteToJavaFiles();
    TypeSpec design;
    ArrayList<TypeSpec> multiDesign;

    @Override
    public void getCode(UserInput userInput) throws IOException {
        buildMainInterface(userInput);
        buildConcreteClasses(userInput);
        buildAbstractDecoratorClass(userInput);
        buildConcreteDecoratorClass(userInput);
        //TODO demo of decorator. Use concrete decorator extending abstract Decorator to decorate main obj
        //TODO build concrete decorator
        //buildClientClass(userInput);
    }

    @Override
    public void buildMainInterface(UserInput userInput) throws IOException {
        MainInterfaceBuilder mainInterfaceBuilder = new MainInterfaceBuilder();
        design = mainInterfaceBuilder.build(userInput);
        writeToJavaFiles.write(design);
    }

    @Override
    public void buildConcreteClasses(UserInput userInput) throws IOException {
        ConcreteClassesBuilder concreteClassesBuilder = new ConcreteClassesBuilder();
        multiDesign = concreteClassesBuilder.build(userInput);
        for (TypeSpec concreteClass: multiDesign) {
            writeToJavaFiles.write(concreteClass);
        }
    }

   // @Override
    //TODO override?
    //TODO rename
    public void buildAbstractDecoratorClass(UserInput userInput) throws IOException {
        MainInterfaceBuilder interfaceBuilder = new MainInterfaceBuilder();
        userInput.superInterfaceName = userInput.interfaceName;
        userInput.interfaceName+="Decorator";
        design = interfaceBuilder.build(userInput,true);
        writeToJavaFiles.write(design);
    }

    public void buildConcreteDecoratorClass(UserInput userInput){

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
