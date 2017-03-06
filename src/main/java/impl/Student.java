package impl;

import interfaces.Pupil;

import java.util.Arrays;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class Student implements Pupil{
    private String name;
    private int [] marks;
    private String [] subjects;

    public Student(String name,int subjectCount){
        this.name=name;
        this.marks=new int[subjectCount];
        this.subjects=new String[subjectCount];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(isValidString(name)){
            this.name = name;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getMark(int idx){
        if(isInsideArray(idx)){
            return marks[idx];
        }
        return -1;
    }

    public void setMark(int idx,int value){
        if(isInsideArray(idx) && isMarkInRange(value)){
            marks[idx]=value;
        }else{
            throw new IllegalArgumentException();
        }

    }

    public String getSubject(int idx){
        if(isInsideArray(idx)){
            return subjects[idx];
        }
        return null;
    }

    public void setSubject(int idx,String value){
        if(isInsideArray(idx) && isValidString(value)){
            subjects[idx]=value.trim();
        }else{
            throw new IllegalArgumentException();
        }

    }

    public void addSubject(String subjectName, int mark){
        if(isMarkInRange(mark) &&
                subjectName!=null &&
                subjectName.trim().length()>0){
            this.subjects = Arrays.copyOf(this.subjects,this.subjects.length+1);
            this.marks    = Arrays.copyOf(this.marks,this.marks.length+1);

            subjects[subjects.length-1]=subjectName;
            marks[marks.length-1]=mark;
        }else{
            throw new IllegalArgumentException();
        }

    }

    public int getSubjectCount(){
        return marks.length;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        Student copy= (Student) super.clone();
        copy.subjects=this.subjects.clone();
        copy.marks=this.marks.clone();
        return copy;
    }

    private boolean isValidString(String value) {
        return value != null && value.trim().length() > 0;
    }

    private boolean isInsideArray(int idx){
        return idx>=0 && idx<marks.length;
    }

    private boolean isMarkInRange(int value){
        return value>=2 && value<=5;
    }
}
