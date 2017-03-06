package strategy;

import interfaces.Pupil;

/**
 * Created by Dmitry on 19.02.2017.
 */
public class PupilSorter {

    private ISortingStrategy strategy;

    public PupilSorter(){
        strategy=new AscendingSort();
    }
    public void setStrategy(ISortingStrategy strategy){
        this.strategy=strategy;
    }
    public void sortPupils(Pupil[] pupils){
        strategy.sort(pupils);
    }

}
