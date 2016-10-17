/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importingcircle2d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pages.ImagePage;

/**
 *
 * @author Mactabi
 */
public class ImportingCircle2d extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        getInstance();
        
    }
    
    public int height;
    public int width;
    
    private static ImportingCircle2d instance;
    
    
    
    
    
    public static ImportingCircle2d getInstance(){
        if(instance==null)
            instance=new ImportingCircle2d();
        return instance;
    }
    
    
    private ImportingCircle2d(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)screenSize.getWidth();
        height = (int)screenSize.getHeight();
        setSize(width, height);
        setUndecorated(true);
        setVisible(true);
        
        init();
        
        
        
        
    }
    
    
    
    
    
    public void init(){
        
        
        ImagePage imagePage = new ImagePage();
        
        add(imagePage);
        
     
        imagePage.setVisible(true);
        
        
        
        
        
    }
    
}
