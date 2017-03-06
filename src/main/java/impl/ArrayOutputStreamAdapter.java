package impl;

import interfaces.IOutputStreamTarget;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class ArrayOutputStreamAdapter implements IOutputStreamTarget {
    private OutputStream stream;
    private PrintWriter pw;

    public ArrayOutputStreamAdapter(OutputStream stream){
        this.stream=stream;
        this.pw=new PrintWriter(stream);
    }

    public void close() throws IOException {
        if(pw!=null){
            pw.close();
        }
    }

    @Override
    public void writeStrings(String[] strings) {
        if(strings==null) return;
        try{
            for(int i=0;i<strings.length;i++){
                pw.write(strings[i]);
            }
            pw.flush();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
