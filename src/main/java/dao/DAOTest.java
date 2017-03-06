package dao;

import impl.Pupils;
import impl.Schoolgirl;

/**
 * Created by Dmitry on 19.02.2017.
 */
public class DAOTest {
    public static void main(String[] args) {
        ISchoolgirlDAO dao1=new SerializeSchoolgirlDAO();
        ISchoolgirlDAO dao2=new TextSchoolgirlDAO();

        Schoolgirl schoolgirl=new Schoolgirl("Ivanova",4);
        schoolgirl.setMark(0,3);
        schoolgirl.setMark(1,4);
        schoolgirl.setMark(2,5);
        schoolgirl.setMark(3,4);

        schoolgirl.setSubject(0,"subj1");
        schoolgirl.setSubject(1,"subj2");
        schoolgirl.setSubject(2,"subj3");
        schoolgirl.setSubject(3,"subj4");
        String pathSerializable=DAOTest.class.getResource("/dao").getPath()+"/sg.dao";
        String pathText=DAOTest.class.getResource("/dao").getPath()+"/sg.txt";
        dao1.saveObject(schoolgirl,pathSerializable);
        dao2.saveObject(schoolgirl,pathText);

        Schoolgirl serialSchoolgirl=dao1.readObject(pathSerializable);
        Schoolgirl txtSchoolgirl=dao2.readObject(pathText);

        System.out.println("-----------------------------------------------\nЧтение файла .dao\n-----------------------------------------------");
        Pupils.printMarks(serialSchoolgirl);
        System.out.println("-----------------------------------------------\nЧтение файла .txt\n-----------------------------------------------");
        Pupils.printMarks(txtSchoolgirl);



    }
}
