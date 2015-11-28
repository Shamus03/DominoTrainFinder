import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DominoSelectorPanel extends JPanel
{
    private JComboBox left;
    private JComboBox right;

    public DominoSelectorPanel()
    {
        super();

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(90, 148));
        setLayout(new GridLayout(2, 1));

        left = new JComboBox(DominoPanel.getImages());
        right = new JComboBox(DominoPanel.getImages());

        add(left);
        add(right);
    }

    public Domino getDomino()
    {
        return new Domino(left.getSelectedIndex(), right.getSelectedIndex());
    }

    public void setDomino(Domino domino)
    {
        left.setSelectedIndex(domino.getLeft());
        right.setSelectedIndex(domino.getRight());
    }
}
