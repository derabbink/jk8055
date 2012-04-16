package org.beblue.k8055.view;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

public class CounterPane extends JPanel
{

    private static final long serialVersionUID = 1L;

    private JTextField value = new JTextField(6);

    JButton reset = new JButton("Reset");

    public CounterPane(String title, String actionCmd)
    {
        setLayout(new MigLayout());
        setBorder(BorderFactory.createTitledBorder(title));

        value.setEditable(false);
        value.setHorizontalAlignment(SwingConstants.RIGHT);
        setValue(0);

        reset.setActionCommand(actionCmd);

        add(value, "wrap");
        add(reset);
    }

    public void addResetActionListener(ActionListener l)
    {
        reset.addActionListener(l);
    }

    public void removeResetActionListener(ActionListener l)
    {
        reset.removeActionListener(l);
    }

    public void setValue(int value)
    {
        this.value.setText(Integer.toString(value));
    }

    public void setEnabled(boolean state)
    {
        reset.setEnabled(state);
    }
}
