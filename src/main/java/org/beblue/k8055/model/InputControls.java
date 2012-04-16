package org.beblue.k8055.model;

/**
 * Container class for all input controls on the board.
 * 
 * @author Mario Boikov
 * 
 */
public class InputControls
{
    private int inputPads;

    private int analogToDigital0;

    private int analogToDigital1;

    private int counter0;

    private int counter1;

    /**
     * @return the inputPads
     */
    public int getInputPads()
    {
        return inputPads;
    }

    /**
     * @param inputPads the inputPads to set
     */
    public void setInputPads(int inputPads)
    {
        this.inputPads = inputPads;
    }

    /**
     * @return the analogToDigital0
     */
    public int getAnalogToDigital0()
    {
        return analogToDigital0;
    }

    /**
     * @param analogToDigital0 the analogToDigital0 to set
     */
    public void setAnalogToDigital0(int analogToDigital0)
    {
        this.analogToDigital0 = analogToDigital0;
    }

    /**
     * @return the analogToDigital1
     */
    public int getAnalogToDigital1()
    {
        return analogToDigital1;
    }

    /**
     * @param analogToDigital1 the analogToDigital1 to set
     */
    public void setAnalogToDigital1(int analogToDigital1)
    {
        this.analogToDigital1 = analogToDigital1;
    }

    /**
     * @return the counter0
     */
    public int getCounter0()
    {
        return counter0;
    }

    /**
     * @param counter0 the counter0 to set
     */
    public void setCounter0(int counter0)
    {
        this.counter0 = counter0;
    }

    /**
     * @return the counter1
     */
    public int getCounter1()
    {
        return counter1;
    }

    /**
     * @param counter1 the counter1 to set
     */
    public void setCounter1(int counter1)
    {
        this.counter1 = counter1;
    }
}
