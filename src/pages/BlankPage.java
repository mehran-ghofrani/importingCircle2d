/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Mactabi
 */
public class BlankPage extends JPanel {
    public static BlankPage instance;
    
    
    public static BlankPage getInstance(){
        if(instance==null)
            instance=new BlankPage();
        return instance;
    }
    private BlankPage(){
        setSize(importingcircle2d.ImportingCircle2d.getInstance().getWidth(),importingcircle2d.ImportingCircle2d.getInstance().getHeight());
        setBackground(Color.WHITE);
    }
    
}
