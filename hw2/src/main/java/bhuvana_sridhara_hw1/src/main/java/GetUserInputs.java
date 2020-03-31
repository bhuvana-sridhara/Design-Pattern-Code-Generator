package bhuvana_sridhara_hw1.src.main.java;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import com.bhuvana.designpatterndemo.ParseConfig;

public class GetUserInputs{

    UserInput input = new UserInput();
    Scanner in = new Scanner(System.in);

    String designPattern;


    List listOfDesignPatterns;
            //= config.getList("conf.available_design_patterns").unwrapped();
    ParseConfig parse = new ParseConfig();

    //static Config config = ConfigFactory.parseResources("Config.conf");
    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);

    public UserInput getInputs() throws InvalidPatternException {

        listOfDesignPatterns = Collections.singletonList(parse.parseConfig("conf.available_design_patterns"));

        int i;
        String s;

        InputHelperFacade inputHelperFacade = new InputHelperFacade();
        //Display all patterns
//        displayAllPatterns();
        inputHelperFacade.displayAllPatterns();

        System.out.println("Choose your pattern! (1-22)");
        //Setter for selected pattern
        i = in.nextInt();
//        setSelectedPattern(i);
        inputHelperFacade.setSelectedPattern(i);
        //logger.error("User selected pattern: "+input.selectedPattern);
        //logger.error("User selected pattern: "+input.selectedPattern);
        in.nextLine();

        designPattern = listOfDesignPatterns.get(inputHelperFacade.returnValue().selectedPattern-1).toString();
        System.out.println("Implementing "+designPattern+"...");

        //Prompt main interface name
        System.out.println("Enter name for main "+designPattern+" class/interface");
        s = in.nextLine();
        //setMainInterfaceName(s);
        inputHelperFacade.setMainInterfaceName(s);

        //Prompt number of classes implementing main interface
        System.out.println("Enter number of concrete classes implementing "+designPattern+" class/interface "+inputHelperFacade.returnValue().interfaceName);
//        setNumberOfConcreteClasses(i);
        i = in.nextInt();
        inputHelperFacade.setNumberOfConcreteClasses(i);


        in.nextLine();
        //Prompt classname for each of concrete classes implementing main interface
        System.out.println("Enter name for "+inputHelperFacade.returnValue().numberOfConcreteClasses+" class(es) implementing "+inputHelperFacade.returnValue().interfaceName);
//        setConcreteClasses();
//        in.nextLine();
        inputHelperFacade.setConcreteClasses();

        //Prompt number of abstract methods defined in the interface/class
        System.out.println("Enter number of abstract methods in "+inputHelperFacade.returnValue().interfaceName+ " class");
        i = in.nextInt();
//        setNumberOfAbstractMethods(i);
        inputHelperFacade.setNumberOfAbstractMethods(i);

        in.nextLine();

        //Prompt names of abstract methods defined in the interface/class
        System.out.println("Enter name of abstract method in "+inputHelperFacade.returnValue().interfaceName+ " class");
//        setAbstractMethods();
        inputHelperFacade.setAbstractMethods();

        input = inputHelperFacade.returnValue();
        return input;
    }
}