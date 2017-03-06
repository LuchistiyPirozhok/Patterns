package observable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class ObservableTest {
    private static final int RADIUS=40;
    public static void main(String[] args) {
        JFrame frame=new JFrame("Observable test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JLabel imgLabel=new JLabel();
        imgLabel.setSize(800,600);
        frame.getContentPane().add(imgLabel);

        /*-------------------LOAD ASSETS----------------------------*/
        BufferedImage background=null;
        BufferedImage leftEyeOpen=null;
        BufferedImage leftEyeClose=null;
        BufferedImage rightEyeOpen=null;
        BufferedImage rightEyeClose=null;
        BufferedImage leftCheekDef=null;
        BufferedImage leftCheekAction=null;
        BufferedImage rightCheekDef=null;
        BufferedImage rightCheekAction=null;
        BufferedImage mouthOpen=null;
        BufferedImage mouthClose=null;

        try {
            background = ImageIO.read(ObservableTest.class.getResourceAsStream("/assets/background.png"));
            leftEyeOpen = ImageIO.read(ObservableTest.class.getResourceAsStream("/assets/left-eye-opened.png"));
            leftEyeClose = ImageIO.read(ObservableTest.class.getResourceAsStream("/assets/left-eye-closed.png"));
            rightEyeOpen = ImageIO.read(ObservableTest.class.getResourceAsStream("/assets/right-eye-opened.png"));
            rightEyeClose = ImageIO.read(ObservableTest.class.getResourceAsStream("/assets/right-eye-closed.png"));
            leftCheekDef = new BufferedImage(99,40,BufferedImage.TYPE_INT_RGB);
            Graphics2D tmp = leftCheekDef.createGraphics();//белый прямоугольник
            tmp.setPaint(Color.WHITE);
            tmp.fillRect(0,0,99,40);
            tmp.dispose();
            leftCheekAction = ImageIO.read(ObservableTest.class.getResourceAsStream("/assets/left-cheek.png"));
            rightCheekDef = new BufferedImage(85,40,BufferedImage.TYPE_INT_RGB);//белый прямоугольник
            tmp=rightCheekDef.createGraphics();
            tmp.setPaint(Color.WHITE);
            tmp.fillRect(0,0,99,40);
            tmp.dispose();
            rightCheekAction = ImageIO.read(ObservableTest.class.getResourceAsStream("/assets/right-cheek.png"));
            mouthOpen = ImageIO.read(ObservableTest.class.getResourceAsStream("/assets/mouth-opend.png"));
            mouthClose = ImageIO.read(ObservableTest.class.getResourceAsStream("/assets/mouth-closed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        239 174  129x147 l-eye
        450 174	 129x147 r-eye
        357 375  128x57 mouth

        285 339  99x40 l-cheek
        460 335  85x40 r-cheek
        417 356 nose
        */
        CoordinatesSubject subject=new CoordinatesSubject();
        imgLabel.setIcon(new ImageIcon(background));
        Graphics2D g = background.createGraphics();
        g.drawImage(background,0,0,null);
        CoordinatesObserver leye=new CoordinatesObserver(239,174,RADIUS,leftEyeOpen,leftEyeClose,g);
        subject.attach(leye);
        CoordinatesObserver reye=new CoordinatesObserver(450,174,RADIUS,rightEyeOpen,rightEyeClose,g);
        subject.attach(reye);
        CoordinatesObserver lcheek=new CoordinatesObserver(285,339,417,356,RADIUS,leftCheekDef,leftCheekAction,g);
        subject.attach(lcheek);
        CoordinatesObserver rcheek=new CoordinatesObserver(460,335,417,356,RADIUS,rightCheekDef,rightCheekAction,g);
        subject.attach(rcheek);
        CoordinatesObserver mouth=new CoordinatesObserver(357,375,RADIUS,mouthClose,mouthOpen,g);
        subject.attach(mouth);

        imgLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Cicked at: x="+e.getX()+";y="+e.getY()+";");
                subject.setCoords(e.getX(),e.getY());
                imgLabel.repaint();
            }
        });

        frame.pack();
        frame.setVisible(true);

    }
}
