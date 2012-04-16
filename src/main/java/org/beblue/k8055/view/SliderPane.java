package org.beblue.k8055.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

public class SliderPane extends JPanel
{
    private static final long serialVersionUID = 1L;

    private JTextField value;

    private JSlider slider;

    public SliderPane(String title, int min, int max)
    {
        setLayout(new MigLayout());

        slider = new JSlider(SwingConstants.VERTICAL, min, max, 0);
        slider.setBorder(BorderFactory.createTitledBorder(title));

        slider.setMajorTickSpacing(max / 8);
        slider.setPaintTicks(true);

        add(slider, "wrap");

        value = new JTextField("0", 4);
        value.setHorizontalAlignment(SwingConstants.CENTER);
        add(value);

        value.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                final int newVal = Integer.parseInt(value.getText());
                final int oldVal = slider.getValue();
                slider.setValue(newVal);
                firePropertyChange("value", oldVal, newVal);
            }

        });

        slider.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e)
            {
                final int newVal = slider.getValue();
                final int oldVal = Integer.parseInt(value.getText());
                value.setText(Integer.toString(newVal));
                firePropertyChange("value", oldVal, newVal);
            }

        });
    }

    public void setValue(int value)
    {
        if (value < slider.getMinimum() || value > slider.getMaximum()) {
            throw new IllegalArgumentException("Value out of boundries");
        }

        this.value.setText(Integer.toString(value));
        this.slider.setValue(value);
    }

    public int getValue()
    {
        return this.slider.getValue();
    }

    public void setEnabled(boolean state)
    {
        value.setEnabled(state);
        slider.setEnabled(state);
    }
}
