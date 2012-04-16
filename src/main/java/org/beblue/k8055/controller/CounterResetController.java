package org.beblue.k8055.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.beblue.k8055.model.K8055Board;
import org.beblue.k8055.view.MainFrame;

public class CounterResetController implements ActionListener
{
    public final static String COUNTER0_ACTION_CMD = "counter.0";

    public final static String COUNTER1_ACTION_CMD = "counter.1";

    private K8055Board board;

    public CounterResetController(final K8055Board board, final MainFrame frame)
    {
        this.board = board;

        frame.getCounter0().addResetActionListener(this);
        frame.getCounter1().addResetActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        final String cmd = e.getActionCommand();

        if (COUNTER0_ACTION_CMD.equals(cmd)) {
            board.resetCounter0();
        } else if (COUNTER1_ACTION_CMD.equals(cmd)) {
            board.resetCounter1();
        }
    }
}
