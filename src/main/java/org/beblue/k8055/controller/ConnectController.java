package org.beblue.k8055.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import org.beblue.k8055.model.InputControls;
import org.beblue.k8055.model.K8055Board;
import org.beblue.k8055.view.ConnectionPane;
import org.beblue.k8055.view.MainFrame;

public class ConnectController implements ActionListener
{
    public final static String ACTION_CMD_POLL_INPUT = "poll.input";

    private MainFrame frame;

    private K8055Board board;

    private Timer pollTimer;

    private InputControls inCtrls = new InputControls();

    public ConnectController(K8055Board board, MainFrame frame)
    {
        this.board = board;
        this.frame = frame;

        frame.enableAll(false);
        frame.getConnection().enableConnection(true);
        frame.getConnection().addActionListener(this);

        pollTimer = new Timer(50, this);
        pollTimer.setActionCommand(ACTION_CMD_POLL_INPUT);
        pollTimer.setInitialDelay(0);

        frame.setStatusBarMessage("Disconnected");
    }

    public void actionPerformed(ActionEvent e)
    {
        final String cmd = e.getActionCommand();

        final ConnectionPane connPane = frame.getConnection();

        if (ConnectionPane.ACTION_CMD_CONNECT.equals(cmd)) {

            try {
                frame.setStatusBarMessage("Connecting...");
                board.connect(connPane.getSk5(), connPane.getSk6());
                frame.enableAll(true);
                connPane.enableConnection(false);
                pollTimer.start();
                frame.setStatusBarMessage("Connected");
            } catch (IOException ex) {
                frame.setStatusBarMessage("Error: " + ex.getMessage());
            }

        } else if (ConnectionPane.ACTION_CMD_DISCONNECT.equals(cmd)) {

            frame.setStatusBarMessage("Disconnecting...");
            pollTimer.stop();
            board.disconnect();
            frame.enableAll(false);
            connPane.enableConnection(true);
            frame.setStatusBarMessage("Disconnected");

        } else if (ACTION_CMD_POLL_INPUT.equals(cmd)) {
            board.getAllInputs(inCtrls);
            frame.getInputPads().setValue(inCtrls.getInputPads());
            frame.getAnalogToDigital0().setValue(inCtrls.getAnalogToDigital0());
            frame.getAnalogToDigital1().setValue(inCtrls.getAnalogToDigital1());
            frame.getCounter0().setValue(inCtrls.getCounter0());
            frame.getCounter1().setValue(inCtrls.getCounter1());
        }
    }
}
