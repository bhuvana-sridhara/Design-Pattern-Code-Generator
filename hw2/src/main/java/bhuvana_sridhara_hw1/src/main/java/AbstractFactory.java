package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.TypeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Concrete class AbstractFactory that extends DesignPattern abstract class
 */
public class AbstractFactory extends DesignPatternBuilder {
    /**
     * Abstract factory groups object factories that have a common theme.
     */

    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
    WriteToJavaFiles writeToJavaFiles = new WriteToJavaFiles();
    TypeSpec design;
    ArrayList<TypeSpec> multiDesign;

    public void getMoreInputScanner(UserInput userInput){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of factory classes you need");
        userInput.numberOfFactories = in.nextInt();
        userInput.implementedMethodName = "get"+userInput.interfaceName;
        System.out.println("Enter name for "+userInput.numberOfFactories+" " +userInput.interfaceName+" factory classes");
        in.nextLine();
        for(int i = 0; i <userInput.numberOfFactories; i++){
            System.out.println("Factory class"+(i+1)+":");
            String classname = in.nextLine();
            userInput.factoryClasses.add(classname);
        }
        in.close();
    }

    @Override
    public void getCode(UserInput userInput) throws IOException {
        if(!userInput.inputMode.equalsIgnoreCase("Plugin")){
            getMoreInputScanner(userInput);
        }

        userInput.mainFactory = userInput.interfaceName+"Factory";

        // TODO do something about repeated param and exception
        buildMainInterface(userInput);
        buildConcreteClasses(userInput);
        buildFactory(userInput);
        buildMainFactory(userInput);
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
        FactoriesBuilder factoriesBuilder = new FactoriesBuilder();
        multiDesign = factoriesBuilder.build(userInput);
        for (TypeSpec factory: multiDesign) {
            writeToJavaFiles.write(factory);
        }

    }

    @Override
    public void buildMainFactory(UserInput userInput) throws IOException {
        MainFactoryBuilder mainFactoryBuilder = new MainFactoryBuilder();
        design = mainFactoryBuilder.build(userInput);
        writeToJavaFiles.write(design);
    }

    @Override
    public void buildClientClass(UserInput userInput) throws IOException {
        //TODO client
    }
}