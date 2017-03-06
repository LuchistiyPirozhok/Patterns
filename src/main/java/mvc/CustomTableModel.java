package mvc;

import javax.swing.table.AbstractTableModel;

import java.util.*;


/**
 * Created by Dmitry on 19.02.2017.
 */
public class CustomTableModel extends AbstractTableModel {
    private List<Point> points = new ArrayList<>();
    private Set<IUpdateListener> listenres=new HashSet<>();

    public void addUpdateListener(IUpdateListener l){
        this.listenres.add(l);
    }
    public void removeUpdateListener(IUpdateListener l){
        this.listenres.remove(l);
    }

    @Override
    public int getRowCount() {
        return points.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (getRowCount() > rowIndex && columnIndex < getColumnCount() && columnIndex >= 0) {
            if (columnIndex == 0) {
                return points.get(rowIndex).getX();
            } else {
                return points.get(rowIndex).getY();
            }
        }
        return null;
    }
    public boolean isCellEditable(int row, int col){
        return col==0;
    }
    public void replaceAll(Collection<Point> points){
        this.points.clear();
        this.points.addAll(points);
        fireTableDataChanged();
    }
    @Override
    public String getColumnName(int idx){
        if(idx==0) return "x";
        return "y";
    }
    public void setValueAt(Object valueAt,int row ,int col){
        if(getRowCount()<=row) return;
        try{
            double val= Double.parseDouble(valueAt.toString());
            double old=points.get(row).getX();
            points.set(row,new Point(val,points.get(row).getY()));
            listenres.forEach(l->l.onUpdate(new TableUpdateEvent(old,val)));
        }catch (Exception ex){

        }
    }
}
