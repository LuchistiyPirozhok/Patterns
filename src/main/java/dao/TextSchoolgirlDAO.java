package dao;

import impl.Schoolgirl;

import java.io.*;


/**
 * Created by Dmitry on 19.02.2017.
 */
public class TextSchoolgirlDAO implements ISchoolgirlDAO {
    @Override
    public void saveObject(Schoolgirl schoolgirl, String path) {
        File f=new File(path);
        if(!f.exists()){
            try{
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try(FileWriter fw = new FileWriter(path);
            PrintWriter pw= new PrintWriter(fw)){
            pw.println(schoolgirl.getName());
            pw.println(schoolgirl.getSubjectCount());
            for(int i=0;i<schoolgirl.getSubjectCount();i++){
                pw.println(schoolgirl.getSubject(i));
                pw.println(schoolgirl.getMark(i));
            }
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Schoolgirl readObject(String path) {
        try(FileReader fr=new FileReader(path);
            BufferedReader br=new BufferedReader(fr)){
            String name=br.readLine();
            int subjectCount=Integer.parseInt(br.readLine());

            Schoolgirl schoolgirl=new Schoolgirl(name,subjectCount);
            for(int i=0;i<subjectCount;i++){
                schoolgirl.setSubject(i,br.readLine());
                schoolgirl.setMark(i,Integer.parseInt(br.readLine()));
            }
            return  schoolgirl;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
