/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import pages.NavPage;

/**
 *
 * @author Mactabi
 */
public class Button extends Polygon implements MouseListener,MouseMotionListener {
    
    private Image[] imgs;
    private int currentImg;
    private String text;
    private Color textColor;
    Rectangle2D textBounds; 
    double fontSize;
    private actionListener listener;
    public boolean visible;
    String fontName;
    private boolean resizeByDrag;
    private int lastMouseX,lastMouseY;
    public boolean pressed;
    public boolean textVisible;
    
    
    
    public Button(String text,int x,int y,double rad,actionListener listener) {
        super(6,x,y,rad);
        imgs=new Image[2];
        this.listener=listener;
        this.text=text;
        textColor=Color.black;
        visible=true;
        fontName="Nazanin";
        textVisible=true;
        
        
        Graphics g=importingcircle2d.ImportingCircle2d.getInstance().getGraphics();
        fontSize=100;
        g.setFont(new Font(fontName, 0, (int)fontSize));
        textBounds=getStringBounds((Graphics2D)g, text, x, y);       

        fontSize*=(2*radius)/(textBounds.getWidth()+textBounds.getHeight()/1.5);

//        double neededWidth=
//                (2d*radius)/
//                (
//                (textBounds.getHeight()/textBounds.getWidth())-Math.tan((2/3d)*Math.PI)
//                );     
//        fontSize*=neededWidth/textBounds.getWidth();

//        g.translate((int)(x-textBounds.getX()-textBounds.getWidth()/2), (int)(y-textBounds.getY()-textBounds.getHeight()/2));
//        g.drawRect((int)(neededWidth/2), (int)(textBounds.getHeight()*(neededWidth/textBounds.getWidth())/2d), (int)neededWidth, (int)(textBounds.getHeight()*(neededWidth/textBounds.getWidth())));
//        g.translate((int)-(x-textBounds.getX()-textBounds.getWidth()/2), (int)-(y-textBounds.getY()-textBounds.getHeight()/2));

        g.setFont(new Font(fontName, 0, (int)fontSize));

        textBounds=getStringBounds((Graphics2D)g, text, x, y);
        if(textBounds.getHeight()>Math.sqrt(3*Math.pow(radius, 2))){
            fontSize*=Math.sqrt(3*Math.pow(radius, 2))/textBounds.getHeight();
            g.setFont(new Font(fontName, 0, (int)fontSize));
            textBounds=getStringBounds((Graphics2D)g, text, x, y);

        }
        
        
        
        
        
    }
    public void showFirstColor(){
        currentColor=firstColor;
    }
    public void showSecondColor(){
        currentColor=secondColor;
    }
    public void setFont(String font){
        this.fontName=font;
    }
    public void setImg1(Image img,int id){
        imgs[id]=img;
    } 
    public void setCurrentImg(int id){
        currentImg=id;
    }
    public void setVisible(boolean visible){
        this.visible=visible;
    }
    public void setFontSize(int size){
        fontSize=size;
        if(text=="+")
            fontSize=4*size;
        Graphics g=importingcircle2d.ImportingCircle2d.getInstance().getGraphics();
        g.setFont(new Font(fontName, 0, (int)fontSize));
        textBounds=getStringBounds((Graphics2D)g, text, x, y);       

        
    }
    public void changeColor(){
        if(currentColor==firstColor)
            currentColor=secondColor;
        else
            currentColor=firstColor;
    }
    public void draw(Graphics g){
        if(visible){
            super.draw(g);
            g.setColor(textColor);
            if(imgs[currentImg]!=null)
                g.drawImage(imgs[currentImg], (int)(x-Math.sqrt(radius*radius/8)/2), (int)(y-Math.sqrt(radius*radius/8)/2), (int)(x+Math.sqrt(radius*radius/8)/2), (int)(y+Math.sqrt(radius*radius/8)/2), null);
            
            
            if(resizeByDrag){
                g.setFont(new Font(fontName, 0, (int)fontSize));
                if(textVisible)g.drawString(text, (int)(x-textBounds.getX()-textBounds.getWidth()/2), (int)(y-textBounds.getY()-textBounds.getHeight()));
            }
            else{
                g.setFont(new Font(fontName, 0, (int)fontSize));
                if(textVisible)g.drawString(text, (int)(x-textBounds.getX()-textBounds.getWidth()/2), (int)(y-textBounds.getY()-textBounds.getHeight()/2));
            }
//            g.translate((int)(x-textBounds.getX()-textBounds.getWidth()/2), (int)(y-textBounds.getY()-textBounds.getHeight()/2));
//            ((Graphics2D)g).draw(textBounds);
//            g.translate((int)-(x-textBounds.getX()-textBounds.getWidth()/2), (int)-(y-textBounds.getY()-textBounds.getHeight()/2));
        }
        
    }
    private Rectangle2D getStringBounds(Graphics2D g2, String str, float x, float y) {
//        FontRenderContext frc = g2.getFontRenderContext();
//        GlyphVector gv = g2.getFont().createGlyphVector(frc, str);
//        return gv.getPixelBounds(null, x, y);
        
        //better solution
        
//        FontRenderContext fontContext = g2.getFontRenderContext();
//        TextLayout layout = new TextLayout(str, g2.getFont(), fontContext);
//        Rectangle2D bounds = layout.getBounds();
//        return bounds;
        
        //better solution
        
//        FontMetrics fm = g2.getFontMetrics();
//        Icon icon = null;
//        int verticalAlignment = SwingConstants.TOP;
//        int horizontalAlignment = SwingConstants.LEFT;
//        int verticalTextPosition = SwingConstants.TRAILING;
//        int horizontalTextPosition = SwingConstants.CENTER;
//        Rectangle viewR = new Rectangle((int)x, (int)y, 1000, 100);
//        Rectangle iconR = new Rectangle();
//        Rectangle textR = new Rectangle((int)x, (int)y, 1000, 100);
//        int textIconGap = 0;
//
//        SwingUtilities.layoutCompoundLabel(fm, str, icon,
//            verticalAlignment, horizontalAlignment,
//            verticalTextPosition, horizontalTextPosition,
//            viewR, iconR, textR, textIconGap);
//        return textR;

    
        //better solution
//        Font f = g2.getFont();
//        Rectangle2D charBounds = f.getStringBounds(text, g2.getFontRenderContext());
//        return charBounds;
        //better solution
        Rectangle2D charBounds = g2.getFont().getStringBounds(text, g2.getFontRenderContext());
        GlyphVector gv = g2.getFont().layoutGlyphVector(g2.getFontRenderContext(), text.toCharArray(), 0, text.length(), GlyphVector.FLAG_MASK);
        Rectangle2D bounds = gv.getVisualBounds();
        return bounds;
    }
    public void setText(String text){
        this.text=text;
    }
    public void setTextVisible(boolean visible){
        textVisible=visible;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
            if(mouseInArea(e)&&listener!=null&&!moving&&visible)
                listener.actionPerformed(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(mouseInArea(e)){
            lastMouseX=e.getX();
            lastMouseY=e.getY();
            showFirstColor();
            pressed=true;
            if(text=="صفحه اصلی"){
                NavPage.getInstance().startArrowMotion();
                setTextVisible(false);
                
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        if(mouseInArea(e)&&text=="صفحه اصلی")
            setTextVisible(true);
            pressed=false;
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
    public void mouseDragged(MouseEvent e) {
        if(resizeByDrag&&mouseInArea(e)){
            double r2=Math.sqrt(
                    Math.pow(e.getX()-x, 2)
                    +
                    Math.pow(e.getY()-y, 2)    
            );
            double r1=Math.sqrt(
                    Math.pow(lastMouseX-x, 2)
                    +
                    Math.pow(lastMouseY-y, 2)    
            );
            setRadius(radius+2*(r2-r1));
            lastMouseX=e.getX();
            lastMouseY=e.getY();
        }        
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(mouseInArea(e))
            setCurrentColor(secondColor);
        else
            setCurrentColor(firstColor);
        
    }

    private boolean mouseInArea(MouseEvent e){
        if(Math.sqrt(
                Math.pow(e.getX()-x, 2)
                +
                Math.pow(e.getY()-y, 2)    
        )<radius)
            return true;
        return false;
    }
    
    public void setResizeByDrag(boolean resizable){
        resizeByDrag=resizable;
    }
    
    @Override
    public void setRadius(double radius){
        double tempR=this.radius;
        super.setRadius(radius);
        fontSize*=radius/tempR;
        Graphics g=importingcircle2d.ImportingCircle2d.getInstance().getGraphics();
        g.setFont(new Font(fontName, 0, (int)fontSize));
        textBounds=getStringBounds((Graphics2D)g, text, x, y);  
        
    }
}

