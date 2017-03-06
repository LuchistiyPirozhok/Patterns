package iterator;

import interfaces.Pupil;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class Schoolboy implements Pupil{
    private String name;
    private Register[] subjects;

    public Schoolboy(String name, int subjectCount){
        this.name=name;
        this.subjects=new Register[subjectCount];
        for(int i=0; i<subjectCount;i++){
            subjects[i]=new Register("empty",2);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (isValidString(name)) {
            this.name = name;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getSubject(int idx){
        if(isInsideArray(idx)){
            return subjects[idx].getSubject();
        }
        return null;
    }

    public void setSubject(int idx,String value){
        if(isInsideArray(idx) && isValidString(value)){
            subjects[idx].setSubject(value);
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getMark(int idx){
        if(isInsideArray(idx)){
            return subjects[idx].getMark();
        }
        return -1;
    }

    public void setMark(int idx,int value){
        if(isInsideArray(idx) && isMarkInRange(value)){
            subjects[idx].setMark(value);
        }else{
            throw new IllegalArgumentException();
        }

    }

    public void addSubject(String subjectName,int mark){
        if(isMarkInRange(mark) && isValidString(subjectName)){
            this.subjects= Arrays.copyOf(this.subjects,this.subjects.length+1);
            Register register=new Register(subjectName,mark);
            this.subjects[subjects.length-1]=register;
        }else{
            throw new IllegalArgumentException();
        }

    }

    public int getSubjectCount(){
        return subjects.length;
    }

    private boolean isInsideArray(int idx){
        return idx>=0 && idx<subjects.length;
    }

    private boolean isMarkInRange(int value){
        return value>=2 && value<=5;
    }
    private boolean isValidString(String value) {
        return value != null && value.trim().length() > 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Schoolboy clone= (Schoolboy) super.clone();
        clone.subjects=this.subjects.clone();
        for(int i=0; i<subjects.length;i++){
            clone.subjects[i]= (Register) clone.subjects[i].clone();
        }
        return clone;
    }

    public Iterator<Register> iterator(){
        return new SchoolboyIterator();
    }

    static class Register implements Cloneable{
        private String subject;
        private int mark;

        public Register(String subject, int mark) {
            this.subject = subject;
            this.mark = mark;
        }

        public int getMark() {
            return mark;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "Register{" +
                    "subject='" + subject + '\'' +
                    ", mark=" + mark +
                    '}';
        }

    }
    private class SchoolboyIterator implements Iterator<Register>{

        private int index=0;

        @Override
        public boolean hasNext() {
            return index<getSubjectCount();
        }

        @Override
        public Register next() {

            return subjects[index++];
        }
    }
}
