package org.beblue.k8055.view;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.beblue.k8055.model.K8055Board;

public class MainFrame extends JFrame
{
    private static final long serialVersionUID = 1L;

    private IOPadsPane inputPads;

    private IOPadsPane outputPads;

    private SliderPane digitalToAnalog0;

    private SliderPane digitalToAnalog1;

    private SliderPane analogToDigital0;

    private SliderPane analogToDigital1;

    private CounterPane counter0;

    private CounterPane counter1;

    private ConnectionPane connection;

    private JLabel statusBar;

    public MainFrame(String title)
    {
        super(title);

        getContentPane().setLayout(new MigLayout("", "", ""));

        addDigitalToAnalogSlides();

        addAnalogToDigitalSlides();

        addIOPads();

        addCounters();

        addConnectButtons();

        addStatusBar();
    }

    private void addStatusBar()
    {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.setBorder(BorderFactory.createLoweredBevelBorder());

        statusBar = new JLabel();
        p.add(statusBar);

        getContentPane().add(p, "south");
    }

    private void addDigitalToAnalogSlides()
    {
        digitalToAnalog0 = new SliderPane("DA1", 0, 255);
        digitalToAnalog1 = new SliderPane("DA2", 0, 255);

        getContentPane().add(digitalToAnalog0, "span 1 3");
        getContentPane().add(digitalToAnalog1, "span 1 3");
    }

    private void addAnalogToDigitalSlides()
    {
        analogToDigital0 = new SliderPane("AD1", 0, 255);
        analogToDigital1 = new SliderPane("AD2", 0, 255);

        getContentPane().add(analogToDigital0, "span 1 3");
        getContentPane().add(analogToDigital1, "span 1 3");
    }

    private void addIOPads()
    {
        inputPads = new IOPadsPane(K8055Board.NBR_INPUT_PADS, "Input", true);
        outputPads = new IOPadsPane(K8055Board.NBR_OUTPUT_PADS, "Output", false);

        getContentPane().add(inputPads, " growx, wrap, align right");
        getContentPane().add(outputPads, "wrap, align right");
    }

    private void addCounters()
    {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createTitledBorder("Counters"));
        getContentPane().add(p, "growx, wrap");

        counter0 = new CounterPane("Counter 0", "counter.0");
        counter1 = new CounterPane("Counter 1", "counter.1");

        p.add(counter0);
        p.add(counter1);
    }

    private void addConnectButtons()
    {
        connection = new ConnectionPane("Connection");
        getContentPane().add(connection, "span 5");
    }

    public void setStatusBarMessage(String msg)
    {
        statusBar.setText(msg);
    }

    /**
     * @return the inputPads
     */
    public IOPadsPane getInputPads()
    {
        return inputPads;
    }

    /**
     * @return the outputPads
     */
    public IOPadsPane getOutputPads()
    {
        return outputPads;
    }

    /**
     * @return the digitalToAnalog0
     */
    public SliderPane getDigitalToAnalog0()
    {
        return digitalToAnalog0;
    }

    /**
     * @return the digitalToAnalog1
     */
    public SliderPane getDigitalToAnalog1()
    {
        return digitalToAnalog1;
    }

    public CounterPane getCounter0()
    {
        return counter0;
    }

    public CounterPane getCounter1()
    {
        return counter1;
    }

    /**
     * @return the analogToDigital0
     */
    public SliderPane getAnalogToDigital0()
    {
        return analogToDigital0;
    }

    /**
     * @return the analogToDigital1
     */
    public SliderPane getAnalogToDigital1()
    {
        return analogToDigital1;
    }

    /**
     * @return the connection
     */
    public ConnectionPane getConnection()
    {
        return connection;
    }

    public void enableAll(boolean state)
    {
        outputPads.setEnabled(state);
        digitalToAnalog0.setEnabled(state);
        digitalToAnalog1.setEnabled(state);
        counter0.setEnabled(state);
        counter1.setEnabled(state);
        connection.setEnabled(state);
    }
}
