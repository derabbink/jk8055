package org.beblue.k8055.model;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.beblue.jna.usb.LibUSB;
import org.beblue.jna.usb.usb_bus;
import org.beblue.jna.usb.usb_dev_handle;
import org.beblue.jna.usb.usb_device;

import com.sun.jna.Native;

/**
 * Implements the USB communication with the board.
 * 
 * @author Mario Boikov
 * 
 */
public class K8055BoardImpl implements K8055Board
{
    private final static int VELLEMAN_VENDOR_ID = 0x10CF;

    private final static int K8055_PRODUCT_ID = 0x5500;

    private LibUSB usb = LibUSB.libUSB;

    private usb_dev_handle usbDevHandle;

    private Thread pollThread;

    ByteBuffer usbInPkt = ByteBuffer.allocate(8);

    ByteBuffer usbOutPkt = ByteBuffer.allocate(8);

    public K8055BoardImpl()
    {
    }

    public void connect(boolean sk5, boolean sk6) throws IOException
    {
        usb.usb_set_debug(255);
        usb.usb_init();
        usb.usb_find_busses();
        usb.usb_find_devices();

        short pid = createProductId(sk5, sk6);
        usb_device dev = findK8055Board(pid);

        for (usb_bus bus = usb.usb_get_busses(); bus != null; bus = bus.next) {
            System.out.printf("Bus filename: %s\n", Native
                    .toString(bus.dirname));
        }

        if (dev != null) {
            usbDevHandle = usb.usb_open(dev);

            if (!claimDevice(0)) {
                disconnect();
                throw new IOException("Can not claim the USB device");
            }

            // Send reset
            synchronized (usbOutPkt) {
                usbOutPkt.put(0, (byte) 0);
                usb.usb_interrupt_write(usbDevHandle, 0x01, usbOutPkt,
                        usbOutPkt.capacity(), 20);
            }

        } else {
            throw new IOException("Can not find the K8055 board");
        }
    }

    public void disconnect()
    {
        if (pollThread != null) {
            pollThread.interrupt();
            pollThread = null;
        }

        if (usbDevHandle != null) {
            usb.usb_close(usbDevHandle);
            usbDevHandle = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.beblue.k8055.model.K8055Board#getAllInputs(org.beblue.k8055.model
     * .InputControls)
     */
    public void getAllInputs(InputControls in)
    {
        usb.usb_interrupt_read(usbDevHandle, 0x81, usbInPkt, usbInPkt
                .capacity(), 20);

        if (usbInPkt.get(1) == 0x01) {
            in.setInputPads(extractDigitalInput(usbInPkt.get(0)));
            in.setAnalogToDigital0(usbInPkt.get(2) & 0xff);
            in.setAnalogToDigital1(usbInPkt.get(3) & 0xff);
            in.setCounter0(usbInPkt.get(4));
            in.setCounter1(usbInPkt.get(6));
        } else {
            System.err.println("Status != OK, should retry here");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.beblue.k8055.model.K8055Board#getAnalog0()
     */
    public int getAnalog0()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.beblue.k8055.model.K8055Board#getAnalog1()
     */
    public int getAnalog1()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.beblue.k8055.model.K8055Board#getCounter0()
     */
    public int getCounter0()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.beblue.k8055.model.K8055Board#getCounter1()
     */
    public int getCounter1()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.beblue.k8055.model.K8055Board#getDigitalInputs()
     */
    public int getDigitalInputs()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.beblue.k8055.model.K8055Board#resetCounter0()
     */
    public void resetCounter0()
    {
        synchronized (usbOutPkt) {
            usbOutPkt.put(0, (byte) 0x03);
            usbOutPkt.put(4, (byte) 0);
            usb.usb_interrupt_write(usbDevHandle, 0x01, usbOutPkt, usbOutPkt
                    .capacity(), 20);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.beblue.k8055.model.K8055Board#resetCounter1()
     */
    public void resetCounter1()
    {
        synchronized (usbOutPkt) {
            usbOutPkt.put(0, (byte) 0x04);
            usbOutPkt.put(5, (byte) 0);
            usb.usb_interrupt_write(usbDevHandle, 0x01, usbOutPkt, usbOutPkt
                    .capacity(), 20);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.beblue.k8055.model.K8055Board#setAllDigitialOutputs(int, int,
     * int)
     */
    public void setAllDigitialOutputs(int pads, int da0, int da1)
    {
        synchronized (usbOutPkt) {
            usbOutPkt.put(0, (byte) 0x05);
            usbOutPkt.put(1, (byte) pads);
            usbOutPkt.put(2, (byte) da0);
            usbOutPkt.put(3, (byte) da1);
            usb.usb_interrupt_write(usbDevHandle, 0x01, usbOutPkt, usbOutPkt
                    .capacity(), 20);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.beblue.k8055.model.K8055Board#setDigitalOutputPads(int)
     */
    public void setDigitalOutputPads(int value)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.beblue.k8055.model.K8055Board#setDigitalToAnalog0(int)
     */
    public void setDigitalToAnalog0(int value)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.beblue.k8055.model.K8055Board#setDigitalToAnalog1(int)
     */
    public void setDigitalToAnalog1(int value)
    {
        // TODO Auto-generated method stub

    }

    final private int extractDigitalInput(byte d)
    {
        return (((d >> 4) & 0x03) | /* Input 1 and 2 */
        ((d << 2) & 0x04) | /* Input 3 */
        ((d) >> 3) & 0x18); /* Input 4 and 5 */
    }

    /*
     * Product ID of the welleman board is 0x5500 + address config
     */
    private short createProductId(boolean sk5, boolean sk6)
    {
        int pId = K8055_PRODUCT_ID;
        pId += sk5 ? 0 : 1;
        pId += sk6 ? 0 : 1;
        return (short) pId;
    }

    private usb_device findK8055Board(short pid)
    {
        for (usb_bus bus = usb.usb_get_busses(); bus != null; bus = bus.next) {

            for (usb_device dev = bus.devices; dev != null; dev = dev.next) {

                if (dev.descriptor.idVendor == VELLEMAN_VENDOR_ID
                        && dev.descriptor.idProduct == pid) {
                    return dev;
                }

            }

        }

        return null;
    }

    private boolean claimDevice(int interf)
    {
        try {
            byte[] driverName = new byte[1024];

            int ret = usb.usb_get_driver_np(usbDevHandle, interf, driverName,
                    driverName.length);

            if (ret == 0) {
                if (usb.usb_detach_kernel_driver_np(usbDevHandle, interf) < 0) {
                    System.err.printf(
                            "Error while detaching kernel driver: %s\n", usb
                                    .usb_strerror());
                }
            } else {
                System.err.printf("Error when trying to get driver name: %s\n",
                        usb.usb_strerror());
            }
        } catch (UnsatisfiedLinkError e) {
            // This means that this non-portable function is not available
            // on the current platform.
        }

        if (usb.usb_claim_interface(usbDevHandle, interf) < 0) {
            return false;
        }

        usb.usb_set_altinterface(usbDevHandle, interf);
        usb.usb_set_configuration(usbDevHandle, 1);

        return true;
    }

}
