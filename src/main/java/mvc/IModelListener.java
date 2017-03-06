package mvc;

import java.util.Set;

/**
 * Created by Dmitry on 19.02.2017.
 */
public interface IModelListener {
    void onModelUpdate(Set<Point> points);
}
