/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.hibernate.tool.instrument.javassist.InstrumentTask;

/**
 *
 * @author Mactabi
 */
public class CatalogPage extends JPanel implements MouseListener,MouseMotionListener{
    
    public static CatalogPage instance;
    JEditorPane pane;
    
    
    
    JFXPanel fxPanel;
        WebView wv;
        JFrame frame;
        
        
        
        
    public static CatalogPage getInstance(){
        if (instance==null)
            instance=new CatalogPage();
        return instance;
    }
    
    private CatalogPage(){
        
        setSize(importingcircle2d.ImportingCircle2d.getInstance().getSize());
        addMouseListener(this);
        addMouseMotionListener(this);
        
//        pane = new JEditorPane();
//        pane.setEditable(false);   
//
//        try {
//          pane.setPage("http://www.google.com/");
//        }catch (IOException e) {
//          pane.setContentType("text/html");
//          pane.setText("<html>Could not load</html>");
//        } 
//
//        JScrollPane scrollPane = new JScrollPane(pane);     
//
//        add(scrollPane);
        

        


  try {

        fxPanel = new JFXPanel ();

        // create JavaFX scene
        com.sun.javafx.application.PlatformImpl.runLater ( new Runnable () {
            @Override
            public void run () {
                wv = new WebView();
                wv.getEngine ().load ( "http://eu.louisvuitton.com/eng-e1/men/shoes" );
                fxPanel.setScene ( new Scene ( wv, getWidth(), getHeight()) );
                
                add ( new JScrollPane ( fxPanel ) );
                
            }
        } );

    } catch ( Exception ex ) {

    }
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        fxPanel.dispatchEvent(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        fxPanel.dispatchEvent(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        fxPanel.dispatchEvent(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        fxPanel.dispatchEvent(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        fxPanel.dispatchEvent(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        fxPanel.dispatchEvent(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        fxPanel.dispatchEvent(e);
    }
}
