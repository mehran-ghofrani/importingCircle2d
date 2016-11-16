package pages.camera.uiComponents;


import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import pages.camera.uiComponents.pages.CatalogEmailSendingPage;
import pages.camera.uiComponents.uiInterfaces.EnterActionPerformListener;

public class TouchJTextField extends JTextField
{
    private EnterActionPerformListener enterActionPerformListener;
    private GhostText ghostText;


    private boolean isSensetiveToTouch;
    private importingcircle2d.ImportingCircle2d parent;
    private String text;

    public TouchJTextField(String text, String ghostText, importingcircle2d.ImportingCircle2d parent)
    {
        super(text);
        this.parent = parent;

        initComponent(text, ghostText);
    }

    private void initComponent(String text, String ghostText)
    {
        isSensetiveToTouch = true;
        setColumns(25);
        this.ghostText = new GhostText(this, ghostText);


        addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                parent.showKeyBoard(TouchJTextField.this, TouchJTextField.this.enterActionPerformListener);
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                if(!CatalogEmailSendingPage.getInstance().submitBtnClicked)
                    parent.hideKeyBoard();
            }
        });

    }

    public String getValidText()
    {
        return ghostText == null || ghostText.isEmpty() ? "" : super.getText();
    }

    public GhostText getGhostText()
    {
        return ghostText;
    }

    public boolean isSensetiveToTouch()
    {
        return isSensetiveToTouch;
    }
    public EnterActionPerformListener getEnterActionPerformListener()
    {
        return enterActionPerformListener;
    }

    public void setEnterActionPerformListener(EnterActionPerformListener enterActionPerformListener)
    {
        this.enterActionPerformListener = enterActionPerformListener;
    }
}
