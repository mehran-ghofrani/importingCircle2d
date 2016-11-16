
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
import db.DBManager;
import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import pages.camera.uiComponents.KeyBoard;
import pages.camera.uiComponents.TouchJTextField;
import pages.camera.uiComponents.pages.CatalogEmailSendingPage;
import pages.camera.uiComponents.pages.ImageCapturingPage;
import pages.camera.uiComponents.pages.RetryPage;
import pages.camera.uiComponents.uiInterfaces.ActivityPage;
import pages.camera.uiComponents.uiInterfaces.EnterActionPerformListener;



public class ImportingCircle2d extends JFrame implements MouseListener,MouseMotionListener,WindowListener{

    public int height;
    public int width;
    private static ImportingCircle2d instance;
    public JPanel currentPage;
    public JLayeredPane layer;
    public KeyBoard keyboardPanel;
    private long spentTimeSinceLastUsage;
   
    
    public static void main(String[] args) {
        importingcircle2d.ImportingCircle2d.getInstance().initialize();
    }
    public static ImportingCircle2d getInstance(){
        if(instance==null)
            instance=new ImportingCircle2d();
        return instance;
    }
    private ImportingCircle2d(){
        
        setLayout(null);
        addWindowListener(this);
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
        
        keyboardPanel=new KeyBoard(this);
        add(keyboardPanel);
        keyboardPanel.setVisible(false);
        
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
        
        
        long eventMask = AWTEvent.MOUSE_MOTION_EVENT_MASK
        + AWTEvent.MOUSE_EVENT_MASK
        + AWTEvent.KEY_EVENT_MASK;
        Toolkit.getDefaultToolkit().addAWTEventListener( new AWTEventListener(){
            public void eventDispatched(AWTEvent e){
                spentTimeSinceLastUsage=0;
            }
        }, eventMask);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ImportingCircle2d.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    spentTimeSinceLastUsage++;
                    System.out.println(".run()");
                    if(spentTimeSinceLastUsage>60){
                        showPage(BlankPage.getInstance());
                        NavPage.getInstance().setVisible(true);
                    }
                }
            }
        }).start();






    }
    public void initialize(){
        
        NavPage.getInstance().setVisible(false);
        layer.add(NavPage.getInstance(),Integer.valueOf(1));
        
        
        layer.add(DoorPage.getInstance(),Integer.valueOf(0));
        showPage(DoorPage.getInstance());
        
        
        DBManager.getMyInstance();
        
        BlankPage.getInstance().setVisible(false);
        ImagePage.getInstance().setVisible(false);
        ImageCapturingPage.getInstance().setVisible(false);
        RetryPage.getInstance().setVisible(false);
        CatalogEmailSendingPage.getInstance().setVisible(false);
                
        layer.add(BlankPage.getInstance(),Integer.valueOf(0));
        layer.add(ImagePage.getInstance(),Integer.valueOf(0));
        //init camera components...
        
        layer.add(ImageCapturingPage.getInstance(),Integer.valueOf(0));
        layer.add(RetryPage.getInstance(),Integer.valueOf(0));
        layer.add(CatalogEmailSendingPage.getInstance(),Integer.valueOf(0));
        
///////////////
        
        
        
        
        
        ImagePage.getInstance().initialize();

        
        


          
          
        
    }
    public void showPage(JPanel page){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(currentPage!=null){
                    currentPage.setVisible(false);
                    if(page instanceof ActivityPage){
                        ((ActivityPage)page).beforeDispose();
                        ((ActivityPage)page).afterDispose();
                    }
                }
                if(page instanceof ActivityPage){
                    ((ActivityPage)page).beforeShow();
                    ((ActivityPage)page).afterShow();
                }
                page.setVisible(true);
                currentPage=page;
            }
        });
        
    }
    public void hideKeyBoard(){
        keyboardPanel.setVisible(false);
        layer.setLocation(0, 0);
        if(currentPage instanceof ActivityPage)
            ((ActivityPage)currentPage).afterKeyboardDispose();
    }
    public void showKeyBoard(TouchJTextField textField, EnterActionPerformListener listener){
        keyboardPanel.setEnterActionPerformListener(listener);
        keyboardPanel.setTextField(textField);
        keyboardPanel.setVisible(true);
        layer.setLocation(0, -keyboardPanel.getHeight());
        if(currentPage instanceof ActivityPage)
            ((ActivityPage)currentPage).beforeKeyboardShow();
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
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if(currentPage instanceof ActivityPage){
            ((ActivityPage)currentPage).beforeDispose();
            ((ActivityPage)currentPage).afterDispose();
        }
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    
}