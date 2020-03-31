package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.TypeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

public class FactoryMethod extends DesignPatternBuilder {
    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
    /**
     * Factory method creates objects without specifying the exact class to create.
     */

    WriteToJavaFiles writeToJavaFiles = new WriteToJavaFiles();
    TypeSpec design;
    ArrayList<TypeSpec> multiDesign;

    @Override
    public void getCode(UserInput userInput) throws IOException {
        userInput.factoryClasses.add(userInput.interfaceName+"Factory");
        userInput.implementedMethodName = "get"+userInput.interfaceName;
        userInput.mainFactory = userInput.interfaceName+"Factory";

        buildMainInterface(userInput);
        buildConcreteClasses(userInput);
        //buildFactory(userInput);
        buildMainFactory(userInput);

//        //TODO move code elsewhere
//        concreteClassdesign = new ArrayList<TypeSpec>();
//
//        //Scanner in = new Scanner(System.in);
//        if(userInput.factoryClasses.isEmpty()){
//            //case: not called through abstract factory
//            userInput.factoryClasses.add(userInput.interfaceName+"Factory");
//        }
//       // in.close();
//        userInput.implementedMethodName = "get"+userInput.interfaceName;
//
//        mainInterfacedesign = TypeSpec
//                .interfaceBuilder(userInput.interfaceName)
//                //main abstract class of factory method design pattern.
//                .addModifiers(Modifier.PUBLIC) // all other concrete classes will extend this
//                .addMethod(MethodSpec
//                        //abstract method that will be overridden by extending classes
//                        .methodBuilder(userInput.overridingMethodNames)
//                        .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
//                        .returns(void.class)
//                        .build())
//                .build();
//
//        for(int i = 0; i< userInput.numberOfConcreteClasses; i++){
//             TypeSpec element = TypeSpec
//                    .classBuilder(userInput.concreteClassNames.get(i))
//                    .addSuperinterface(TypeVariableName.get(userInput.interfaceName))
//                    //concrete classes of factory method design pattern.
//                    .addModifiers(Modifier.PUBLIC) // all other concrete classes will extend this
//                    .addMethod(MethodSpec
//                            //overriding method in the main abstract class of factory
//                            .methodBuilder(userInput.overridingMethodNames)
//                            .addAnnotation(Override.class)
//                            .addModifiers(Modifier.PUBLIC)
//                            .returns(void.class)
//                            .build())
//                    .build();
//            concreteClassdesign.add(element);
//
//        }
//        factoryDesign = new ArrayList<>();
//        factoryDesign.add(TypeSpec
//                .classBuilder(userInput.factoryClasses.get(0))
//                .addModifiers(Modifier.PUBLIC)
//                .addMethod(MethodSpec
//                        .methodBuilder(userInput.implementedMethodName)
//                        .addModifiers(Modifier.PUBLIC)
//                        .addComment("You can use this method to get object of type " + userInput.interfaceName + ". Example:")
//                        .addStatement("return new $T()", (TypeVariableName.get(userInput.concreteClassNames.get(0))))
//                        .returns(TypeVariableName.get(userInput.interfaceName))
//                        .build())
//                .build());
//
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
        MainFactoryBuilder mainFactoryBuilder = new MainFactoryBuilder();
        design = mainFactoryBuilder.build(userInput);
        writeToJavaFiles.write(design);
    }

    @Override
    public void buildClientClass(UserInput userInput) throws IOException {

    }
}