package Vue;

import Modele.Resizable;
import Controleur.SelectCurrentPanelListener;
import Modele.Item;
import Tools.TextToolListener;
import Modele.Presentation;
import Tools.ToolOval;
import Tools.ToolRectangle;
import Tools.ToolSelect;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar {
    private Presentation presentation;
    private MainFrame mainFrame;
    private Color mainColor = Color.BLACK;
    private Color borderColor = Color.BLACK;
    

    
    public Toolbar(Presentation presentation, MainFrame mainFrame){
        this.setRollover(true);
        this.presentation = presentation;
        this.mainFrame = mainFrame;
        
        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(1,2,4,0));
        JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new GridLayout(1,8,4,0));
        
        generalPanel.add(new ToolSelect(this.mainFrame, this.presentation));
        
        JButton textZoneButton = new JButton("Text");
        ActionListener textZoneButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               mainFrame.getCurrentSlideView().removeListeners();
               mainFrame.getCurrentSlideView().addMouseListener(new TextToolListener(presentation));
               mainFrame.getStatusPanel().setStatus("Text");
            }        
        };
        textZoneButton.addActionListener(textZoneButtonListener);
        generalPanel.add(textZoneButton);
        
        JButton deleteButton = new JButton("Delete");
        ActionListener DeleteButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               for(Item current : presentation.getCurrentSlideModel().getItemsCurrentSlide()) {
                    if(current.isSelected()) {
                        presentation.getCurrentSlideModel().getItemsCurrentSlide().remove(current);
                        presentation.notifyObserver();
                        break;
                    }
                }
            }        
        };
        deleteButton.addActionListener(DeleteButtonListener);
        generalPanel.add(deleteButton);


        JButton mainColorButton = new JButton();
        mainColorButton.setBackground(Color.black);
        ActionListener mainColorButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               Color newColor = JColorChooser.showDialog(null, "Change background color",mainColorButton.getBackground());
               if(newColor != null){
                   mainColorButton.setBackground(newColor);
                   presentation.setTextColor(newColor);
                   mainColor = newColor;
                   for(Component current : presentation.getCurrentSlideModel().getItemsCurrentSlide()) {
                       if(current instanceof Resizable) {
                           Resizable resizable = (Resizable)current;
                           if(resizable.isSelected()) {
                                resizable.getTextZone().setForeground(newColor);
                                presentation.notifyObserver();
                           }
                       }
                   }
               }
            }        
        };
        mainColorButton.addActionListener(mainColorButtonListener);
        colorPanel.add(mainColorButton);
        
        JButton borderColorButton = new JButton();
        borderColorButton.setBackground(Color.black);
        ActionListener borderColorButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               Color newColor = JColorChooser.showDialog(null, "Change background color",borderColorButton.getBackground());
               if(newColor != null){
                   borderColorButton.setBackground(newColor);
                   borderColor = newColor;
               }
            }        
        };
        borderColorButton.addActionListener(borderColorButtonListener);
        colorPanel.add(borderColorButton);
        
        
        ToolOval toolOval = new ToolOval(this.mainFrame);
        generalPanel.add(toolOval);
        
        ToolRectangle toolRectangle = new ToolRectangle(this.mainFrame);
        generalPanel.add(toolRectangle);
        
        generalPanel.add(colorPanel);
        
        String[] fonts = { "Arial", "Calibri", "Vladimir Script", "Times New Roman", "Sylfaen"};
        JComboBox fontsList = new JComboBox(fonts);
        ActionListener fontsListListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                presentation.setFont(new Font((String)fontsList.getSelectedItem(), Font.PLAIN, presentation.getTextSize()));
            }        
        };
        fontsList.addActionListener(fontsListListener);
        
        String[] textSizes = { "15", "30", "42", "60", "72" };
        JComboBox sizeList = new JComboBox(textSizes);
        sizeList.setSelectedIndex(1);
        ActionListener sizeListListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedSize = Integer.parseInt((String)sizeList.getSelectedItem());
                presentation.setTextSize(selectedSize);
                presentation.setFont(new Font((String)fontsList.getSelectedItem(), Font.PLAIN,selectedSize));
            }        
        };
        sizeList.addActionListener(sizeListListener);
        
        generalPanel.add(sizeList);
        generalPanel.add(fontsList);
        
        this.add(generalPanel);
    } 

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public Color getMainColor() {
        return mainColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }
}
