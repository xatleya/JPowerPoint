package Vue;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class StatusPanel extends JPanel{    //barre de status
    private JLabel statusLabel;
    private String status = new String("Select");   //status en cours (select, fill, ...)
    
    public StatusPanel(JFrame frame) {
        this.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.setPreferredSize(new Dimension(frame.getWidth(),16));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.statusLabel = new JLabel("[" + this.status + "] tool selected");
        this.statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(statusLabel);
    }

    public void setStatus(String status) {  //ajoute un nouveau status à la barre de status
        this.status = status;
        this.statusLabel.setText("[" + status + "] tool selected");
    }

    public String getStatus() {
        return status;
    }
}
