package impl;

import interfaces.Pupil;
import interfaces.PupilFactory;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class SchoolgirlFactory implements PupilFactory{
    @Override
    public Pupil createInstance(String name, int subjectCount) {
        return new Schoolgirl(name,subjectCount);
    }
}
