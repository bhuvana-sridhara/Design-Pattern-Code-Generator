package bhuvana_sridhara_hw1.src.main.java;

import java.io.IOException;

//Builder class. All components are slowly built step by step inside each of the extending classes
//by overriding some/all of these classes
public abstract class DesignPatternBuilder {
     protected DesignPattern designpattern = new DesignPattern();
     //exception is propagated to outer layers by javafile.writeto statements inside
     public abstract void getCode(UserInput userInput) throws IOException;
     public abstract void buildMainInterface(UserInput userInput) throws IOException;
     public abstract void buildConcreteClasses(UserInput userInput) throws IOException;
     public abstract void buildOverridingMethods(UserInput userInput) throws IOException;
     public abstract void buildFactory(UserInput userInput) throws IOException;
     public abstract void buildMainFactory(UserInput userInput) throws IOException;
     public abstract void buildClientClass(UserInput userInput) throws IOException;

}
