package bhuvana_sridhara_hw1.src.main.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Program execution begins
 */
public class Executor {

    private static final Logger logger = LoggerFactory.getLogger(Executor.class);
    public static void main (String[] args) throws IOException, InvalidPatternException {
        logger.debug("Beginning of the program");

        /**
         *  Get all inputs from GetUserInputs
         */
        GetCodeFactory getCodeFactory = new GetCodeFactory();
        UserInput userInput;
        GetUserInputs input = new GetUserInputs();

        userInput = input.getInputs();
        logger.debug("Got user inputs! "+userInput.interfaceName);

        /**
         * Invoke getDesignPattern() and getCode()
         * Use the Factory to get object of concrete class by passing an information such as selectedPattern
         */
        DesignPatternBuilder designPatternBuilder = getCodeFactory.getDesignPattern(userInput.selectedPattern);
        logger.debug("Got required design pattern for "+userInput.interfaceName+" !");
        designPatternBuilder.getCode(userInput);
    }
}