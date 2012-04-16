package org.beblue.k8055.model;

import java.io.IOException;

/**
 * Interface representing the K8055 board.
 * 
 * @author Mario Boikov
 *
 */
public interface K8055Board
{
    final static int NBR_INPUT_PADS = 5;

    final static int NBR_OUTPUT_PADS = 8;

    void connect(boolean sk5, boolean sk6) throws IOException;

    void disconnect();

    /* Write operations */

    void setDigitalOutputPads(int value);

    void resetCounter0();

    void resetCounter1();

    void setDigitalToAnalog0(int value);

    void setDigitalToAnalog1(int value);

    void setAllDigitialOutputs(int pads, int da0, int da1);

    /* Read operations */

    int getDigitalInputs();

    int getCounter0();

    int getCounter1();

    int getAnalog0();

    int getAnalog1();

    void getAllInputs(InputControls in);

}
