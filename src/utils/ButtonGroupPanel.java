/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 *
 * @author Mactabi
 */
public class ButtonGroupPanel extends JPanel implements MouseListener{
    
    private boolean moving;
    private Button[] buttons=new Button[6];
    private Button centerBtn;
    private boolean closed;
    private double buttonsRR;

    public ButtonGroupPanel(int width) {
        closed=true;
        setOpaque(false);
//        setBackground(Color.red);
        buttonsRR=width/6;
//        setSize(width, (int)(3*Math.sqrt(3)*buttonsRR));
        setSize(width, (int)(6*buttonsRR));
        addMouseListener(this);
        System.out.println(getWidth()+" "+getHeight());
        init();
        
        
        
        
//        String fonts[] = 
//      GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
//
//    for ( int i = 0; i < fonts.length; i++ )
//    {
//      System.out.println(fonts[i]);
//    }
        
       
    }
    private void init(){
        centerBtn=new Button("+",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed(Button btn) {
                if(closed)
                    open();
                else
                    close();
                
                
                
            
            }
        });
        buttons[0]=new Button("سلفی",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed(Button btn) {
                
            }
        });
        buttons[1]=new Button("از من بپرس",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed(Button btn) {
                
            }
        });
        buttons[2]=new Button("عکس",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed(Button btn) {
                
            }
        });
        buttons[3]=new Button("دوربین هوشمند",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed(Button btn) {
                
            }
        });
        buttons[4]=new Button("فیلم",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed(Button btn) {
                
            }
        });
        buttons[5]=new Button("کاتالوگ",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed(Button btn) {
                
            }
        });
        
        int smallestFontSize=(int)buttons[0].fontSize;
        for(int i=0;i<6;i++){
            if(smallestFontSize>(int)buttons[i].fontSize)
                smallestFontSize=(int)buttons[i].fontSize;
        }
        for(int i=0;i<6;i++){
            buttons[i].setFontSize(smallestFontSize);
        }
        centerBtn.setFontSize(smallestFontSize);
        
        for(int i=0;i<6;i++){
            buttons[i].setColor(new Color(255, 255, 255,100));
        }
        centerBtn.setColor(new Color(255, 255, 255,100));
        
        for(int i=0;i<6;i++){
            buttons[i].setVisible(false);
        }
        
    }
    public void close(){
        moving=true;
        centerBtn.setText("+");
        for(int i=0;i<=5;i++){
            buttons[i].move(getWidth()/2,getHeight()/2);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {      
                int n=0;
                while(buttons[5].moving){
                    System.out.println("waiting to complete close"+n);
                    n++;
                }
                System.out.println("completed close");
                for(int i=0;i<=5;i++){
                    buttons[i].setVisible(false);
                    
                }
                ButtonGroupPanel.this.moving=false;
                ButtonGroupPanel.this.closed=true;
            }
        },"closer").start();
    }
    public void open(){
        moving=true;
        centerBtn.setText("-");
        for(int i=0;i<=5;i++){
            buttons[i].setVisible(true);
        }
        for(int i=0;i<=5;i++){
            double deg=(i/6d)*(2*Math.PI)+Math.PI/6;
            buttons[i].move((int)(getWidth()/2+Math.cos(deg)*(2*buttonsRR)),
                    (int)(getHeight()/2-Math.sin(deg)*(2*buttonsRR)));
        }
        new Thread(new Runnable() {
            @Override
            public void run() {                
                int n=0;
                while(buttons[5].moving){
                    System.out.println("waiting to complete open"+n);
                    n++;
                }
                System.out.println("completed open");
                
                
                ButtonGroupPanel.this.moving=false;
                ButtonGroupPanel.this.closed=false;
            }
        },"opener").start();
        
        
        
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i=0;i<=5;i++){
            buttons[i].draw(g);
        }
        centerBtn.draw(g);
        
    }

    

    @Override
    public void mouseClicked(MouseEvent e) {
        centerBtn.mouseClicked(e);
        for(int i=0;i<=5;i++){
            buttons[i].mouseClicked(e);
        }
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
    
    
    
    
}
