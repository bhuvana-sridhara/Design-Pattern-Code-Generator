package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.TypeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

public class Facade extends DesignPatternBuilder {
    /**
     * Facade provides a simplified interface to a large body of code
     */

    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
    WriteToJavaFiles writeToJavaFiles = new WriteToJavaFiles();
    TypeSpec design;
    ArrayList<TypeSpec> multiDesign;

    @Override
    public void getCode(UserInput userInput) throws IOException {
        buildMainInterface(userInput);
        buildConcreteClasses(userInput);
        buildFacade(userInput);
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

    public void buildFacade(UserInput userInput) throws IOException {

        for (String className:userInput.concreteClassNames) {
            for(String methodName: userInput.overridingMethodNames){
                //for example: drawSquare, colorRectangle etc
                userInput.facadeClassMethods.add(methodName+className);
            }
        }
        userInput.facadeClassName = userInput.interfaceName+"Facade";
        ConcreteClassesBuilder concreteClassesBuilder = new ConcreteClassesBuilder();
        design = concreteClassesBuilder.build(userInput,true);
        writeToJavaFiles.write(design);
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

//    //@Override
//    public void getCode(UserInput userInput) {
//        //TODO remove duplicate code. Use design pattern eg: builder
//        concreteClassdesign = new ArrayList<>();
//        mainInterfacedesign = TypeSpec
//                .classBuilder(userInput.interfaceName)
//                //main abstract class of template design pattern.
//                .addModifiers(Modifier.PUBLIC)
//                .addMethod(MethodSpec
//                        .methodBuilder(userInput.overridingMethodNames)
//                        .addModifiers(Modifier.PUBLIC)
//                        .build())
//                .build();
//        for (int i = 0; i < userInput.numberOfConcreteClasses; i++) {
//            TypeSpec element = TypeSpec
//                    .classBuilder(userInput.concreteClassNames.get(i))
//                    .superclass(TypeVariableName.get(userInput.interfaceName))
//                    //concrete classes of facade design pattern.
//                    .addModifiers(Modifier.PUBLIC) //
//                    .addMethod(MethodSpec
//                            .methodBuilder(userInput.overridingMethodNames)
//                            .addAnnotation(Override.class)
//                            .addModifiers(Modifier.PUBLIC)
//                            .build())
//                    .build();
//            concreteClassdesign.add(element);
//        }

}
