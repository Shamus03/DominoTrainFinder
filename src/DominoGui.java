import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DominoGui extends JFrame
{
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 275;
    private static final int DEFAULT_HAND_SIZE = 6;

    private HandSelectorPanel handSelector;
    
    public static void main(String[] args)
    {
        DominoGui window = new DominoGui();
        window.setVisible(true);
    }

    public DominoGui()
    {
        super("Domino Train Finder");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        JPanel selector = new JPanel();
        selector.setLayout(new FlowLayout());

        handSelector = new HandSelectorPanel(DEFAULT_HAND_SIZE);
        selector.add(handSelector);

        JPanel buttonPanel = new JPanel();

        JButton randomize = new JButton("Randomize");
        buttonPanel.add(randomize);
        randomize.addActionListener(new RandomizeButtonListener());

        JButton calculate = new JButton("Calculate");
        buttonPanel.add(calculate);
        calculate.addActionListener(new CalculateButtonListener());

        add(selector);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class CalculateButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            DominoTrain train = handSelector.getBestTrain();

            if (train.length() > 0)
            {
                TrainFrame frame = new TrainFrame(train);
                frame.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(DominoGui.this,
                    "No train can be made.", "Best Train",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class RandomizeButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            handSelector.randomize();
        }
    }
}
