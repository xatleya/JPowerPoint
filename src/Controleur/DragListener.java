package Controleur;

import Modele.Resizable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.event.MouseInputAdapter;


public class DragListener implements MouseListener, MouseMotionListener{
    Resizable zr;
    int width, height, x, y, xtr, ytr, xtl, ytl, xbr, ybr, xbl, ybl;  //t = top, b=bot, r=right, l=left;
    public DragListener(Resizable zr) {
        this.zr = zr;
    }
    
    @Override
    public void mouseMoved(MouseEvent me){
        
    }
    
    @Override
    public void mouseDragged(MouseEvent me) {
        JButton button = (JButton)me.getSource();
        if(button == zr.getDragBotRight() && (width+me.getX()>5 && height+me.getY()>5)){
            if(x+me.getX()<zr.getCurrentSlideView().getWidth()){
                zr.setSize(width+me.getX(), height+me.getY());
                zr.getTextZone().setBounds(1,1, width+me.getX()-2, height+me.getY()-2);
                zr.getDragTopRight().setLocation(xtr+me.getX(), ytr);
                zr.getDragBotLeft().setLocation(xbl, ybl+me.getY());
            }
        }else if(button == zr.getDragTopLeft() && (width-me.getX() > 5 && height-me.getY() > 5)){
            if(x+me.getX()>0 || y+me.getY()>0){ //to prevent growing outside the slide
                zr.setSize(width-me.getX(), height-me.getY());
                zr.getTextZone().setBounds(1,1, width-me.getX()-2, height-me.getY()-2);
                zr.setLocation(x+me.getX(),y+me.getY());
                zr.getDragTopRight().setLocation(xtr, ytr+me.getY());
                zr.getDragBotLeft().setLocation(xbl+me.getX(), ybl);
            }
        }else if(button == zr.getDragTopRight() && (width+me.getX()>5 && height-me.getY()>5)){
            zr.setSize(width+me.getX(), height-me.getY());
            zr.getTextZone().setBounds(1,1, width+me.getX()-2, height-me.getY()-2);
            zr.setLocation(x,y+me.getY());
            zr.getDragTopLeft().setLocation(xtl, ytl+me.getY());
            zr.getDragBotRight().setLocation(xbr+me.getX(),ybr);
        }else if(button == zr.getDragBotLeft() && (width-me.getX()>5 & height+me.getY()>5)){
            zr.setSize(width-me.getX(), height+me.getY());
            zr.getTextZone().setBounds(1,1, width-me.getX()-2, height+me.getY()-2);
            zr.setLocation(x+me.getX(),y);
            zr.getDragTopLeft().setLocation(xtl+me.getX(),ytl);
            zr.getDragBotRight().setLocation(xbr, ybr+me.getY());
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        width = zr.getWidth();
        height = zr.getHeight();
        x = zr.getX();
        y = zr.getY();
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
        JButton button = (JButton)me.getSource();
        if(button == zr.getDragBotRight()){
            zr.getDragBotRight().setLocation(zr.getDragTopRight().getX(), zr.getDragBotLeft().getY());
        }else if(button == zr.getDragTopLeft()){
            zr.getDragTopLeft().setLocation(zr.getDragBotLeft().getX(), zr.getDragTopRight().getY());
        }else if(button == zr.getDragTopRight()){
            zr.getDragTopRight().setLocation(zr.getDragBotRight().getX(), zr.getDragTopLeft().getY());
        }else if(button == zr.getDragBotLeft()){
            zr.getDragBotLeft().setLocation(zr.getDragTopLeft().getX(), zr.getDragBotRight().getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
}
