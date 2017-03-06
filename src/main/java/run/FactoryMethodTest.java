package run;

import impl.Pupils;
import impl.SchoolgirlFactory;
import interfaces.Pupil;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        Pupil sadStudent= Pupils.createInstance("�����",3);

        sadStudent.setMark(0,3);
        sadStudent.setMark(1,2);
        sadStudent.setMark(2,3);

        sadStudent.setSubject(0,"�����");
        sadStudent.setSubject(1,"������");
        sadStudent.setSubject(2,"������");

        sadStudent.addSubject("�����",3);

        Pupils.printMarks(sadStudent);
        System.out.println("������� ������:"+Pupils.getAverageMark(sadStudent));

        Pupils.setPupilFactory(new SchoolgirlFactory());

        Pupil schoolgirl=Pupils.createInstance("�������",2);

        schoolgirl.setMark(0,4);
        schoolgirl.setMark(1,5);

        schoolgirl.setSubject(0,"�� ���");
        schoolgirl.setSubject(1,"��� ������");

        schoolgirl.addSubject("������ �������",4);

        Pupils.printMarks(schoolgirl);
        System.out.println("������� ������:"+Pupils.getAverageMark(schoolgirl));

        System.out.println("Student:"+sadStudent.getClass());
        System.out.println("Schoolgirl:"+schoolgirl.getClass());
    }
}
