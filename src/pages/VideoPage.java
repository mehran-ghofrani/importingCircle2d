/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.JPanel;
import org.opencv.video.Video;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JPanel;

/**
 *
 * @author mgh
 */
public class VideoPage extends JPanel{
    
    public static VideoPage instance;
    URL source;
    Player player;
    Component playerView;
    
    	
    
    
    
    public static VideoPage getInstance(){
        if(instance==null)
            instance=new VideoPage();
        return instance;
    }

    public VideoPage() {
        
        setSize(importingcircle2d.ImportingCircle2d.getInstance().getSize());
        
        
//        try {
//            source=new File("C:\\Users\\mgh\\Desktop\\m.mp4").toURL();
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(VideoPage.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        try {
            player = Manager.createRealizedPlayer( new URL("http://www.sample-videos.com/video/mp4/240/big_buck_bunny_240p_30mb.mp4") );
        } catch (Exception ex) {
            Logger.getLogger(VideoPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        playerView = player.getVisualComponent();
        setLayout(null);
        add(playerView);
        playerView.setSize(getSize());
        playerView.setVisible(true);
        player.start();
        setBackground(Color.red);
        playerView.setBackground(Color.red);
        
        
    }
    
}
