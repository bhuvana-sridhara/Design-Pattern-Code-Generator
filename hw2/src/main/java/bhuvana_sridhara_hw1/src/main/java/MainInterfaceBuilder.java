package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class MainInterfaceBuilder extends DesignPattern{
    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);

    ArrayList<MethodSpec> methodBuilder = new ArrayList<>();
//Why static
    private void getMethods(UserInput userInput) {
        //ArrayList<MethodSpec> methodBuilder = new ArrayList<>();
        MethodSpec element;

        for(String methodName: userInput.overridingMethodNames){
            element = MethodSpec.methodBuilder(methodName)
                  .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                  .returns(void.class)
                   .build();
          methodBuilder.add(element);
        }
    }
    public TypeSpec build(UserInput userInput) {


       // ArrayList<MethodSpec> methodBuilder = null;
        getMethods(userInput);

        logger.debug("Building main interface "+userInput.interfaceName);
        mainInterfacedesign = TypeSpec
                .interfaceBuilder(userInput.interfaceName)
                //main abstract class of factory method design pattern.
                .addModifiers(Modifier.PUBLIC) // all other concrete classes will extend this
                .addMethods(methodBuilder)
                .build();

        return mainInterfacedesign;
    }

    /**
     *  If main interface has super interface: Like in Prototype pattern
     */

    public TypeSpec build(UserInput userInput,boolean hasSuperInterface){

        getMethods(userInput);

        logger.debug("Building main interface "+userInput.interfaceName);
        //TODO add clone() implementation
            mainInterfacedesign = TypeSpec
                    .interfaceBuilder(userInput.interfaceName)
                    //main abstract class of factory method design pattern.
                    .addSuperinterface(TypeVariableName.get(userInput.superInterfaceName))
                    .addModifiers(Modifier.PUBLIC) // all other concrete classes will extend this
                    .addMethods(methodBuilder)
                    .build();
            return mainInterfacedesign;
        }

}

