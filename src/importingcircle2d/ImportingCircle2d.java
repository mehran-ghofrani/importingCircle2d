
package importingcircle2d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import pages.DoorPage;
import pages.ImagePage;
import pages.NavPage;
import pages.BlankPage;
import pages.CameraPage;


public class ImportingCircle2d extends JFrame implements MouseListener,MouseMotionListener{

    public int height;
    public int width;
    private static ImportingCircle2d instance;
    public JPanel currentPage;
    public JLayeredPane layer;
    
    public static void main(String[] args) {
        importingcircle2d.ImportingCircle2d.getInstance().initialize();
    }
    public static ImportingCircle2d getInstance(){
        if(instance==null)
            instance=new ImportingCircle2d();
        return instance;
    }
    private ImportingCircle2d(){
        
        setBackground(Color.yellow);
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
//        layer.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                System.out.println("mmmmmm.mouseClicked()");            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                System.out.println(".mousePressed()");            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                System.out.println(".mouseReleased()");            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                System.out.println(".mouseEntered()");            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                System.out.println(".mouseExited()");}
//        });
        
        


    }
    public void initialize(){
        
        NavPage.getInstance().setVisible(false);
        layer.add(NavPage.getInstance(),Integer.valueOf(1));
        
        
        layer.add(DoorPage.getInstance(),Integer.valueOf(0));
        showPage(DoorPage.getInstance());
        
        layer.add(BlankPage.getInstance(),Integer.valueOf(0));
        layer.add(ImagePage.getInstance(),Integer.valueOf(0));
        
        
        
        
        
        ImagePage.getInstance().initialize();

        
        

//        layer.add(CameraPage.getInstance());
//        showPage(CameraPage.getInstance());
        
    }
    public void showPage(JPanel page){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(currentPage!=null)
                currentPage.setVisible(false);
                page.setVisible(true);
                currentPage=page;
            }
        });
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("importingcircle2d.ImportingCircle2d.mouseClicked()");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}