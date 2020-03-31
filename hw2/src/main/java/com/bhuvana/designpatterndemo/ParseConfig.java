package com.bhuvana.designpatterndemo;


import bhuvana_sridhara_hw1.src.main.java.InputHelperFacade;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;



//import static jdk.xml.internal.SecuritySupport.getResourceAsStream;


public class ParseConfig {
    public String parseConfig(String path){
        String result = "";
        try {
            //TODO fix config reader
            InputStream inStream = GetInputAction.class.getResourceAsStream("Conf.conf");
            if (inStream != null) {
                Reader reader = new InputStreamReader(inStream);

                // Load the configuration file
                Config config = ConfigFactory.parseReader(reader);

                // Load the destination directory for designs being generated
                 result = config.getString(path);

                inStream.close();
//                System.out.println("Returning inside"+result);
                return result;
               // logger.info("DIRECTORY:"+directory);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("Returning "+result);
        return result;
    }
}
