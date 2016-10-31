/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mactabi
 */
public class Button extends Polygon implements MouseListener,MouseMotionListener {
    
    private String text;
    private Color textColor;
    Rectangle2D textBounds; 
    double fontSize;
    private actionListener listener;
    
    public Button(String text,int x,int y,double rad) {
        super(6,x,y,rad);
        this.text=text;
        textColor=Color.black;
    }
    public void draw(Graphics g){
        super.draw(g);
        g.setColor(textColor);
        double fontSize=100;
        g.setFont(new Font("B Jadid Bold", 2, (int)fontSize));
        textBounds=getStringBounds((Graphics2D)g, text, x, y);       
        fontSize*=(2*radius)/(textBounds.getWidth()+textBounds.getHeight()/1.5);
//        fontSize*=(
//                (2*radius)/(
//                ((double)textBounds.height/textBounds.width)-Math.tan((2/3d)*Math.PI)
//                )
//                )/(double)textBounds.width;
        
        g.setFont(new Font("B Jadid Bold", 2, (int)fontSize));
        textBounds=getStringBounds((Graphics2D)g, text, x, y);
        if(textBounds.getHeight()>Math.sqrt(3*Math.pow(radius, 2))){
            fontSize*=Math.sqrt(3*Math.pow(radius, 2))/textBounds.getHeight();
            g.setFont(new Font("B Jadid Bold", 2, (int)fontSize));
            textBounds=getStringBounds((Graphics2D)g, text, x, y);

        }
        
        g.drawString(text, (int)(x-textBounds.getX()-textBounds.getWidth()/2), (int)(y-textBounds.getY()-textBounds.getHeight()/2));
        g.drawLine(x, y, x-(int)(textBounds.getWidth()/2), y+(int)(textBounds.getHeight()/2));
        g.translate((int)(x-textBounds.getX()-textBounds.getWidth()/2), (int)(y-textBounds.getY()-textBounds.getHeight()/2));
        ((Graphics2D)g).draw(textBounds);
        
        
    }
    private Rectangle2D getStringBounds(Graphics2D g2, String str,
                                      float x, float y)
    {
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
    public void mouseDragged(MouseEvent e) { 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}

