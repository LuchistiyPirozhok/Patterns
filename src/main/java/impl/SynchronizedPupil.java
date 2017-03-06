package impl;

import interfaces.Pupil;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class SynchronizedPupil implements Pupil {
    private Pupil pupil;
    private Object key=new Object();

    public SynchronizedPupil(Pupil pupil){
        this.pupil=pupil;
    }

    @Override
    public String getName() {
        synchronized (key) {
            return pupil.getName();
        }
    }

    @Override
    public void setName(String name) {
        synchronized (key) {
            pupil.setName(name);
        }
    }

    @Override
    public String getSubject(int idx) {
        synchronized (key){
            return pupil.getSubject(idx);
        }
    }

    @Override
    public void setSubject(int idx, String value) {
        synchronized (key){
            pupil.setSubject(idx,value);
        }
    }

    @Override
    public int getMark(int idx) {
        synchronized (key){
            return pupil.getMark(idx);
        }
    }

    @Override
    public void setMark(int idx, int value) {
        synchronized (key){
            pupil.setMark(idx,value);
        }
    }

    @Override
    public void addSubject(String name, int value) {
        synchronized (key){
            pupil.addSubject(name,value);
        }
    }

    @Override
    public int getSubjectCount() {
        synchronized (key){
            return pupil.getSubjectCount();
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        synchronized (key){
            return pupil.clone();
        }
    }
}
