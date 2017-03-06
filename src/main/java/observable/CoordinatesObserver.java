package observable;

import impl.SynchronizedPupil;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Dmitry on 18.02.2017.
 */
public class CoordinatesObserver implements IObserver {
    private int anchorX;
    private int anchorY;
    private int centerX;
    private int centerY;
    private int radius;
    private BufferedImage actionTexture;
    private BufferedImage defaultTexture;
    private Graphics g;
    private boolean isDefault;


    public CoordinatesObserver(int anchorX,
                               int anchorY,
                               int radius,
                               BufferedImage actionTexture,
                               BufferedImage defaultTexture,
                               Graphics g) {
        this.anchorX = anchorX;
        this.anchorY = anchorY;
        this.radius = radius;
        this.actionTexture = actionTexture;
        this.defaultTexture = defaultTexture;
        this.centerX=(2*anchorX+defaultTexture.getWidth())/2;
        this.centerY=(2*anchorY+defaultTexture.getHeight())/2;
        this.g = g;
        this.isDefault=true;
    }

    public CoordinatesObserver(int anchorX, int anchorY, int centerX, int centerY, int radius, BufferedImage actionTexture, BufferedImage defaultTexture, Graphics g) {
        this.anchorX = anchorX;
        this.anchorY = anchorY;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.actionTexture = actionTexture;
        this.defaultTexture = defaultTexture;
        this.g = g;

    }

    @Override
    public void update(int x, int y) {
        double distance =Math.sqrt(Math.pow(x-centerX,2)+Math.pow(y-centerY,2));
        if(distance<=radius){
            //toggle state;
            System.out.println("Click close enough: toggle and repaint");
            isDefault=!isDefault;
            if(isDefault){
                g.drawImage(defaultTexture,anchorX,anchorY,null);
            }else{
                g.drawImage(actionTexture,anchorX,anchorY,null);
            }
        }else{
            System.out.println("Click is too far away:" + distance);
        }
    }
}
