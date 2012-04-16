package org.beblue.k8055;

import javax.swing.WindowConstants;

import org.beblue.k8055.controller.ConnectController;
import org.beblue.k8055.controller.CounterResetController;
import org.beblue.k8055.controller.DigitalOuputController;
import org.beblue.k8055.model.FakeK8055Board;
import org.beblue.k8055.model.K8055Board;
import org.beblue.k8055.model.K8055BoardImpl;
import org.beblue.k8055.view.MainFrame;

/**
 * Application entry point
 * 
 * @author Mario Boikov
 */
public class Main
{

    public static void main(String[] args)
    {
        final K8055Board board;
        if (args.length > 0 && "-fake".equals(args[0])) {
            board = new FakeK8055Board();
        } else {
            board = new K8055BoardImpl();
        }

        final MainFrame frame = new MainFrame("K8055");

        new DigitalOuputController(board, frame);
        new CounterResetController(board, frame);
        new ConnectController(board, frame);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Runtime.getRuntime().addShutdownHook(new Thread() {

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Thread#run()
             */
            @Override
            public void run()
            {
                board.disconnect();
            }

        });
    }

}
