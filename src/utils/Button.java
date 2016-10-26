/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;

/**
 *
 * @author Mactabi
 */
public class Button extends Polygon {
    
    private MouseMotionListener motionlistener;
    private MouseListener listener;
    private String text;
    private Color textColor;
    
    public Button(String text,int x,int y,double rad) {
        super(6,x,y,rad);
        this.text=text;
        textColor=Color.black;
    }
    public void draw(Graphics g){
        super.draw(g);
        g.setColor(textColor);
        int fontSize=100;
        g.setFont(new Font("Bodoni MT", 2, fontSize));
        Rectangle textBounds=getStringBounds((Graphics2D)g, text, x, y);
        fontSize*=textBounds.width/(2*radius-(textBounds.height/(2*Math.sqrt(Math.pow(radius, 2)-Math.pow(radius/2, 2))))*radius);
        g.setFont(new Font("Bodoni MT", 2, fontSize));
        Rectangle newTextBounds=getStringBounds((Graphics2D)g, text, x, y);
        
        g.drawString(text, x-newTextBounds.width/2, y+newTextBounds.height/2);
        
    }
    private Rectangle getStringBounds(Graphics2D g2, String str,
                                      float x, float y)
    {
        FontRenderContext frc = g2.getFontRenderContext();
        GlyphVector gv = g2.getFont().createGlyphVector(frc, str);
        return gv.getPixelBounds(null, x, y);
    }
    
}
