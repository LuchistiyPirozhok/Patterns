package mvc;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.*;

/**
 * Created by Dmitry on 19.02.2017.
 */
public class Model {
    private Map<Double,Double> points;
    private IFunction function;
    private Set<IModelListener> listeners;

    public Model(IFunction function){
        this.function=function;
        points=new HashMap<>();
        listeners=new HashSet<>();
    }

    public void addListener(IModelListener listener){
        listeners.add(listener);
    }
    public void removeListener(IModelListener listener){
        listeners.remove(listener);
    }

    public void addPoint(double x){
        if(points.containsKey(x)){
            return;
        }
        double y=function.countValue(x);
        points.put(x,y);
        notifyListeners();
    }
    public void removePoint(double x){
        if(!points.containsKey(x)){
            return;
        }
        points.remove(x);
        notifyListeners();
    }

    private void notifyListeners() {
        Set<Point> pts=new TreeSet<>(POINT_COMPARATOR);
        points.keySet().forEach(x->pts.add(new Point(x,points.get(x))));
        listeners.forEach(l->l.onModelUpdate(pts));
    }

    private static final Comparator<Point> POINT_COMPARATOR= (o1, o2) -> Double.compare(o1.getX(),o2.getX());


}
