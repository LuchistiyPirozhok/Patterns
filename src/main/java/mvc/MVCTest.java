package mvc;

/**
 * Created by Dmitry on 19.02.2017.
 */
public class MVCTest {
    public static void main(String[] args) {
       new Controller("MVC", x -> Math.sin(x));
    }
}
