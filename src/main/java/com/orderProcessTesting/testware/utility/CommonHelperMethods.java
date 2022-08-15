package com.orderProcessTesting.testware.utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonHelperMethods {
    Logger log = LoggerFactory.getLogger(this.getClass());

    public Map<String, String> returnKeyValuesFromCurrentEnvPropertyFile(String[] propertyNames){

        LinkedHashMap<String, String> values = new LinkedHashMap<String, String>() ;
        String host = Constants.HOST;

        if (StringUtils.isBlank(Constants.HOST)) {
            host = Constants.ACTIVE_ENV;
        }

        String filePath = Constants.ENV_FILE_PATH + host + ".properties";
        log.info("reading Filepath :: "+ filePath);

        FileReader reader = null;
        Properties prop = null;

        try {
            reader = new FileReader(filePath);
            prop = new Properties();
            prop.load(reader);
            for(String property : propertyNames){
                if (prop.getProperty(property) == null) {
                    values.put(property, "");
                }
                else {
                    values.put(property,prop.getProperty(property));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("returning value :: "+ values.toString());
        return values;

    }

    public String returnConcatenatedBaseURI(String protocol, String baseUrl) {
        Map<String, String> values = returnKeyValuesFromCurrentEnvPropertyFile(new String[]{ protocol, baseUrl});
        return new String(values.get(protocol) + values.get(baseUrl));
    }
}
