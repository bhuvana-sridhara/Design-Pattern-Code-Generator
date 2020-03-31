package bhuvana_sridhara_hw1.src.main.java;

import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;

/**
 *Create abstract class DesignPattern
 */
class DesignPattern {

    protected TypeSpec mainInterfacedesign;
    protected ArrayList<TypeSpec> concreteClassdesign = new ArrayList<>();
    protected ArrayList<TypeSpec> factoryDesign = new ArrayList<>();
    protected TypeSpec mainFactoryDesign;
    protected TypeSpec clientClassDesign;
    protected TypeSpec compositeDesign;
    protected TypeSpec facadeDesign;

}