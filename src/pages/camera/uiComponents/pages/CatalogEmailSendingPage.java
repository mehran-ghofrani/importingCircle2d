package pages.camera.uiComponents.pages;

import db.DBManager;
import pages.camera.uiComponents.TouchJTextField;
import pages.camera.uiComponents.uiInterfaces.EnterActionPerformListener;
import pages.camera.uiComponents.uiInterfaces.ActivityPage;
import utilities.EmailUtils;
import utilities.ImageUtilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.core.Core;
import pages.BlankPage;
import pages.NavPage;

import static utilities.EmailUtils.isValidEmailAddress;
import static utilities.Fonts.bodyFont;
import static utilities.Fonts.headingFont;
import static utilities.ImageUtilities.framifyImage;

public class CatalogEmailSendingPage extends JPanel implements EnterActionPerformListener, ActivityPage
{
    private static CatalogEmailSendingPage instance = null;
    private Image userImg;
    private JLabel infoLable0;
    private JLabel infoLable;
    private JButton submitBtn;
    private JLabel statusLabel;
    private TouchJTextField emailInputField;
    private JLabel emailLabel;

//    private ImportingCircle2d parent;
    private int currentIndex;
    private Image frameImage;
    private Image logoImage;
    private JLabel imagePanel;
    
    utils.Polygon submitBtnCircle;
    public boolean submitBtnClicked;
    boolean submitBtnCircleMaked;


    private CatalogEmailSendingPage()
    {
//        this.parent = ImportingCircle2d.getInstance();
        try
        {
            frameImage = ImageIO.read(new File("icons\\ImageFrame3.png"));
            logoImage = ImageIO.read(new File("icons\\logo.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        initComponents();
    }

    public static CatalogEmailSendingPage getInstance()
    {
        if (instance == null)
            instance = new CatalogEmailSendingPage();
        return instance;
    }


    private static void initializeLayout(JPanel panel)
    {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        for (int i = 0; i < 5; i++)
        {
            c.gridx = i;
            JLabel temp = new JLabel("");
            panel.add(temp, c);
        }

        c.gridx = 0;
        for (int i = 0; i < 3; i++)
        {
            c.gridy = i;
            JLabel temp = new JLabel("");
            panel.add(temp, c);
        }
    }

    private void initComponents()
    {
        
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                importingcircle2d.ImportingCircle2d.getInstance().hideKeyBoard();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        setSize(importingcircle2d.ImportingCircle2d.getInstance().getSize());
        setLocation(0, 0);
        setBackground(Color.WHITE);

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
//        initializeLayout(this);
        GridBagConstraints c = new GridBagConstraints();


        

        c.ipady = 1;
        c.insets = new Insets(10, 0, 0, 0);
        c.weighty = 0;
        c.weightx = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        
        
        
        add(new JLabel(), c);
        
        c.ipady = 1;
        c.insets = new Insets(10, 0, 0, 0);
        c.weighty = 0;
        c.weightx = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        
        
        String infoMsg0 = "محل تبلیغات شما";
        infoLable0 = new JLabel(infoMsg0);
        infoLable0.setFont(headingFont);
        add(infoLable0, c);
        
        int i = 3;
        
        c.ipady = 20;
        c.insets = new Insets(0, 0, 0, 0);
        c.weighty = 0;
        c.weightx = 0;
        c.gridx = 0;
        c.gridy = i++;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;

        String infoMsg = "لطفا، ایمیل خود را برای دریافت عکس سلفی وارد نمایید";
        infoLable = new JLabel(infoMsg);
        infoLable.setFont(headingFont);
        add(infoLable, c);

        c.insets = new Insets(10, 0, 0, 20);
        c.ipadx = 0;
        c.gridx = 1;
        c.gridy = i;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.LINE_END;
        emailLabel = new JLabel("ایمیل:");
        emailLabel.setFont(bodyFont);
        add(emailLabel, c);

        c.ipadx = 200;
        c.gridx = 0;
        c.gridy = i++;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        emailInputField = new TouchJTextField("", "example@host.com", importingcircle2d.ImportingCircle2d.getInstance());
        emailInputField.setColumns(45);
        emailInputField.setEnterActionPerformListener(new EnterActionPerformListener() {
            @Override
            public void EnterActionPerform() {
                CatalogEmailSendingPage.this.EnterActionPerform();
            }
        });
        add(emailInputField, c);


        submitBtn = new JButton("         "+"ارسال"+"         "){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                if(submitBtnCircle!=null)
                    submitBtnCircle.draw(g);
            }
        };
        submitBtn.setFont(bodyFont);
        c.ipadx = 100;
        c.ipady = 100;
        c.gridx = 0;
        c.gridy = i++;
        c.gridwidth = 2;
        c.gridheight= 3;
        c.fill = GridBagConstraints.NONE;
        add(submitBtn, c);

        c.insets = new Insets(40, 0, 0, 0);
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = i++;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        statusLabel = new JLabel(" ");
        statusLabel.setFont(bodyFont);
        add(statusLabel, c);

        
        
        submitBtn.setBorder(null);
        submitBtn.setBorderPainted(false);
        submitBtn.setContentAreaFilled(false);
        submitBtn.setOpaque(false);
        submitBtn.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {

            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                
                submitBtnClicked=true;
                if(!submitBtnCircleMaked){
                    submitBtnCircle=new utils.Polygon(100, submitBtn.getWidth()/2, submitBtn.getHeight()/2, 0);
                    submitBtnCircle.setFirstColor(new Color(0, 255, 0, 44));
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(submitBtnClicked){
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(CatalogEmailSendingPage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if(submitBtnCircle.getRadius()<submitBtn.getWidth()/2)
                                submitBtnCircle.setRadius(submitBtnCircle.getRadius()+1);
                            submitBtn.repaint();
                            
                            
                            
                        }
                    }
                }).start();
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                if(submitBtnClicked==true)
                    infoLable.requestFocus();
                
                submitBtnClicked=false;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(!submitBtnClicked){
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(CatalogEmailSendingPage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if(submitBtnCircle.getRadius()>0)
                                submitBtnCircle.setRadius(submitBtnCircle.getRadius()-1); 
                            submitBtn.repaint();
                            
                            
                            
                        }
                    }
                }).start();
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                if(submitBtnClicked==true)
                    infoLable.requestFocus();
                
                submitBtnClicked=false;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(!submitBtnClicked){
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(CatalogEmailSendingPage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if(submitBtnCircle!=null&&submitBtnCircle.getRadius()>0)
                                submitBtnCircle.setRadius(submitBtnCircle.getRadius()-1); 
                            submitBtn.repaint();
                            
                            
                            
                        }
                    }
                }).start();
            }
        });
        submitBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                EnterActionPerform();
            }
        });

        emailInputField.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                resetStatusLabel();
            }

            @Override
            public void focusLost(FocusEvent e)
            {

            }
        });
//        submitBtn.requestFocus();

        setFocusTraversalPolicy(new FocusTraversalPolicy()
        {
            @Override
            public Component getComponentAfter(Container aContainer, Component aComponent)
            {
                if (aComponent.equals(emailInputField))
                    return submitBtn;
                return emailInputField;
            }

            @Override
            public Component getComponentBefore(Container aContainer, Component aComponent)
            {
                if (aComponent.equals(emailInputField))
                    return submitBtn;
                return emailInputField;
            }

            @Override
            public Component getFirstComponent(Container aContainer)
            {
                return submitBtn;
            }

            @Override
            public Component getLastComponent(Container aContainer)
            {
                return emailInputField;
            }

            @Override
            public Component getDefaultComponent(Container aContainer)
            {
                return submitBtn;
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                requestFocusInWindow();
            }
        });
//        currentIndex = ImportingCircle2d.getInstance().addPanel(this);
    }

    private void resetStatusLabel()
    {
        statusLabel.setText(" ");
    }

    private void setVisibleAll(boolean b)
    {
        infoLable.setVisible(b);
        emailInputField.setVisible(b);
        emailLabel.setVisible(b);
        submitBtn.setVisible(b);
    }

    @Override
    public void EnterActionPerform()
    {
        if (isValidEmailAddress(emailInputField.getValidText()))
        {
            String tempEmail = emailInputField.getValidText();
            submitBtn.setEnabled(false);
            setVisibleAll(false);
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    DBManager.getMyInstance().addEmail(tempEmail);
                    ImageUtilities.saveImage(ImageUtilities.logolizeImage(userImg, logoImage), "image.jpg");
                    EmailUtils.send(tempEmail, "image.jpg", "عکس سلفی شما.jpg");
                    new File("image.jpg").delete();
                }
            }).start();
            statusLabel.setFont(headingFont.deriveFont(22.0f));
            String text = "عکس به آدرس " + tempEmail + " <font color='green'>ارسال شد</font> <br> عکس شما از روی سرور مرکزی پاک شد";
            statusLabel.setText("<html><div style='text-align: center;'>" + text + "</div></html>");
            statusLabel.setHorizontalAlignment(JLabel.CENTER);
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        Thread.sleep(3000);
                    } catch (InterruptedException e1)
                    {
                        e1.printStackTrace();
                    }
                    resetStatusLabel();
                    emailInputField.setText("");
                    importingcircle2d.ImportingCircle2d.getInstance().showPage(BlankPage.getInstance());
                    NavPage.getInstance().setVisible(true);
                    importingcircle2d.ImportingCircle2d.getInstance().hideKeyBoard();
                }
            }).start();
        } else
        {
            statusLabel.setFont(bodyFont);
            if(emailInputField.getValidText()==(String)"")
                statusLabel.setText("لطفا ایمیل را وارد کنید");
            else
                statusLabel.setText("<html>ایمیل <font color='red'>اشتباه</font> وارد شده است</html>");
        }
    }

    @Override
    public int getPanelIndex()
    {
        return currentIndex;
    }

    @Override
    public void beforeShow()
    {

    }

    @Override
    public void afterShow()
    {
//        ImportingCircle2d.getInstance().showNavbar();
        setVisibleAll(true);
        submitBtn.setEnabled(true);
        emailInputField.getGhostText().setEmpty(true);
        emailInputField.requestFocus();
        submitBtn.requestFocus();
//        ImportingCircle2d.getInstance().showLogo();
    }

    @Override
    public void beforeDispose()
    {

    }

    @Override
    public void afterDispose()
    {

    }

    @Override
    public void beforeKeyboardShow() {
        if(imagePanel != null) remove(imagePanel);
        invalidate();
        updateUI();
        repaint();
    }

    @Override
    public void afterKeyboardDispose() {
        if(imagePanel != null) addImagePanel();
        invalidate();
        updateUI();
        repaint();
    }

    public void setImage(Image img)
    {
        userImg = img;

        double imgAspectRatio = userImg.getWidth(null) / ((double) userImg.getHeight(null));
        int finalHeight = (getHeight()) / 3, finalWidth = (int) (finalHeight * imgAspectRatio);
        ImageIcon imgIcon = framifyImage(userImg, logoImage, frameImage, finalWidth, finalHeight);


        if (imagePanel == null)
        {
            imagePanel = new JLabel(imgIcon);
            addImagePanel();
        } else imagePanel.setIcon(imgIcon);
        repaint();
    }

    private void addImagePanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.ipady = 20;
        c.insets = new Insets(10, 0, 30, 0);
        c.weighty = 0;
        c.weightx = 0;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        add(imagePanel, c);
    }
}

