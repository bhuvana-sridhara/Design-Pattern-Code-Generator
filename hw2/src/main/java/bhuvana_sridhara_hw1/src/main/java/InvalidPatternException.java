package bhuvana_sridhara_hw1.src.main.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidPatternException extends Exception {
    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);

    public InvalidPatternException(String errorMessage) {

        super(errorMessage);
    }
}