/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utils.Button;
import utils.ButtonGroupPanel;
import utils.Polygon;



/**
 *
 * @author Mactabi
 */
public class NavPage extends JPanel implements MouseListener,MouseMotionListener{

    public static NavPage instance;
    public ButtonGroupPanel btnGP;
    private Button homeBtn;
    private int homeBtnMinR;
    private int homeBtnMaxR;
    //arrow
    private boolean arrowVisible;
    private double arrowTranslation;
    Point[] points;
    private int[] x;
    private int[] y;
    
    


    public static NavPage getInstance(){
        if(instance==null)
            instance=new NavPage();
        return instance;
    }
    private NavPage(){
        addMouseListener(this);
        addMouseMotionListener(this);
        setOpaque(false);
        setLayout(null);
        setSize(importingcircle2d.ImportingCircle2d.getInstance().width, importingcircle2d.ImportingCircle2d.getInstance().height);
        btnGP=new ButtonGroupPanel(Math.max(getWidth() , getHeight())/4);
        add(btnGP);
        btnGP.setLocation(0, 0);
        homeBtn=new Button("صفحه اصلی", getWidth()/2, getHeight(), homeBtnMinR=Math.min(getWidth(), getHeight())/10, null);
        homeBtnMaxR=homeBtnMinR*9;
        homeBtn.setResizeByDrag(true);
        homeBtn.setFirstColor(new Color(100, 100, 100, 100));
        homeBtn.setSecondColor(new Color(255, 255, 255, 100));
        new Thread(new Runnable() {
            int a=0;
            int step=4;
            short beatDone=0;
            @Override
            public void run() {
                while(true){
                    if(beatDone==2){
                        try { 
                            Thread.sleep(1500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(NavPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        beatDone=0;
                    }
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(NavPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    a+=step;
                    if(a+Math.abs(step)>255||a-Math.abs(step)<0)
                        step*=-1;
                    if(a+Math.abs(step)>255)
                        beatDone++;
                    if(!homeBtn.pressed&&homeBtn.getRadius()<=homeBtnMinR){
                        homeBtn.setCurrentColor(new Color(100, 100, 100, 255-a));
                        homeBtn.setRadius((a/255d*0.2+0.8)*(homeBtnMinR));
                    }
                    
                    repaint();
                }
            }
        }).start();
        
        
        //arrow
        int arrowX=getWidth()/10;
        int arrowY=getHeight()/10;
        points=new Point[7];   
        
        points[0]=new Point(5*arrowX, -4*arrowY);
        
        points[1]=new Point(4*arrowX, -2*arrowY);
        
        points[2]=new Point((int)(4.5*arrowX), -2*arrowY);
        points[3]=new Point((int)(4.5*arrowX), 0*arrowY);
        points[4]=new Point((int)(5.5*arrowX), 0*arrowY);
        points[5]=new Point((int)(5.5*arrowX), -2*arrowY);
        
        points[6]=new Point(6*arrowX, -2*arrowY);
        
        x=new int[7];
        y=new int[7];
        for(int i=0;i<=6;i++){
            x[i]=points[i].x;
            y[i]=points[i].y;
        }
        
        
    }
    
    
    public void startArrowMotion(){
        arrowVisible=true;
        arrowTranslation=1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                
                    double speed=-0.0005;
                double arrowTranslationSpeed=0.001*speed;
                while(arrowVisible){
//                    try {
//                        Thread.sleep(1);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(DoorPage.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//assign 1 to speed if uncommented
                    
                    arrowTranslation+=arrowTranslationSpeed;
                    if(0>arrowTranslation){
                        arrowTranslation=1;
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(DoorPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    for(int i=0;i<=6;i++){
//                        x[i]=points[i].x;
                        y[i]=points[i].y+
                                (int)((arrowTranslation)*
                                (8/10d)*
                                Math.min(getWidth(), getHeight()));
                        repaint();
                    }
                }
            
                
                
            }
        }).start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        importingcircle2d.ImportingCircle2d.getInstance().currentPage.dispatchEvent(e);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        homeBtn.mousePressed(e);
        repaint();
        
        importingcircle2d.ImportingCircle2d.getInstance().currentPage.dispatchEvent(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        arrowVisible=false;
        homeBtn.mouseReleased(e);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!homeBtn.pressed&&homeBtn.getRadius()>homeBtnMinR){
                    homeBtn.setRadius(homeBtn.getRadius()-1);
                    NavPage.getInstance().repaint();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(NavPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
        
        importingcircle2d.ImportingCircle2d.getInstance().currentPage.dispatchEvent(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        importingcircle2d.ImportingCircle2d.getInstance().currentPage.dispatchEvent(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        importingcircle2d.ImportingCircle2d.getInstance().currentPage.dispatchEvent(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        homeBtn.mouseDragged(e);
        if(homeBtn.getRadius()<homeBtnMinR&&homeBtn.pressed)
            homeBtn.setRadius(homeBtnMinR);
        if(homeBtn.getRadius()>homeBtnMaxR&&homeBtn.pressed){
            homeBtn.setRadius(homeBtnMaxR);
            importingcircle2d.ImportingCircle2d.getInstance().showPage(DoorPage.getInstance());
            setVisible(false);
        }
        repaint();
        importingcircle2d.ImportingCircle2d.getInstance().currentPage.dispatchEvent(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        importingcircle2d.ImportingCircle2d.getInstance().currentPage.dispatchEvent(e);
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        homeBtn.draw(g);
        
        //arrow
        
        if(arrowVisible){
            g.setColor(new Color(0, 0, 0, (int)((arrowTranslation-1)*-255)));
            g.fillPolygon(x,y,7);
        }
        
        if(homeBtn.getRadius()>homeBtnMinR){
            g.setColor(new Color(0, 0, 0, (int)((homeBtn.getRadius()-homeBtnMinR)/(homeBtnMaxR-homeBtnMinR)*255)));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        
    }

    @Override
    public void setVisible(boolean visible){
        super.setVisible(visible);
        homeBtn.setTextVisible(true);
    }
    
    
}