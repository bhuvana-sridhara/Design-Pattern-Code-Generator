package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class FactoriesBuilder extends DesignPattern{
    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
    public ArrayList<TypeSpec> build(UserInput userInput) {
        for (int i = 0; i < userInput.numberOfConcreteClasses; i++) {
            TypeSpec element = TypeSpec
                    .classBuilder(userInput.factoryClasses.get(i))
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(MethodSpec
                            .methodBuilder(userInput.implementedMethodName)
                            .addModifiers(Modifier.PUBLIC)
                            .addComment("You can use this method to get object of type " + userInput.interfaceName + ". Example:")
                            .addStatement("return new $T()", (TypeVariableName.get(userInput.concreteClassNames.get(0))))
                            .returns(TypeVariableName.get(userInput.interfaceName))
                            .build())
                    .build();
            factoryDesign.add(element);
        }
        return factoryDesign;
    }
}