package bhuvana_sridhara_hw1.src.main.java;

import java.util.ArrayList;

public class UserInput {
    public int selectedPattern = -1;
    public String interfaceName = null; // interface
    public int numberOfConcreteClasses = 0;
    public int numberOfAbstractClasses = 0;
    public int numberOfFactories = 1; //minimum 1 factory class in case factory method
    public ArrayList<String> concreteClassNames = new ArrayList<>();
    public ArrayList<String> concreteClassMethods = new ArrayList<>();
    public ArrayList<String> factoryClasses = new ArrayList<>();
    public String facadeClassName = new String();
    public ArrayList<String> facadeClassMethods = new ArrayList<>();
    public String implementedMethodName = new String();
    public ArrayList<String> overridingMethodNames = new ArrayList<String>();
    public String mainFactory = new String();
    public String mainClass = new String();
    public String compositeClass = new String();
    public String superInterfaceName;
    public String product;
    public String inputMode;
}
