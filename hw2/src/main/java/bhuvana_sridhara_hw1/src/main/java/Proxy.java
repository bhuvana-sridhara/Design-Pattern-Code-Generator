package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.TypeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

public class Proxy extends DesignPatternBuilder {
    /**
     * Proxy provides a placeholder for another object to control access, reduce cost, and reduce complexity.
     */

    WriteToJavaFiles writeToJavaFiles = new WriteToJavaFiles();
    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
    TypeSpec design;
    ArrayList<TypeSpec> multiDesign;

    @Override
    public void getCode(UserInput userInput) throws IOException {
        buildMainInterface(userInput);
        buildConcreteClasses(userInput);
        //build proxy
        buildClientClass(userInput);
        //command invoker?
        //broker
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
        ClientClassBuilder clientClassBuilder = new ClientClassBuilder();
        design = clientClassBuilder.build(userInput);
        writeToJavaFiles.write(design);
    }
}
