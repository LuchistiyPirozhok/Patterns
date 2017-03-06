package run;

import impl.SingletonProperties;

import java.util.Properties;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class SingletonTest {
    public static void main(String[] args) {
        Properties prop1=SingletonProperties.getInstance();
        Properties prop2=SingletonProperties.getInstance();

        prop1.keySet().forEach(key -> {
           System.out.println(key+"="+prop1.getProperty(key.toString()));
        });
        System.out.println("------------------------");
        prop2.keySet().forEach(key -> {
           System.out.println(key+"="+prop2.getProperty(key.toString()));
        });
    }
}
