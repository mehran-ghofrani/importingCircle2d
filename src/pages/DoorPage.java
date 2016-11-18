
package pages;

import com.sun.javafx.Utils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventDispatcher;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import utils.Button;
import utils.ButtonGroupPanel;
import utils.Polygon;
import utils.actionListener;


public class DoorPage extends JPanel implements MouseMotionListener,MouseListener {
    
    public static DoorPage instance;
    private double openedAmount;
    private boolean visibile;
    private double arrowTranslation;
    Point[] points;
    private int[] leftX;;
    private int[] rightX;
    private int[] y;
    private JFrame parent;
    private int lastMouseX;
    private int lastMouseY;
    private boolean mouseClicked;
    private boolean dragStartedFromLeft;
    private boolean closing;
    
    
    
    
    public static DoorPage getInstance(){
        if(instance==null)
            instance=new DoorPage();
        return instance;
    }
    private DoorPage(){
        setBackground(Color.BLUE);
        setLayout(null);
        parent=importingcircle2d.ImportingCircle2d.getInstance();
        setBackground(Color.BLACK);
        setSize(parent.getWidth(), parent.getHeight());
        addMouseListener(this);
        addMouseMotionListener(this);   
        int arrowX=getWidth()/20;
        int arrowY=getHeight()/10;   
        mouseClicked=false;
        openedAmount=0.001;
        points=new Point[7];
        
        
        points[0]=new Point(1*arrowX, 5*arrowY);
        
        points[1]=new Point(2*arrowX, 4*arrowY);
        
        points[2]=new Point(2*arrowX, (int)(4.5*arrowY));
        points[3]=new Point(4*arrowX, (int)(4.5*arrowY));
        points[4]=new Point(4*arrowX, (int)(5.5*arrowY));
        points[5]=new Point(2*arrowX, (int)(5.5*arrowY));
        
        points[6]=new Point(2*arrowX, 6*arrowY);
        
        leftX=new int[7];
        rightX=new int[7];
        y=new int[7];
        for(int i=0;i<=6;i++){
            leftX[i]=points[i].x;
            rightX[i]=parent.getWidth()-leftX[i];
            y[i]=points[i].y;
        }
        setVisible(false);
        

        
        
            
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        openedAmount+=(double)(e.getX()-lastMouseX)/getWidth()*2*(dragStartedFromLeft?-1:1);
        if(openedAmount<.001)
            openedAmount=.001;
        lastMouseX=e.getX();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("pages.DoorPage.mouseClicked()");
    }
    @Override
    public void mousePressed(MouseEvent e) {
        lastMouseX=e.getX();
        lastMouseY=e.getY();
        mouseClicked=true;
        dragStartedFromLeft=e.getX()<getWidth()/2;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClicked=false;
        if(openedAmount>4/5d)
            new Thread(new Runnable() {
            @Override
            public void run() {
                closing=true;
                while(openedAmount<1)
                    openedAmount+=0.0000000001;
                
                importingcircle2d.ImportingCircle2d.getInstance().showPage(BlankPage.getInstance());
                NavPage.getInstance().setVisible(true);
                closing=false;
                NavPage.getInstance().setVisible(true);
            }
        }).start();
                        


    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.white);
//        for(int y=0;y<=100;y+=2){
//            g.drawLine((getWidth()/10), y*(getWidth()/100), getWidth()/10, (y+1)*(getWidth()/100));
//            g.drawLine(getWidth()/10*9, y*(getWidth()/100), getWidth()/10*9, (y+1)*(getWidth()/100));
//        }
        g.fillRect((int)(getWidth()/2-openedAmount*(getWidth()/2)), 0, (int)(openedAmount*getWidth()), getHeight());
        g.setColor(new Color(255, 255, 255, (int)(arrowTranslation*255)));
        g.fillPolygon(leftX,y,7);
        g.fillPolygon(rightX,y,7);
        
        

        
        
        
        
        
    }
    @Override
    public void setVisible(boolean visibile){
        openedAmount=0.001;
        super.setVisible(visibile);
        boolean temp=this.visibile;
        this.visibile=visibile;
        if(!temp){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    double speed=0.0005;
                    double arrowTranslationSpeed=0.001*speed;
                    while(DoorPage.getInstance().visibile){
    //                    try {
    //                        Thread.sleep(1);
    //                    } catch (InterruptedException ex) {
    //                        Logger.getLogger(DoorPage.class.getName()).log(Level.SEVERE, null, ex);
    //                    }
    //assign 1 to speed if uncommented
                        if(openedAmount>0.001&&!mouseClicked&&!closing)
                            openedAmount-=openedAmount/500*speed;
                        arrowTranslation+=arrowTranslationSpeed;
                        if(arrowTranslation<0||1<arrowTranslation)
                            arrowTranslationSpeed*=-1;
                        for(int i=0;i<=6;i++){
                            leftX[i]=points[i].x+
                                    (int)((arrowTranslation-openedAmount*5)*
                                    (1/10d)*
                                    DoorPage.getInstance().getWidth());
                            rightX[i]=parent.getWidth()-leftX[i];
                            y[i]=points[i].y;
                            DoorPage.getInstance().repaint();
                        }
                    }
                }
            }).start();
        }
    }
}