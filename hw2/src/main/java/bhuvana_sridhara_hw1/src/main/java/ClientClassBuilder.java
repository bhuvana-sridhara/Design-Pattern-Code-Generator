package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Modifier;


public class ClientClassBuilder extends DesignPattern {
    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
    public TypeSpec build(UserInput userInput) {

        userInput.mainClass = "Client"; //TODO change

        clientClassDesign = TypeSpec
                .classBuilder(userInput.mainClass)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(MethodSpec
                        .methodBuilder("main") //TODO change name
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(String.class, "args")
                        //TODO
                        .addComment("You can use this method to __. Example:")
                        //TODO better way? new keyword
                        .addStatement(userInput.interfaceName+" "+userInput.interfaceName+"Object = new "+userInput.concreteClassNames.get(0)+"()")
                        .addStatement(userInput.interfaceName+"Object."+userInput.overridingMethodNames.get(0)+"()")
                        .returns(void.class)
                        .build())
                .build();
        return clientClassDesign;
    }
}
