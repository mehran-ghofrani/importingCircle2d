/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import importingcircle2d.ImportingCircle2d;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import sun.security.jca.GetInstance;

public class ImagePage extends JPanel{
    
    private Vector<ImageItem> imgs;
    private static ImagePage instance;
    private int currentPic;
    private JFrame parent;
    int height;
    int width;
    static boolean moving=false;
    
    public static ImagePage getInstance(){
        if(instance==null)
            instance=new ImagePage();
        return instance;    
    }
    ImagePage(){
        setLayout(null);
        parent=importingcircle2d.ImportingCircle2d.getInstance();
        currentPic=0;        
        imgs=new Vector();
        width=parent.getWidth();
        height=parent.getHeight();        
        setSize(width,height);
    }
    public void initialize(){
        
        setVisible(false);
        imgs.add(new ImageItem("res//img//1.jpg"));
        imgs.add(new ImageItem("res//img//2.png"));
        imgs.add(new ImageItem("res//img//3.jpg"));
        imgs.add(new ImageItem("res//img//4.jpg"));
        imgs.add(new ImageItem("res//img//5.jpg"));
        imgs.add(new ImageItem("res//img//6.jpg"));
        imgs.add(new ImageItem("res//img//7.jpg"));
        imgs.add(new ImageItem("res//img//8.jpg"));
        int i=0;
        for(ImageItem imgItm:imgs){
            add(imgs.get(i));
            imgs.get(i).setLocation(i*width, 0);
            imgs.get(i).setSize(width, height);
            imgs.get(i).setVisible(true);
            i++;
        }
        revalidate();
    }
    public void reinitialize(){
        int i=0;
        for(ImageItem imgItm:imgs){
            imgs.get(i).setLocation(i*width, 0);
            imgItm.setSize(importingcircle2d.ImportingCircle2d.getInstance().width, importingcircle2d.ImportingCircle2d.getInstance().height);
            i++;
        }
    }
    public void moveImages(final boolean right){

        final int[] currentLocation=new int[imgs.size()];
        for(int i=0;i<=imgs.size()-1;i++){
                currentLocation[i]=width*(i-currentPic);
        }
        final int stepsNum=right?500:-500;
        final double stepsLenght=(double)width/stepsNum;
        if (((1<=currentPic&&right)||(!right&&currentPic<=imgs.size()-2))&&!moving)
            new Thread(new Runnable() {
                public void run() {
                    ImagePage.moving=true;
                    for(int j=1;j<=Math.abs(stepsNum);j++){
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        for(int i=0;i<=imgs.size()-1;i++){                    
                            imgs.get(i).setLocation((i-currentPic)*width+(int)(stepsLenght*j), 0);
                        }
                    }
                    currentPic+=right?-1:+1;
                    ImagePage.moving=false;
                }
            }).start();
    }
}
class ImageItem extends JPanel implements MouseListener{
    
    private BufferedImage image;
    private int lastX;
    private int lastY;
    
    public ImageItem(String imgAddress) {
        setBackground(Color.white);
        addMouseListener(this);
        try {
            image = ImageIO.read(new File(imgAddress));
        } 
        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(imgAddress);
        }        
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double imageAspect=(double)image.getWidth()/image.getHeight();
        double screenAspect=(double)importingcircle2d.ImportingCircle2d.getInstance().width/importingcircle2d.ImportingCircle2d.getInstance().height;
        boolean screenIsWider=imageAspect<screenAspect;
        double scale;
        if(screenIsWider){
            scale=(double)image.getHeight()/importingcircle2d.ImportingCircle2d.getInstance().height;           
        }
        else{
            scale=(double)image.getWidth()/importingcircle2d.ImportingCircle2d.getInstance().width;  
        }
        int newWidth=(int)(image.getWidth()/scale);
        int newHeight=(int)(image.getHeight()/scale);
        
            
               
        g.drawImage(image,0,0,getParent().getWidth() ,getParent().getHeight(),null);
        g.drawImage(image,(getParent().getWidth() - newWidth) / 2,(getParent().getHeight() - newHeight)/2 , newWidth, newHeight,null);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        lastX=e.getX();
        lastY=e.getY();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        int verticalMove=Math.abs(e.getX()-lastX);
        int hortizalMove=Math.abs(e.getY()-lastY);
        if(verticalMove>hortizalMove&&verticalMove>getWidth()/5)
            ((ImagePage)getParent()).moveImages(e.getX()>lastX);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
