package iterator;

import java.util.Iterator;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class IteratorTest {
    public static void main(String[] args) {
        Schoolboy boy=new Schoolboy("Йош",3);
        boy.setMark(0,4);
        boy.setMark(1,2);
        boy.setMark(2,5);

        boy.setSubject(0,"Матан");
        boy.setSubject(1,"Cатан");
        boy.setSubject(2,"Банан");

        Iterator<Schoolboy.Register> iterator=boy.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
