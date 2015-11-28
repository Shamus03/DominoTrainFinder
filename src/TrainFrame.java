import javax.swing.*;
import java.awt.*;

public class TrainFrame extends JFrame
{
    private static final int HEIGHT = 200;

    public TrainFrame(DominoTrain train)
    {
        super("Best Train");
        
        Domino[] dominos = train.getDominos();

        setSize(Math.max(dominos.length * 85, 250), HEIGHT);
        setLocationRelativeTo(null);

        JPanel panel;
        setLayout(new GridLayout(1, dominos.length));
        for (int i = 0; i < dominos.length; i++)
        {
            panel = new JPanel();
            panel.add(new DominoPanel(dominos[i]));
            add(panel);
        }
    }
}
