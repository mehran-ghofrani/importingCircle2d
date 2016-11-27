/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Mactabi
 */
public class AskPage extends JPanel implements MouseListener, MouseMotionListener {
    
    public static AskPage instance;
    BufferedImage sourceImage, image;
    int monitorX, monitorY;
    int imgX, imgY;
    
    
    public static AskPage getInstance(){
        if(instance==null)
            instance=new AskPage();
        return instance;
    }
    private AskPage(){
        setLayout(null);
        setSize(importingcircle2d.ImportingCircle2d.getInstance().getWidth(),importingcircle2d.ImportingCircle2d.getInstance().getHeight());
        setBackground(Color.WHITE);
        
        int buttonsSize=importingcircle2d.ImportingCircle2d.getInstance().biggerLenght/15;
        
        Image zoomInImg=null;
        try {
            zoomInImg = ImageIO.read(new File("res\\img\\AskPage\\zoomIn.png"));
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }   
        JButton zoomIn = new JButton(new ImageIcon(getScaledImage((BufferedImage)zoomInImg, buttonsSize, buttonsSize)));
        
        Image zoomOutImg=null;
        try {
            zoomOutImg = ImageIO.read(new File("res\\img\\AskPage\\zoomOut.png"));
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }   
        JButton zoomOut = new JButton(new ImageIcon(getScaledImage((BufferedImage)zoomOutImg, buttonsSize, buttonsSize)));
        
        zoomIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomIn();
            }
        });
        zoomOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomOut();
            }
        });
        
        zoomIn.setSize(buttonsSize, buttonsSize);
        zoomOut.setSize(buttonsSize, buttonsSize);
        
        zoomIn.setLocation(importingcircle2d.ImportingCircle2d.getInstance().width-zoomIn.getWidth(),
                importingcircle2d.ImportingCircle2d.getInstance().height-zoomIn.getHeight());
        zoomOut.setLocation(zoomIn.getX()-zoomOut.getWidth(),zoomIn.getY());
        
        zoomIn.setOpaque(false);
        zoomIn.setContentAreaFilled(false); //to make the content area transparent
        zoomIn.setBorderPainted(false); //to make the borders transparent

        zoomOut.setOpaque(false);
        zoomOut.setContentAreaFilled(false); //to make the content area transparent
        zoomOut.setBorderPainted(false); //to make the borders transparent
        
        add(zoomIn);
        add(zoomOut);
        
        
        
        try {
            sourceImage = ImageIO.read(new File("res\\img\\AskPage\\mallDeskInfo\\new\\ask.png"));
        } 
        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("img//ask.jpg");
        }        
        image = getScaledImage(sourceImage, importingcircle2d.ImportingCircle2d.getInstance().width, importingcircle2d.ImportingCircle2d.getInstance().height);
        
    }
    
    private void zoomIn(){
        System.out.println("pages.AskPage.zoomIn()");
        imgX-=(image.getWidth()-monitorX)/2;
        imgY-=(image.getHeight()-monitorY)/2;
        image = getScaledImage(sourceImage, image.getWidth()*2d, image.getHeight()*2d);
        repaint();
    }
    private void zoomOut(){
        System.out.println("pages.AskPage.zoomOut()");
        imgX+=(image.getWidth()-monitorX)/2;
        imgY+=(image.getHeight()-monitorY)/2;
        image = getScaledImage(sourceImage, image.getWidth()/2d, image.getHeight()/2d);
        repaint();
    }
    private BufferedImage getScaledImage(BufferedImage image, double width, double height){
        
        
        double imageAspect=(double)image.getWidth()/image.getHeight();
        double nedeedAspect=(double)width/height;
        boolean nedeedIsWider=imageAspect<nedeedAspect;
        double scale;
        if(nedeedIsWider){
            scale=(double)image.getHeight()/height;           
        }
        else{
            scale=(double)image.getWidth()/width;  
        }
        int newWidth=(int)(image.getWidth()/scale);
        int newHeight=(int)(image.getHeight()/scale);
        
        BufferedImage tempImg = new BufferedImage((int)width, (int)height, BufferedImage.TYPE_INT_ARGB);
        tempImg.createGraphics().drawImage(image, (tempImg.getWidth()-newWidth)/2, (tempImg.getHeight()-newHeight)/2, newWidth, newHeight,null);
        return tempImg;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, imgX, imgY, null);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    
    
    
}