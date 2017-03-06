package impl;

import interfaces.Pupil;
import interfaces.PupilFactory;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class Pupils {

    private static PupilFactory factory = new StudentFactory();

    public static void setPupilFactory(PupilFactory newFactory){
       factory=newFactory;
    }

    public static Pupil createInstance(String name, int size){
        return  factory.createInstance(name,size);
    }

    public static void printMarks(Pupil pupil){
        int count = pupil.getSubjectCount();
        for(int i=0;i<count;i++){
            System.out.println("Subject:"+ pupil.getSubject(i) + ",\tMark:"+pupil.getMark(i));
        }
    }
    public static double getAverageMark(Pupil pupil){
        int count = pupil.getSubjectCount();
        double sum=0;
        for(int i=0;i<count;i++){
            sum+=pupil.getMark(i);
        }
        return sum/count;
    }
    public static Pupil synchronizedPupil (Pupil p){
        return new SynchronizedPupil(p);
    }
}
