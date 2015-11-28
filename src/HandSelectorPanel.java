import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HandSelectorPanel extends JPanel
{
    private static final int MAX_HAND_SIZE = 12;

    private JComboBox resize;
    private JComboBox startSelector;
    private DominoSelectorPanel[] dominoSelectors;
    private JPanel dominosPanel;

    public HandSelectorPanel(int handSize)
    {
        super();
        setLayout(new BorderLayout());

        dominosPanel = new JPanel();

        Object[] handSizes = new Object[MAX_HAND_SIZE];
        for (int i = 0; i < MAX_HAND_SIZE; i++)
            handSizes[i] = i + 1;
        resize = new JComboBox(handSizes);
        resize.addActionListener(new HandSizeChangedListener());
        resize.setSelectedIndex(handSize - 1);

        Object[] startValues = new Object[Domino.MAX_VALUE + 1];
        for (int i = 0; i <= Domino.MAX_VALUE; i++)
            startValues[i] = i;
        startSelector = new JComboBox(startValues);
        startSelector.setSelectedIndex(12);

        JPanel box = new JPanel();
        box.add(new JLabel("Train Start:"));
        box.add(startSelector);
        box.add(new JLabel("Hand Size:"));
        box.add(resize);
        add(box, BorderLayout.NORTH);
    }

    private void resizeHand(int handSize)
    {
        remove(dominosPanel);

        dominosPanel = new JPanel();
        dominosPanel.setLayout(new GridLayout(1, handSize));
        dominoSelectors = new DominoSelectorPanel[handSize];

        JPanel panel;
        DominoSelectorPanel select;
        for (int i = 0; i < handSize; i++)
        {
            panel = new JPanel();
            select = new DominoSelectorPanel();
            panel.add(select);
            dominosPanel.add(panel);
            dominoSelectors[i] = select;
        }

        add(dominosPanel, BorderLayout.CENTER);
        revalidate();
    }

    public void randomize()
    {
        DominoDeck deck = new DominoDeck();

        Domino domino;
        for (int i = 0; i < dominoSelectors.length; i++)
        {
            domino = deck.pop();
            dominoSelectors[i].setDomino(domino);
        }
    }

    public DominoHand getHand()
    {
        DominoHand hand = new DominoHand(dominoSelectors.length);
        for (int i = 0; i < dominoSelectors.length; i++)
        {
            hand.add(dominoSelectors[i].getDomino());
        }

        return hand;
    }

    public int getStart()
    {
        return startSelector.getSelectedIndex();
    }

    public DominoTrain getBestTrain()
    {
        return LongestTrain.bestTrain(getStart(), getHand());
    }

    private class HandSizeChangedListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            resizeHand(resize.getSelectedIndex() + 1);
        }
    }
}
