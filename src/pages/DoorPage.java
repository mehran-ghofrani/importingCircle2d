
package pages;

import com.sun.javafx.util.Utils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class DoorPage extends JPanel implements MouseMotionListener,MouseListener {
    
    public static DoorPage instance;
    private double opened;
    private boolean visibility;
    private int[] leftX;;
    private int[] rightX;
    private int[] y;
    private JFrame parent;
    
    public static DoorPage getInstance(){
        if(instance==null)
            instance=new DoorPage();
        return instance;
    }
    
    private DoorPage(){
        JFrame parent=importingcircle2d.ImportingCircle2d.getInstance();
        setBackground(Color.BLACK);
        setSize(parent.getWidth(), parent.getHeight());
        
        
        int arrowX=getWidth()/20;
        int arrowY=getHeight()/10;
        
        Point[] points=new Point[7];
        
        points[0]=new Point(1*arrowX, 5*arrowY);
        
        points[1]=new Point(2*arrowX, 4*arrowY);
        
        points[2]=new Point(2*arrowX, (int)(4.5*arrowY));
        points[3]=new Point(4*arrowX, (int)(4.5*arrowY));
        points[4]=new Point(4*arrowX, (int)(5.5*arrowY));
        points[5]=new Point(2*arrowX, (int)(5.5*arrowY));
        
        points[6]=new Point(2*arrowX, 6*arrowY);
        
        leftX=new int[7];
        for(int i=0;i<=6;i++)
            leftX[i]=points[i].x;
        this.y=new int[7];
        for(int i=0;i<=6;i++)
            y[i]=points[i].y;
        
        rightX=new int[7];
        for(int i=0;i<=6;i++)
            rightX[i]=parent.getWidth()-points[i].x;
        
        
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.white);
        for(int y=0;y<=100;y+=2){
            g.drawLine((getWidth()/10), y*(getWidth()/100), getWidth()/10, (y+1)*(getWidth()/100));
            g.drawLine(getWidth()/10*9, y*(getWidth()/100), getWidth()/10*9, (y+1)*(getWidth()/100));
        }
        g.setColor(new Color(255, 255, 255, (int)(opened*255)));
        
        g.fillPolygon(leftX,y,7);
        g.fillPolygon(rightX,y,7);
        
        
    }
    
    @Override
    public void setVisible(boolean visibility){
        setVisible(visibility);
        this.visibility=visibility;
    }
    
    
}
