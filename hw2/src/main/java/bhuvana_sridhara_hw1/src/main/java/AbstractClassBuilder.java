package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class AbstractClassBuilder extends DesignPattern {

    ArrayList<MethodSpec> methodBuilder = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);

    private void getMethods(UserInput userInput) {
        //ArrayList<MethodSpec> methodBuilder = new ArrayList<>();
        MethodSpec element;

        for(String methodName: userInput.overridingMethodNames){
            element = MethodSpec.methodBuilder(methodName)
                    .addModifiers(Modifier.PUBLIC)
                    .returns(void.class)
                    .build();
            methodBuilder.add(element);
        }
    }

    public TypeSpec build(UserInput userInput) {

        getMethods(userInput);
        logger.debug("Building abstract class "+userInput.interfaceName);
        mainInterfacedesign = TypeSpec
                .classBuilder(userInput.interfaceName)
                .addModifiers(Modifier.PUBLIC,Modifier.ABSTRACT)
                .addMethods(methodBuilder)
                .addMethod(MethodSpec
                        .methodBuilder("check") //TODO change name
                        .addModifiers(Modifier.PUBLIC)
                        .addComment("Checking if the receiver can handle this request")
                        .addStatement("return false")
//                        .addStatement(userInput.interfaceName+" "+userInput.interfaceName+"Object = new "+userInput.concreteClassNames.get(0)+"()")
//                        .addStatement(userInput.interfaceName+"Object."+userInput.overridingMethodNames.get(0)+"()")
                        .returns(boolean.class)
                        .build())
                .build();
        return mainInterfacedesign;
    }

}
