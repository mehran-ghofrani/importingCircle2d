
package importingcircle2d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pages.DoorPage;
import pages.ImagePage;


public class ImportingCircle2d extends JFrame implements MouseListener{

    public int height;
    public int width;
    private static ImportingCircle2d instance;
    private JPanel currentPage;
    
    
    
    public static void main(String[] args) {
        getInstance().initialize();  

    }
    public static ImportingCircle2d getInstance(){
        if(instance==null)
            instance=new ImportingCircle2d();
        return instance;
    }
    private ImportingCircle2d(){
        getContentPane().setBackground(Color.blue);
        addMouseListener(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)screenSize.getWidth();
        height = (int)screenSize.getHeight();
        setSize(width, height);
        setUndecorated(true);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public void initialize(){
        
        
        
        
        add(ImagePage.getInstance());
        add(DoorPage.getInstance());
        ImagePage.getInstance().initialize();
        showPage(ImagePage.getInstance());
        
    }
    
    public void showPage(JPanel page){
        
        if(currentPage!=null)
            currentPage.setVisible(false);
        page.setVisible(true);
        currentPage=page;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.exit(0);    
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