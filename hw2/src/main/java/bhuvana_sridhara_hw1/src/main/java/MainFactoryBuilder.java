package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Modifier;
public class MainFactoryBuilder extends DesignPattern {
    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
    public TypeSpec build(UserInput userInput) {
        logger.debug("Building main factory class "+userInput.interfaceName);
        mainFactoryDesign = TypeSpec
                .classBuilder(userInput.mainFactory)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(MethodSpec
                        .methodBuilder(userInput.implementedMethodName)
                        .addModifiers(Modifier.PUBLIC)
                        .addComment("You can use this method to get object of type " + userInput.interfaceName + ". Example:")
                        .addStatement("return $T()", TypeVariableName.get(userInput.factoryClasses.get(0)+"."+userInput.implementedMethodName))
                        .returns(TypeVariableName.get(userInput.interfaceName))
                        .build())
                .build();
        return mainFactoryDesign;
    }
}
