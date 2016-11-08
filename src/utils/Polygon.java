/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import pages.NavPage;

/**
 *
 * @author Mactabi
 */
public class Polygon
{
    
    protected int x,y;
    protected double radius;
    protected Color firstColor,secondColor,currentColor;
    protected boolean moving;
    protected int npoints;
    protected int[] xpoints;
    protected int[] ypoints;
    
    public Polygon(int n,int x,int y,double radius){
        moving=false;
        this.radius=radius;
        npoints=n;
        xpoints=new int[n];
        ypoints=new int[n];
        for(int i=0;i<=n-1;i++){
            double deg=((double)i/n)*(2*Math.PI);
            xpoints[i]=(int)(Math.cos(deg)*radius);
            ypoints[i]=(int)(Math.sin(deg)*radius);
        }
        setLocation(x, y);
    }
    public double getRadius(){
        return radius;
    }
    public void setRadius(double radius){
        this.radius=radius;
        for(int i=0;i<=npoints-1;i++){
            double deg=(double)(((double)i/npoints)*(2*Math.PI));
            xpoints[i]=(int)(Math.cos(deg)*radius)+x;
            ypoints[i]=(int)(Math.sin(deg)*radius)+y;
        }
    }
    public void setLocation(int x,int y){
        for(int i=0;i<npoints;i++){
            xpoints[i]+=x-this.x;
            ypoints[i]+=y-this.y;
        }
        this.x=x;
        this.y=y;
    }
    public void move(final int x,final int y){
        moving=true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                
                while(Math.abs(Polygon.this.x-x)>3||Math.abs(Polygon.this.y-y)>3){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Polygon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    setLocation((int)(Polygon.this.x+(x-Polygon.this.x)/3d),(int)( Polygon.this.y+(y-Polygon.this.y)/3d));
                    NavPage.getInstance().btnGP.repaint();
                }
                Polygon.this.moving=false;
                
            }
        },"mover").start();
    }
    public void draw(Graphics g){
        g.setColor(currentColor);
        g.fillPolygon(xpoints,ypoints,npoints);
    }
    public void setCurrentColor(Color color){
        this.currentColor=color;
    }
    public void setFirstColor(Color color){
        firstColor=color;
        currentColor=firstColor;
    }
    public void setSecondColor(Color color){
        secondColor=color;
    }
}
