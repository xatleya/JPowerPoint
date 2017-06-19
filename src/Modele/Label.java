package Modele;

import javax.swing.JLabel;

public class Label extends Item{
    private JLabel myLabel;
    
    public Label(String text) {
        this.myLabel = new JLabel(text);
        this.add(this.myLabel);
    }

    public String getText() {
        return this.myLabel.getText();
    }

    public void setText(String text) {
        this.myLabel.setText(text);
    }

    public JLabel getMyLabel() {
        return myLabel;
    }

    @Override
    public Item itemCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
