
package importingcircle2d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import pages.DoorPage;
import pages.ImagePage;


public class ImportingCircle2d extends JFrame{

    public int height;
    public int width;
    private static ImportingCircle2d instance;
    private JPanel currentPage;
    JLayeredPane layer;
    
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
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        layer=new JLayeredPane();
        layer.setLayout(null);
        layer.setSize(getSize());
        layer.setVisible(true);
        add(layer);
        

    }
    public void initialize(){
        
        
        layer.add(DoorPage.getInstance(),Integer.valueOf(0));
        layer.add(ImagePage.getInstance(),Integer.valueOf(0));
        ImagePage.getInstance().initialize();
        showPage(DoorPage.getInstance());
        
        
    }
    public void showPage(JPanel page){
        
        if(currentPage!=null)
            currentPage.setVisible(false);
        page.setVisible(true);
        currentPage=page;
    }
}