import javax.swing.*;
import java.awt.*;

public class DominoPanel extends JPanel
{
    private static boolean inited = false;
    private static ImageIcon[] images;

    private Domino domino;

    private static void init()
    {
        if (inited)
            return;

        Domino dummy = new Domino(-1, -1); //Dummy object needed for resources

        images = new ImageIcon[Domino.MAX_VALUE + 1];
        for (int i = 0; i < images.length; i++)
            images[i] = new ImageIcon(dummy.getClass().getResource(
                "/image/" + i + ".png"), "" + i);

        inited = true;
    }

    public DominoPanel(Domino domino)
    {
        super();
        this.domino = domino;

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(74, 148));
        setLayout(new GridLayout(2, 1));

        JLabel left = new JLabel(images[domino.getLeft()]);
        JLabel right = new JLabel(images[domino.getRight()]);

        add(left);
        add(right);
    }

    public static ImageIcon getImage(int i)
    {
        if (i > Domino.MAX_VALUE || i < 0)
            throw new IllegalArgumentException("no image for face value " + i);
        
        init();
        return images[i];
    }

    public static ImageIcon[] getImages()
    {
        init();
        return images;
    }
}
