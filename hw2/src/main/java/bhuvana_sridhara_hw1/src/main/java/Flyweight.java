package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.TypeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

public class Flyweight extends DesignPatternBuilder{
    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
    /**
     * Flyweight reduces the cost of creating and manipulating a large number of similar objects.
     */

    WriteToJavaFiles writeToJavaFiles = new WriteToJavaFiles();
    TypeSpec design;
    ArrayList<TypeSpec> multiDesign;

    @Override
    public void getCode(UserInput userInput) throws IOException {
        buildMainInterface(userInput);
        buildConcreteClasses(userInput);
        buildMainFactory(userInput);
        buildClientClass(userInput);
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

    @Override
    public void buildOverridingMethods(UserInput userInput) throws IOException {

    }

    @Override
    public void buildFactory(UserInput userInput) throws IOException {

    }

    @Override
    public void buildMainFactory(UserInput userInput) throws IOException {
        //TODO change
        userInput.mainFactory = userInput.interfaceName+"Factory";
        userInput.implementedMethodName = "get"+userInput.interfaceName;
        MainFactoryBuilder mainFactoryBuilder = new MainFactoryBuilder();
        design = mainFactoryBuilder.build(userInput);
        writeToJavaFiles.write(design);
    }

    @Override
    public void buildClientClass(UserInput userInput) throws IOException {
        ClientClassBuilder clientClassBuilder = new ClientClassBuilder();
        design = clientClassBuilder.build(userInput);
        writeToJavaFiles.write(design);
    }
}
