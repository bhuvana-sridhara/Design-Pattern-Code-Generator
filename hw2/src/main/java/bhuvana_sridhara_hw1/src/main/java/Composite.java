package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.TypeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

public class Composite extends DesignPatternBuilder{
    /**
     * Composite composes zero-or-more similar objects so that they can be manipulated as one object.
     */

    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
    WriteToJavaFiles writeToJavaFiles = new WriteToJavaFiles();
    TypeSpec design;
    ArrayList<TypeSpec> multiDesign;

    @Override
    public void getCode(UserInput userInput) throws IOException {
        buildMainInterface(userInput);
        buildConcreteClasses(userInput);
        //Composite?
        buildCompositeClass(userInput);
        buildClientClass(userInput);
    }

    @Override
    public void buildMainInterface(UserInput userInput) throws IOException {
        MainInterfaceBuilder mainInterfaceBuilder = new MainInterfaceBuilder();
        userInput.superInterfaceName = "Cloneable";
        design = mainInterfaceBuilder.build(userInput,true);
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

    //Override?
    //TODO required?? can u reuse?
    public void buildCompositeClass(UserInput userInput) throws IOException {

        userInput.compositeClass = userInput.interfaceName+"Composite";

//        ConcreteClassesBuilder concreteClassesBuilder = new ConcreteClassesBuilder();
//        multiDesign = concreteClassesBuilder.build(userInput);
//        for (TypeSpec concreteClass: multiDesign) {
//            writeToJavaFiles.write(concreteClass);
//        }
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
//        {
//            concreteClassdesign = new ArrayList<>();
//            mainInterfacedesign = TypeSpec
//                    .interfaceBuilder(userInput.interfaceName)
//                    //main abstract class of facade design pattern.
//                    .addModifiers(Modifier.PUBLIC)
//                    .addMethod(MethodSpec
//                            // method that will call all other concrete methods
//                            .methodBuilder(userInput.overridingMethodNames)
//                            .addModifiers(Modifier.PUBLIC,Modifier.ABSTRACT)
//                            .build())
//                    .build();
//            for (int i = 0; i < userInput.numberOfConcreteClasses; i++) {
//                TypeSpec element = TypeSpec
//                        .classBuilder(userInput.concreteClassNames.get(i))
//                        .addSuperinterface(TypeVariableName.get(userInput.interfaceName))
//                        //concrete classes of facade design pattern.
//                        .addModifiers(Modifier.PUBLIC) //
//                        .addMethod(MethodSpec
//                                //overriding method in the main abstract class of factory
//                                .methodBuilder(userInput.overridingMethodNames)
//                                .addAnnotation(Override.class)
//                                .addModifiers(Modifier.PUBLIC)
//                                .build())
//                        .build();
//                concreteClassdesign.add(element);
//            }
//            userInput.compositeClass = userInput.interfaceName+"Composite";
//            compositeFlag = 1; // composite called TODO make it better
//            compositeDesign = TypeSpec
//                    .classBuilder(userInput.compositeClass)
//                    .addSuperinterface(TypeVariableName.get(userInput.interfaceName))
//                    //concrete classes of facade design pattern.
//                    .addModifiers(Modifier.PUBLIC) //
//                    .addMethod(MethodSpec
//                            //overriding method in the main abstract class of factory
//                            .methodBuilder(userInput.overridingMethodNames)
//                            .addAnnotation(Override.class)
//                            .addModifiers(Modifier.PUBLIC)
//                            .build())
//                    .build();
//
//            userInput.mainClass = "CompositeClient";
//            clientClassDesign = TypeSpec
//                    .classBuilder(userInput.mainClass)
//                    .addModifiers(Modifier.PUBLIC)
//                    .addMethod(MethodSpec
//                            .methodBuilder("main") //TODO change name
//                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//                            .addParameter(String[].class, "args")
//                            //TODO
//                            .addComment("You can use this method to __. Example:")
//                            //TODO better way? new keyword
//                            .addStatement(userInput.concreteClassNames.get(0)+" "+userInput.concreteClassNames.get(0)+"Object = new "+userInput.concreteClassNames.get(0)+"()")
//                            .addComment("Do something with "+userInput.concreteClassNames.get(0)+"Object")
//                            .addStatement(userInput.compositeClass+" "+userInput.compositeClass+"Object = new "+userInput.compositeClass+"()")
//                            //TODO use codeblocks
//                            .addStatement(userInput.compositeClass+"Object."+userInput.overridingMethodNames+"()")
//                            .returns(void.class)
//                            .build())
//                    .build();
//        }
//    }
}