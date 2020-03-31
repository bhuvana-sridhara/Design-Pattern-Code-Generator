package bhuvana_sridhara_hw1.src.main.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetCodeFactory {

    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
    public DesignPatternBuilder getDesignPattern(int pattern){


        /**
         * use getDesignPattern to generate object of type DesignPatternBuilder
         * based on user input.
         *
         */

        switch (pattern){
            case 1: return new AbstractFactory();

            case 2: return new Builder();

            case 3: return new FactoryMethod();

            case 4: return new Facade();

            case 5: return new ChainOfResponsibility();

            case 6: return new Mediator();

            case 7: return new Visitor();

            case 8: return new TemplateMethod();

            case 9: return new Prototype();

            case 10: return new Adapter();

            case 11: return new Bridge();

            case 12: return new Composite();

            case 13: return new Decorator();

            case 14: return new Flyweight();

            case 15: return new Proxy();

            case 16: return new Command();

            case 17: return new Interpreter();

            case 18: return new Iterator();

            case 19: return new Memento();

            case 20: return new Observer();

            case 21: return new State();

            case 22: return new Strategy();
        }
        logger.error("Inside GetCodeFactory. Invalid Pattern "+pattern);
        return null;
    }
}