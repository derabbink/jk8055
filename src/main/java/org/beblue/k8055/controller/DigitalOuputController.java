package org.beblue.k8055.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.beblue.k8055.model.K8055Board;
import org.beblue.k8055.view.MainFrame;

public class DigitalOuputController implements PropertyChangeListener
{

    private K8055Board board;

    private MainFrame gui;

    public DigitalOuputController(K8055Board board, MainFrame gui)
    {
        this.board = board;
        this.gui = gui;
        gui.getOutputPads().addPropertyChangeListener(this);
        gui.getDigitalToAnalog0().addPropertyChangeListener(this);
        gui.getDigitalToAnalog1().addPropertyChangeListener(this);
    }

    public void propertyChange(PropertyChangeEvent evt)
    {
        if ("value".equals(evt.getPropertyName())) {
            final int digPads = gui.getOutputPads().getValue();
            final int da0 = gui.getDigitalToAnalog0().getValue();
            final int da1 = gui.getDigitalToAnalog1().getValue();
            board.setAllDigitialOutputs(digPads, da0, da1);
        }
    }
}
