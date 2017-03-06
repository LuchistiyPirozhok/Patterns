package interfaces;

/**
 * Created by Dmitry on 18.02.2017.
 */
public interface Pupil extends Cloneable{
    String getName();
    void setName(String name);

    String getSubject(int idx);
    void setSubject(int idx,String value);

    int getMark(int idx);
    void setMark(int idx,int value);

    void addSubject(String name,int value);
    int getSubjectCount();
    Object clone() throws CloneNotSupportedException;
}
