package bhuvana_sridhara_hw1.src.main.java;

import com.bhuvana.designpatterndemo.WriterHelperClass;
import com.intellij.openapi.project.Project;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteToJavaFiles extends DesignPattern{

    private static final Logger logger = LoggerFactory.getLogger(GetUserInputs.class);
//    Config config = ConfigFactory.parseResources("Config.conf");
//    Path outputDirectory = Paths.get(config.getString("conf.path_file"));
    //Filer filer;
    static Path outputDirectory;
    static String packageName = "";

    public void write(TypeSpec design) {

//        try {
//            Files.createDirectories(outputDirectory);
//        } catch (IOException e) {
//            logger.error("Error while creating directory");
//            e.printStackTrace();
//        }
        //File directory = new File();

        JavaFile file = JavaFile
                //TODO userinput
                //.builder(config.getString("conf.package"), design)
                .builder(packageName,design)
                .indent("   ")
                .build();
        try {
            file.writeTo(System.out);
            System.out.println("outputDirectory = "+outputDirectory);
            file.writeTo(outputDirectory);
        } catch (IOException e) {
           // logger.error("Error while writing to files");
            e.printStackTrace();
        }
    }

    public void setValues(String path, String packagename){
        outputDirectory = Paths.get(path);
        packageName = "src/"+packagename;
    }
}
