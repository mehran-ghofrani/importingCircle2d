
package importingcircle2d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pages.ImagePage;


public class ImportingCircle2d extends JFrame {

    public int height;
    public int width;
    public static ImportingCircle2d instance;
    
    public static void main(String[] args) {
        getInstance();  
    }
    
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
        
        initialize();
        
    }
    
    public void initialize(){
        
        
        ImagePage imagePage = new ImagePage();
        
        add(imagePage);
        
     
        imagePage.setVisible(true);
        
        
    }
    
}
