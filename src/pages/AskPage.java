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
import java.awt.Rectangle;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Mactabi
 */
public class AskPage extends JPanel {
    
    public static AskPage instance;
    BufferedImage image;
    
    
    public static AskPage getInstance(){
        if(instance==null)
            instance=new AskPage();
        return instance;
    }
    private AskPage(){
        setLayout(null);
        setSize(importingcircle2d.ImportingCircle2d.getInstance().getWidth(),importingcircle2d.ImportingCircle2d.getInstance().getHeight());
        setBackground(Color.WHITE);
        
        try {
            image = ImageIO.read(new File("res//img//ask.jpg"));
        } 
        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("img//ask.jpg");
        }        
        scratchImg();
        
    }
    
    private void scratchImg(){
        
        
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
        
        BufferedImage tempImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        tempImg.createGraphics().drawImage(image, (tempImg.getWidth()-newWidth)/2, (tempImg.getHeight()-newHeight)/2, newWidth, newHeight,null);
        image=tempImg;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);


    }
    
    
}