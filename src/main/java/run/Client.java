package run;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class Client {
    private static final String PATH="localhost";
    private static final int PORT=5000;
    private static Double proxyMultiply(double x, double y){
        try(Socket client=new Socket(PATH,PORT);
            DataOutputStream dos=new DataOutputStream(client.getOutputStream());
            DataInputStream dis=new DataInputStream(client.getInputStream())){
            dos.writeDouble(x);
            dos.writeDouble(y);
            return dis.readDouble();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(proxyMultiply(5,6));
    }
}
