package mvc;

/**
 * Created by Dmitry on 19.02.2017.
 */
public class Controller {
    private MainForm form;
    private Model model;

    public Controller(String title,IFunction function){
        model=new Model(function);
        form = new MainForm(title, this);
        model.addListener(form.getListener());
    }

    public void addValue(double x){
        model.addPoint(x);
    }

    public void removeValue(double x){
        model.removePoint(x);
    }

}
