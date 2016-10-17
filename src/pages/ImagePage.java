/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import importingcircle2d.ImportingCircle2d;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Mactabi
 */
public class ImagePage extends JPanel{

    public ImagePage() {
        setBackground(Color.red);
        setSize(ImportingCircle2d.getInstance().width, ImportingCircle2d.getInstance().height);
    }
    
    
    
}
