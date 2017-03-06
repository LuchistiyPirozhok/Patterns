package mvc;


import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.concurrent.ExecutionException;


/**
 * Created by Dmitry on 19.02.2017.
 */
public class MainForm extends JFrame {
    private static final int FORM_WIDTH=800;
    private static final int FORM_HEIGHT=630;

    private static final int PLOT_WIDTH=600;
    private static final int PLOT_HEIGHT=600;

    private static final int TABLE_WIDTH=200;
    private static final int TABLE_HEIGHT=600;

    private JLabel plot;
    private JScrollPane scrollPane;
    private JTable table;
    private BufferedImage plotImg;
    private Controller controller;
    private IModelListener listener;
    private CustomTableModel tableModel;
    public  MainForm (String title,Controller controller){
        super(title);
        this.controller=controller;
        this.tableModel=new CustomTableModel();
        this.listener= points -> {
           tableModel.replaceAll(points);
           paintPlot();
        };
        init();
    }
    public IModelListener getListener(){
        return listener;
    }

    private void init() {
        setResizable(false);
        setLayout(new BorderLayout());
        table=new JTable(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(TABLE_WIDTH/2-4);
        table.getColumnModel().getColumn(1).setPreferredWidth(TABLE_WIDTH/2-4);
        scrollPane=new JScrollPane(table);
        plot=new JLabel();
        plotImg=new BufferedImage(PLOT_WIDTH,PLOT_HEIGHT,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = plotImg.createGraphics();
        g.setPaint(Color.WHITE);
        g.fillRect(0,0,PLOT_WIDTH,PLOT_HEIGHT);
        g.dispose();
        table.setFillsViewportHeight(true);
        plot.setSize(PLOT_WIDTH,PLOT_HEIGHT);
        plot.setIcon(new ImageIcon(plotImg));
        setSize(FORM_WIDTH,FORM_HEIGHT);
        JPanel tableContainer=new JPanel();
        tableContainer.setLayout(new BorderLayout());
        tableContainer.add(table.getTableHeader(),BorderLayout.PAGE_START);
        tableContainer.add(table,BorderLayout.CENTER);
        tableContainer.setSize(TABLE_WIDTH,TABLE_HEIGHT);

        JPanel buttonContainer=new JPanel();
        JButton addButton=new JButton("Добавить");
        JButton delButton=new JButton("Удалить");
        JTextField text=new JTextField();
        addButton.addActionListener(e->{
            try{
                double input=Double.parseDouble(text.getText());
                controller.addValue(input);
            }catch(Exception ex){
                //just ignore;
            }
            text.setText("");
        });
        delButton.addActionListener(e->{
            int selected=table.getSelectedRow();
            if(selected<0){
                try{
                    double value=Double.parseDouble(text.getText());
                    controller.removeValue(value);
                }catch(Exception ex){
                    //ignore;
                }
                text.setText("");
            }else{
               double value=Double.parseDouble(table.getValueAt(selected,0).toString());
               controller.removeValue(value);
            }
        });
        tableModel.addUpdateListener(e->{
            controller.removeValue(e.getOldX());
            controller.addValue(e.getNewX());
        });
        buttonContainer.setLayout(new BorderLayout());
        buttonContainer.add(text,BorderLayout.PAGE_START);
        buttonContainer.add(addButton,BorderLayout.WEST);
        buttonContainer.add(delButton,BorderLayout.EAST);
        tableContainer.add(buttonContainer,BorderLayout.PAGE_END);
        getContentPane().add(plot,BorderLayout.WEST);
        getContentPane().add(tableContainer,BorderLayout.EAST);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paintPlot();
    }

    public void paintPlot(){
        TableModel model =table.getModel();
        double minX=Double.MAX_VALUE;
        double maxX=Double.MIN_VALUE;

        double minY=Double.MAX_VALUE;
        double maxY=Double.MIN_VALUE;

        ArrayList<Double> xList=new ArrayList<>();
        ArrayList<Double> yList=new ArrayList<>();

        for(int i=0; i<model.getRowCount();i++){
            double x= Double.valueOf(model.getValueAt(i,0).toString());
            double y= Double.valueOf(model.getValueAt(i,1).toString());

            if(minX>x) minX=x;
            if(maxX<x) maxX=x;
            if(minY>y) minY=y;
            if(maxY<y) maxY=y;

            xList.add(x);
            yList.add(y);
        }
        if(xList.size()==0){
            minX=minY=0;
            maxX=maxY=1;
        }
        Graphics2D g=plotImg.createGraphics();
        g.setPaint(Color.WHITE);
        g.fillRect(0,0,PLOT_WIDTH,PLOT_HEIGHT);//закрашиваем область графика белым цветом
        g.setPaint(Color.BLACK);
        g.drawLine((int)(0.08*PLOT_WIDTH),(int)(0.08*PLOT_HEIGHT)-15,(int)(0.08*PLOT_WIDTH),(int)(0.92*PLOT_HEIGHT)+15);//ось y
        g.drawLine((int)(0.08*PLOT_WIDTH),(int)(0.08*PLOT_HEIGHT),(int)(0.08*PLOT_WIDTH-15),(int)(0.08*PLOT_HEIGHT));//горизонтальная риска сверху
        g.drawLine((int)(0.08*PLOT_WIDTH),(int)(0.5*PLOT_HEIGHT),(int)(0.08*PLOT_WIDTH-15),(int)(0.5*PLOT_HEIGHT));//горизонтальная риска посередине y
        g.drawLine((int)(0.08*PLOT_WIDTH-15),(int)(0.92*PLOT_HEIGHT),(int)(0.92*PLOT_WIDTH)+15,(int)(0.92*PLOT_HEIGHT));//ось х
        g.drawLine((int)(0.92*PLOT_WIDTH),(int)(0.92*PLOT_HEIGHT),(int)(0.92*PLOT_WIDTH),(int)(0.92*PLOT_HEIGHT+15));//вертикальная риска справа
        g.drawLine((int)(0.5*PLOT_WIDTH),(int)(0.92*PLOT_HEIGHT),(int)(0.5*PLOT_WIDTH),(int)(0.92*PLOT_HEIGHT+15));//вертикальная риска посередине х

        g.drawString(String.format("%1$,.2f", minX),(int)(0.08*PLOT_WIDTH),(int)(0.92*PLOT_HEIGHT+30));//минимум по х
        g.drawString(String.format("%1$,.2f", maxX),(int)(0.92*PLOT_WIDTH),(int)(0.92*PLOT_HEIGHT+30));//максимум по х
        g.drawString(String.format("%1$,.2f", maxY),(int)(0.08*PLOT_WIDTH-30),(int)(0.08*PLOT_HEIGHT)+15);//максимум по y
        g.drawString(String.format("%1$,.2f", minY),(int)(0.08*PLOT_WIDTH-30),(int)(0.92*PLOT_HEIGHT)-15);//минимум по y
        double deltaX=((int)(0.84*PLOT_WIDTH))/(maxX-minX);
        double deltaY=((int)(0.84*PLOT_HEIGHT))/(maxY-minY);

        if(xList.size()>1 && yList.size()>1) {
            g.setPaint(Color.RED);
            g.fillOval((int)(0.08*PLOT_WIDTH+(xList.get(0)-minX)*deltaX)-2,(int)(0.92*PLOT_WIDTH-(yList.get(0)-minY)*deltaY)-2,4,4);//рисуем первую точку
            for(int i=1;i<xList.size();i++){
                //рисуем остальные точки и линии
                g.fillOval((int)(0.08*PLOT_WIDTH+(xList.get(i)-minX)*deltaX)-2,(int)(0.92*PLOT_WIDTH-(yList.get(i)-minY)*deltaY)-2,4,4);
                g.drawLine((int)(0.08*PLOT_WIDTH+(xList.get(i-1)-minX)*deltaX),(int)(0.92*PLOT_WIDTH-(yList.get(i-1)-minY)*deltaY),
                        (int)(0.08*PLOT_WIDTH+(xList.get(i)-minX)*deltaX),(int)(0.92*PLOT_WIDTH-(yList.get(i)-minY)*deltaY));
            }
        }
        plot.repaint();
    }


}
