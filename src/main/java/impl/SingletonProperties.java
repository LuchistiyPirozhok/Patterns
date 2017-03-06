package impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class SingletonProperties {

    private static Properties properties;
    private static final String PROPS_PATH="/config.properties";

    private SingletonProperties(){

    }
    public synchronized static Properties getInstance(){
        if(properties==null){
            try(InputStream is=SingletonProperties.class.getResourceAsStream(PROPS_PATH)){
                if(is==null){
                    throw new FileNotFoundException();
                }
                properties=new Properties();
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }






}
