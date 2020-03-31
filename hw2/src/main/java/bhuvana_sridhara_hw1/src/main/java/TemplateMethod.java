package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.TypeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

public class TemplateMethod extends DesignPatternBuilder {
    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
    /**
     *
     * Template method defines the skeleton of an algorithm as an abstract class, allowing its subclasses to provide concrete behavior.
     */

    WriteToJavaFiles writeToJavaFiles = new WriteToJavaFiles();
    TypeSpec design;
    ArrayList<TypeSpec> multiDesign;

    @Override
    public void getCode(UserInput userInput) throws IOException {
        buildMainInterface(userInput);
        buildConcreteClasses(userInput);
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

    }

    @Override
    public void buildClientClass(UserInput userInput) throws IOException {
        ClientClassBuilder clientClassBuilder = new ClientClassBuilder();
        design = clientClassBuilder.build(userInput);
        writeToJavaFiles.write(design);
    }
    //@Override
//    public void getCode(UserInput userInput){
//        concreteClassdesign = new ArrayList<>();
//        mainInterfacedesign = TypeSpec
//                .classBuilder(userInput.interfaceName)
//                //main abstract class of facade design pattern.
//                .addModifiers(Modifier.PUBLIC)
//                .addMethod(MethodSpec
//                        // method that will call all other concrete methods
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
//                            //overriding method in the main abstract class of factory
//                            .methodBuilder(userInput.overridingMethodNames)
//                            .addAnnotation(Override.class)
//                            .addModifiers(Modifier.PUBLIC)
//                            .build())
//                    .build();
//            concreteClassdesign.add(element);
//        }
//        userInput.mainClass = "TemplateClient";
//        clientClassDesign = TypeSpec
//                .classBuilder(userInput.mainClass)
//                .addModifiers(Modifier.PUBLIC)
//                .addMethod(MethodSpec
//                        .methodBuilder("main") //TODO change name
//                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//                        .addParameter(String.class, "args")
//                        //TODO
//                        .addComment("You can use this method to __. Example:")
//                        //TODO better way? new keyword
//                        .addStatement(userInput.interfaceName+" "+userInput.interfaceName+"Object = new "+userInput.concreteClassNames.get(0)+"()")
//                        .addStatement(userInput.interfaceName+"Object."+userInput.overridingMethodNames)
//                        .returns(void.class)
//                        .build())
//                .build();
//    }
}

