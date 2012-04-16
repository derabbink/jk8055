package org.beblue.k8055.view;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;


public class ConnectionPane extends JPanel
{
    private static final long serialVersionUID = 1L;

    public final static String ACTION_CMD_CONNECT = "connect";
    
    public final static String ACTION_CMD_DISCONNECT = "disconnect";

    private JButton connect;

    private JButton disconnect;

    private JCheckBox sk5;

    private JCheckBox sk6;

    public ConnectionPane(String title)
    {
        sk5 = new JCheckBox("SK5", true);
        sk6 = new JCheckBox("SK6", true);

        connect = new JButton("Connect");
        disconnect = new JButton("Disconnect");

        connect.setActionCommand(ConnectionPane.ACTION_CMD_CONNECT);
        disconnect.setActionCommand(ConnectionPane.ACTION_CMD_DISCONNECT);

        add(sk5);
        add(sk6);
        add(connect);
        add(disconnect);

        setBorder(BorderFactory.createTitledBorder(title));
    }

    public void addActionListener(ActionListener l)
    {
        connect.addActionListener(l);
        disconnect.addActionListener(l);
    }

    public void removeActionListener(ActionListener l)
    {
        connect.removeActionListener(l);
        disconnect.removeActionListener(l);
    }

    public boolean getSk5()
    {
        return sk5.isSelected();
    }

    public boolean getSk6()
    {
        return sk6.isSelected();
    }

    public void enableConnection(boolean state)
    {
        connect.setEnabled(state);
        sk5.setEnabled(state);
        sk6.setEnabled(state);

        disconnect.setEnabled(!state);
    }
}
