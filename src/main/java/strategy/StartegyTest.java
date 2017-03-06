package strategy;

import impl.Pupils;
import impl.Schoolgirl;
import impl.Student;
import interfaces.Pupil;

/**
 * Created by Dmitry on 19.02.2017.
 */
public class StartegyTest {
    public static void main(String[] args) {
        Pupil[] pupils=new Pupil[4];
        pupils[0]=new Student("Petrov",1);
        pupils[0].setSubject(0,"subj");
        pupils[0].setMark(0,2);

        pupils[1]=new Student("Ivanov",1);
        pupils[1].setSubject(0,"subj");
        pupils[1].setMark(0,4);

        pupils[2]=new Schoolgirl("Sidorova",1);
        pupils[2].setSubject(0,"subj");
        pupils[2].setMark(0,3);

        pupils[3]=new Schoolgirl("Vurst",1);
        pupils[3].setSubject(0,"subj");
        pupils[3].setMark(0,5);
        for(int i=0 ; i<pupils.length;i++){
            System.out.println(pupils[i].getName()+"-"+Pupils.getAverageMark(pupils[i]));
        }
        System.out.println("---------------------------------------------\nAscending\n---------------------------------------------");
        PupilSorter sorter=new PupilSorter();//Default ascending sort
        sorter.sortPupils(pupils);
        for(int i=0 ; i<pupils.length;i++){
            System.out.println(pupils[i].getName()+"-"+Pupils.getAverageMark(pupils[i]));
        }
        System.out.println("---------------------------------------------\nDescending\n---------------------------------------------");

        sorter.setStrategy(new DescendingSort());
        sorter.sortPupils(pupils);
        for(int i=0 ; i<pupils.length;i++){
            System.out.println(pupils[i].getName()+"-"+Pupils.getAverageMark(pupils[i]));
        }
    }
}
