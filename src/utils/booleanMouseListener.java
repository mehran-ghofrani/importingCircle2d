/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.event.MouseEvent;

/**
 *
 * @author Mactabi
 */
public interface booleanMouseListener {
    public boolean mousePressed(MouseEvent e);
    public boolean mouseClicked(MouseEvent e);
    public boolean mouseMoved(MouseEvent e);
    public boolean mouseDragged(MouseEvent e);
    public boolean mouseReleased(MouseEvent e);
}
