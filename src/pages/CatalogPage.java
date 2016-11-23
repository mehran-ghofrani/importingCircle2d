/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Set;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.hibernate.tool.instrument.javassist.InstrumentTask;

import javafx.fxml.FXML;  
import javafx.fxml.Initializable;  
import javafx.scene.Node;  
import javafx.scene.control.ScrollBar;  
import javafx.scene.web.WebEngine;  
import javafx.scene.web.WebView; 

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
    Point origin;
    JScrollPane scrollPane;
        
        
        
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
//        JScrollPane scrollPane2 = new JScrollPane(pane);     
//
//        add(scrollPane2);
//        
//        System.out.println(pane.getHeight());
        

        


  try {

        fxPanel = new JFXPanel ();

        // create JavaFX scene
        com.sun.javafx.application.PlatformImpl.runLater ( new Runnable () {
            @Override
            public void run () {
                wv = new WebView();
                wv.getEngine ().load ( "http://eu.louisvuitton.com/eng-e1/men/shoes" );
                
                wv.getEngine ().setJavaScriptEnabled(true);
//                wv.getEngine ().loadContent("<body contentEditable='true'><div id='content'>Initial Text</div><div id='first'>My first web view in fx</div></body><span id='second'>My first web view in fx</span><span id='second'>My first web view in fx</span><span id='second'>My first web view in fx</span><span id='second'>My first web view in fx</span><span id='second'>My first web view in fx</span><span id='second'>My first web view in fx</span><span id='second'>My first web view in fx</span><span id='second'>My first web view in fx</span><div id='first'>My first web view in fx</div></body></body>");
                fxPanel.setScene ( new Scene ( wv, getWidth(), getHeight()) );
                fxPanel.setSize((int)wv.getWidth(),(int)wv.getHeight());
                add ( /*scrollPane = new JScrollPane*/ ( fxPanel ) );
                
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
//        fxPanel.dispatchEvent(e);
        origin=e.getPoint();
        
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        fxPanel.dispatchEvent(e);
        origin=e.getPoint();
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
//        fxPanel.dispatchEvent(e);

//        System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getVerticalScrollBar().getMinimum());
//            JViewport viewPort = (JViewport) fxPanel.getVisibleRect();
            if (origin != null) {
                int deltaX = origin.x - e.getX();
                int deltaY = origin.y - e.getY();

                Rectangle view = fxPanel.getVisibleRect();
                view.x += deltaX;
                view.y += deltaY;

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
//                        String webViewContents = (String) wv.getEngine()
//                                .executeScript("document.documentElement.outerHTML");
//                        String appendContent = "<div id='append'>Appended html content</div> Appended text content";
//
//                        StringBuilder scrollHtml = scrollWebView(0, 1);
//
//                        wv.getEngine().loadContent(scrollHtml + webViewContents/* + appendContent*/);       
                        Set<Node> nodes = wv.lookupAll(".scroll-bar");  
        for (Node node : nodes) {  
            if (ScrollBar.class.isInstance(node)) {  
                System.out.println("Scrollbar here!!!");  
                ScrollBar scroll = (ScrollBar) node;  
                scroll.setValue(scroll.getMax());  
            }  
        }  
                    }
                });
                
//                fxPanel.scrollRectToVisible(new Rectangle(0, 100, 100, 100));
//                scrollPane.getVerticalScrollBar().setValue(222);
//                scrollPane.getVerticalScrollBar().getModel().setValue(222);
//                System.out.println(scrollPane.getVerticalScrollBar().getValue());



                
            }
        
        
//fxPanel.setLocation(0, (int)(fxPanel.getLocation().getY()+origin.getY()-e.getY()));////////////////////////////////////////////////////////////////
        
        
//        origin=e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        fxPanel.dispatchEvent(e);
    }
    
    public static StringBuilder scrollWebView(int xPos, int yPos) {
        StringBuilder script = new StringBuilder().append("<html>");
        script.append("<head>");
        script.append("   <script language=\"javascript\" type=\"text/javascript\">");
        script.append("       function toBottom(){");
        script.append("           window.scrollTo(" + xPos + ", " + yPos + ");");
        script.append("       }");
        script.append("   </script>");
        script.append("</head>");
        script.append("<body onload='toBottom()'>");
        return script;
    }
}
