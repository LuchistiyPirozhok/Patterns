package dao;

import impl.Schoolgirl;

import java.io.*;

/**
 * Created by Dmitry on 19.02.2017.
 */
public class SerializeSchoolgirlDAO implements ISchoolgirlDAO {
    @Override
    public void saveObject(Schoolgirl schoolgirl, String path) {
        File f=new File(path);
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try(FileOutputStream fos=new FileOutputStream(path);
            ObjectOutputStream oos=new ObjectOutputStream(fos)) {

            oos.writeObject(schoolgirl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Schoolgirl readObject(String path) {
        try(FileInputStream fos=new FileInputStream(path);
            ObjectInputStream ois=new ObjectInputStream(fos)){
            Schoolgirl schoolgirl= (Schoolgirl) ois.readObject();
            return schoolgirl;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
