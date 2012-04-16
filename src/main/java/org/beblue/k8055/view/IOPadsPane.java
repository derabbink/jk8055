package org.beblue.k8055.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IOPadsPane extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private JCheckBox input[];

    private JTextField value = new JTextField(3);

    private int currentValue;

    public IOPadsPane(int nbrPads, String title, boolean readOnly)
    {
        setBorder(BorderFactory.createTitledBorder(title));

        input = new JCheckBox[nbrPads];

        while (--nbrPads >= 0) {
            String buttonId = Integer.toString(nbrPads);
            input[nbrPads] = new JCheckBox(buttonId);
            input[nbrPads].setActionCommand(buttonId);

            if (readOnly) {
                input[nbrPads].setEnabled(false);
            } else {
                input[nbrPads].addActionListener(this);
            }

            add(input[nbrPads]);
        }

        if (readOnly) {
            value.setEditable(false);
        }

        value.setActionCommand("new_value");
        value.addActionListener(this);
        setValue(0);

        add(new JLabel("Value (hex): "));
        add(value);
    }

    public void setValue(int val)
    {
        updateTextField(val);
        updateCheckboxes(val);
    }

    public int getValue()
    {
        return currentValue;
    }

    public void actionPerformed(ActionEvent e)
    {
        final int oldVal = currentValue;

        String cmd = e.getActionCommand();
        if ("new_value".equals(cmd)) {

            currentValue = Integer.parseInt(value.getText(), 16);
            updateCheckboxes(currentValue);
            firePropertyChange("value", oldVal, currentValue);

        } else {

            int index = 1 << Integer.parseInt(cmd);
            boolean state = ((JCheckBox) e.getSource()).isSelected();
            if (state) {
                currentValue |= index;
            } else {
                currentValue &= ~index;
            }
            updateTextField(currentValue);
            firePropertyChange("value", oldVal, currentValue);

        }
    }

    private void updateCheckboxes(int val)
    {
        for (int i = 0; i < input.length; ++i) {
            final boolean sel = ((val >> i) & 1) == 1 ? true : false;
            input[i].setSelected(sel);
        }
    }

    private void updateTextField(int val)
    {
        value.setText(String.format("%02X", val));
    }

    public void setEnabled(boolean state)
    {
        for (JCheckBox b : input) {
            b.setEnabled(state);
        }

        value.setEnabled(state);
    }
}
