package run;

import impl.Pupils;
import impl.Schoolgirl;
import impl.Student;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Student student=new Student("Борис", 1);
        student.setMark(0,5);
        student.setSubject(0,"СЯП");

        Student cloneStudent=(Student) student.clone();
        System.out.println("---------------------------------");
        System.out.println("До изменения");
        System.out.println("---------------------------------");
        Pupils.printMarks(student);
        Pupils.printMarks(cloneStudent);

        cloneStudent.setSubject(0,"Виртуозная езда на уницикле");
        cloneStudent.setMark(0,2);

        System.out.println("---------------------------------");
        System.out.println("После изменения");
        System.out.println("---------------------------------");
        Pupils.printMarks(student);
        Pupils.printMarks(cloneStudent);


        Schoolgirl schoolgirl=new Schoolgirl("Генадий", 1);
        schoolgirl.setMark(0,4);
        schoolgirl.setSubject(0,"СИИ");

        Schoolgirl cloneSchoolgirld=(Schoolgirl) schoolgirl.clone();
        System.out.println("---------------------------------");
        System.out.println("До изменения");
        System.out.println("---------------------------------");
        Pupils.printMarks(schoolgirl);
        Pupils.printMarks(cloneSchoolgirld);

        cloneSchoolgirld.setSubject(0,"БЖД");
        cloneSchoolgirld.setMark(0,5);

        System.out.println("---------------------------------");
        System.out.println("После изменения");
        System.out.println("---------------------------------");
        Pupils.printMarks(schoolgirl);
        Pupils.printMarks(cloneSchoolgirld);



    }
}
