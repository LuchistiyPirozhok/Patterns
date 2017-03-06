package dao;

import impl.Schoolgirl;

/**
 * Created by Dmitry on 19.02.2017.
 */
public interface ISchoolgirlDAO {
    void saveObject(Schoolgirl schoolgirl,String path);
    Schoolgirl readObject(String path);
}
