package Controleur;

import Modele.Resizable;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import javax.swing.BorderFactory;

public class ResizableListener implements MouseListener, MouseMotionListener, Serializable{
    int x, y, xtr, ytr, xtl, ytl, xbr, ybr, xbl, ybl, xmouse, ymouse; 
    
    @Override
    public void mouseClicked(MouseEvent me) {
        Resizable zr = (Resizable)me.getSource();
        zr.setBorder(BorderFactory.createTitledBorder(""));
        zr.repaint();
        zr.setSelected(true);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        Resizable zr = (Resizable)me.getSource();
        x = zr.getX();
        y = zr.getY();
        xmouse = me.getXOnScreen();
        ymouse = me.getYOnScreen();
        xtr = zr.getDragTopRight().getX();
        ytr = zr.getDragTopRight().getY();
        xtl = zr.getDragTopLeft().getX();
        ytl = zr.getDragTopLeft().getY(); 
        xbr = zr.getDragBotRight().getX();
        ybr = zr.getDragBotRight().getY();
        xbl = zr.getDragBotLeft().getX();
        ybl = zr.getDragBotLeft().getY();  
    }

    @Override
    public void mouseReleased(MouseEvent me) {      
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
       Resizable zr = (Resizable)me.getSource();
       if(zr.getCursor().getType() == Cursor.MOVE_CURSOR){
           int movex = me.getXOnScreen() - xmouse;
           int movey = me.getYOnScreen() - ymouse;
           zr.setLocation(x+movex, y+movey);
           zr.getDragTopLeft().setLocation(xtl+movex, ytl+movey);
           zr.getDragTopRight().setLocation(xtr+movex, ytr+movey);
           zr.getDragBotLeft().setLocation(xbl+movex, ybl+movey);
           zr.getDragBotRight().setLocation(xbr+movex, ybr+movey);
       }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        Resizable zr = (Resizable)me.getSource();
        if((me.getX() < 3) || (me.getX() > zr.getWidth()-3) || (me.getY() < 3) || (me.getY() > zr.getHeight()-3)){
            zr.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        }else{
            zr.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
    
}
