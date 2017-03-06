package run;

import impl.Pupils;
import impl.SchoolgirlFactory;
import interfaces.Pupil;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        Pupil sadStudent= Pupils.createInstance("Борис",3);

        sadStudent.setMark(0,3);
        sadStudent.setMark(1,2);
        sadStudent.setMark(2,3);

        sadStudent.setSubject(0,"Матан");
        sadStudent.setSubject(1,"Дифуры");
        sadStudent.setSubject(2,"Физика");

        sadStudent.addSubject("Линал",3);

        Pupils.printMarks(sadStudent);
        System.out.println("Средняя оценка:"+Pupils.getAverageMark(sadStudent));

        Pupils.setPupilFactory(new SchoolgirlFactory());

        Pupil schoolgirl=Pupils.createInstance("Генадий",2);

        schoolgirl.setMark(0,4);
        schoolgirl.setMark(1,5);

        schoolgirl.setSubject(0,"Но это");
        schoolgirl.setSubject(1,"уже совсем");

        schoolgirl.addSubject("другая история",4);

        Pupils.printMarks(schoolgirl);
        System.out.println("Средняя оценка:"+Pupils.getAverageMark(schoolgirl));

        System.out.println("Student:"+sadStudent.getClass());
        System.out.println("Schoolgirl:"+schoolgirl.getClass());
    }
}
