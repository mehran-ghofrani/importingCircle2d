
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
    private static ImportingCircle2d instance;
    private JPanel currentPage;
    private ImagePage imagePage;
    
    
    public static void main(String[] args) {
        getInstance().initialize();  
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    
    public void initialize(){
        
        
        imagePage = new ImagePage();
        add(imagePage);
        
     
        showPage(imagePage);
        
        
    }
    
    public void showPage(JPanel page){
        
        if(currentPage!=null)
            currentPage.setVisible(false);
        page.setVisible(true);
        currentPage=page;
    }
    
}