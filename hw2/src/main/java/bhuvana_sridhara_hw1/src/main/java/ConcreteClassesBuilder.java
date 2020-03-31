package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class ConcreteClassesBuilder extends DesignPattern {

    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
    public ArrayList<TypeSpec> build(UserInput userInput) {

        ArrayList<MethodSpec> methodBuilder = new ArrayList<>();
        MethodSpec element;

        for(String methodName: userInput.overridingMethodNames){
            element = MethodSpec.methodBuilder(methodName)
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC)
                    .returns(void.class)
                    .build();
            methodBuilder.add(element);
        }

        for (int i = 0; i < userInput.numberOfConcreteClasses; i++) {
            TypeSpec ele = TypeSpec
                    .classBuilder(userInput.concreteClassNames.get(i))
                    .addSuperinterface(TypeVariableName.get(userInput.interfaceName))
                    //concrete classes of factory method design pattern.
                    .addModifiers(Modifier.PUBLIC) // all other concrete classes will extend this
                    .addMethods(methodBuilder)
                    .build();
            concreteClassdesign.add(ele);
        }
        return concreteClassdesign;
    }

    //used for facade?
    public TypeSpec build(UserInput userInput, boolean isFacade) {
        ArrayList<MethodSpec> methodBuilder = new ArrayList<>();
        MethodSpec element;

        logger.debug("Building concrete class for "+userInput.interfaceName);
        for(String methodName: userInput.facadeClassMethods){
            element = MethodSpec.methodBuilder(methodName)
                    .addComment("Call overriding methods in each concrete class by creating an object of each class")
                    //.addComment("Example: "+userInput.concreteClassNames.get(0)+"."+userInput.overridingMethodNames.get(0)+"()")
                    .addModifiers(Modifier.PUBLIC)
                    .returns(void.class)
                    .build();
            methodBuilder.add(element);
        }

            facadeDesign = TypeSpec
                    .classBuilder(userInput.facadeClassName)
                    //concrete classes of factory method design pattern.
                    .addModifiers(Modifier.PUBLIC) // all other concrete classes will extend this
                    .addMethods(methodBuilder)
                    .build();
        return facadeDesign;
    }
}
