package run;

import impl.ArrayOutputStreamAdapter;
import interfaces.IOutputStreamTarget;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class AdapterTest {
    public static void main(String[] args) {
        IOutputStreamTarget iost=new ArrayOutputStreamAdapter(System.out);
        String [] strings= {
                "string1\n",
                "string2\n",
                "string3\n",
                "string4\n",
                "string5\n",
                "string6\n",
                "string7\n",
                "string8\n",
                "string9\n",
                "string10\n",
        };
        iost.writeStrings(strings);
    }
}
