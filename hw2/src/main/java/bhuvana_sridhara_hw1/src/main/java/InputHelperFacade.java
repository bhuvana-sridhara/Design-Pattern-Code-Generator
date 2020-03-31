package bhuvana_sridhara_hw1.src.main.java;

import com.bhuvana.designpatterndemo.ParseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InputHelperFacade{
    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
//all my functions
    UserInput input = new UserInput();
    Scanner in = new Scanner(System.in);
    //static Config config = ConfigFactory.parseResources("Config.conf");

    ParseConfig parse = new ParseConfig();
    String numberOfDesignPatterns;
    List listOfDesignPatterns;

    public InputHelperFacade(){
        numberOfDesignPatterns = parse.parseConfig("conf.number_of_design_patterns");
        listOfDesignPatterns = Collections.singletonList(parse.parseConfig("conf.available_design_patterns"));
    }
//    int numberOfDesignPatterns = config.getInt("conf.number_of_design_patterns");
//    List listOfDesignPatterns = config.getList("conf.available_design_patterns").unwrapped();

    void displayAllPatterns(){
        System.out.println("Design patterns available:");
        for(int i = 1;i <= Integer.parseInt(numberOfDesignPatterns); i++){
            System.out.println(i+": "+listOfDesignPatterns.get(i-1));
        }
    }

    public void setSelectedPattern(Integer i) throws InvalidPatternException {
        input.selectedPattern = i;
        if(input.selectedPattern <1 || input.selectedPattern > 22){
            logger.error("Inside InputHelperFacade. Invalid Pattern selected");
            throw new InvalidPatternException("Invalid pattern selected!");
        }
    }

    public void setMainInterfaceName(String s){
        input.interfaceName = s;
    }

    public void setNumberOfConcreteClasses(Integer i){
        input.numberOfConcreteClasses = i;
    }

    public void setConcreteClasses(){
        for(int i = 0; i < input.numberOfConcreteClasses; i++){
            System.out.println("Class "+(i+1)+":");
            String classname = in.nextLine();
            input.concreteClassNames.add(classname);
        }
    }

    public void setNumberOfAbstractMethods(Integer i){
        input.numberOfAbstractClasses = i;
    }

    public void setAbstractMethods(){
        for(int i = 0; i < input.numberOfAbstractClasses; i++){
            System.out.println("Method "+(i+1)+":");
            String classname = in.nextLine();
            input.overridingMethodNames.add(classname);
        }
    }

    public UserInput returnValue(){
        return input;
    }

}
