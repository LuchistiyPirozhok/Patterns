package strategy;

import impl.Pupils;
import interfaces.Pupil;

import java.util.Arrays;

/**
 * Created by Dmitry on 19.02.2017.
 */
public class DescendingSort implements  ISortingStrategy{
    @Override
    public void sort(Pupil[] pupils) {
        Arrays.sort(pupils,(a,b)->{
            double dif=Pupils.getAverageMark(a)-Pupils.getAverageMark(b);
            if(dif>0){
                return -1;
            }else if(dif==0){
                return 0;
            }else {
                return 1;
            }
        });
    }
}
