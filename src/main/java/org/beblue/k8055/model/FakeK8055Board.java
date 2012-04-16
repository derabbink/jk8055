package org.beblue.k8055.model;

import java.io.IOException;

public class FakeK8055Board implements K8055Board
{
    private byte[] data_out = new byte[8];

    public FakeK8055Board()
    {
    }

    public void connect(boolean sk5, boolean sk6) throws IOException
    {
        System.out
                .printf("Connect called with sk5[%b] and sk6[%b]\n", sk5, sk6);
    }

    public void disconnect()
    {
        System.out.println("Disconnect called");
    }

    public void resetCounter0()
    {
        data_out[4] = 0;
    }

    public void resetCounter1()
    {
        data_out[6] = 0;
    }

    public void setAllDigitialOutputs(int pads, int da0, int da1)
    {
        setDigitalOutputPads(pads);
        setDigitalToAnalog0(da0);
        setDigitalToAnalog1(da1);
    }

    public void setDigitalOutputPads(int value)
    {
        checkValue(value, 0, 1 << NBR_OUTPUT_PADS);
        data_out[1] = (byte) value;
    }

    public void setDigitalToAnalog0(int value)
    {
        checkValue(value, 0, 255);
        data_out[2] = (byte) value;
    }

    public void setDigitalToAnalog1(int value)
    {
        checkValue(value, 0, 255);
        data_out[3] = (byte) value;
    }

    private void checkValue(int v, int min, int max)
    {
        if (v < min || v > max) {
            throw new IllegalArgumentException(String.format(
                    "Value is out of range: %d (%d - %d)", v, min, max));
        }
    }

    public void getAllInputs(InputControls in)
    {
        in.setAnalogToDigital0(getAnalog0());
        in.setAnalogToDigital1(getAnalog1());
        in.setCounter0(getCounter0());
        in.setCounter1(getCounter1());
        in.setInputPads(getDigitalInputs());
    }

    public int getAnalog0()
    {
        return data_out[2] & 0xff;
    }

    public int getAnalog1()
    {
        return data_out[3] & 0xff;
    }

    public int getCounter0()
    {
        return data_out[2] & 0xff; //data_out[4];
    }

    public int getCounter1()
    {
        return data_out[3] & 0xff; //data_out[6];
    }

    public int getDigitalInputs()
    {
        return data_out[1] & 0x1f;
    }
}
