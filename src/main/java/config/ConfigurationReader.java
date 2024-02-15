package config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties configFile;
    static{
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/config/configuration.properties");
            configFile = new Properties();
            configFile.load(fileInputStream);
            fileInputStream.close();
        }catch (Exception e){

        }
    }
    public static String getProperty(String key){
        return configFile.getProperty(key);
    }
}
