package pages.camera.uiComponents;

import pages.camera.uiComponents.pages.EntrancePage;

/**
 * Created by Mactabi on 14/08/2016.
 */
public class TimerPanel {
    private int spentTime;
    public TimerPanel(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                spentTime++;
                if(spentTime==30)
                    ImportingCircle2d.getInstance().showPanel(EntrancePage.getInstance().getPanelIndex());
            }

        }).start();






    }
    void restart(){
        spentTime=0;
    }


}
