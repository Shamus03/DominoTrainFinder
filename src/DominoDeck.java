import java.util.Random;

public class DominoDeck
{
    private static Random generator = new Random();

    private int maxValue;
    private Domino[] deck;
    private int remaining;

    public static void main(String[] args)
    {
        DominoDeck deck = new DominoDeck(12);
        System.out.println(deck);
    }

    public DominoDeck()
    {
        this(Domino.MAX_VALUE);
    }

    public DominoDeck(int maxValue)
    {
        if (maxValue < 0)
            throw new IllegalArgumentException("maxValue must be at least 0");

        this.maxValue = maxValue;
        deck = new Domino[(maxValue + 1) * (maxValue + 2) / 2];

        remaining = 0;
        int left;
        int right;
        for (left = 0; left <= maxValue; left++)
        {
            for (right = left; right <= maxValue; right++)
            {
                deck[remaining] = new Domino(left, right);
                remaining++;
            }
        }

        shuffle();
    }

    public boolean isEmpty()
    {
        return remaining == 0;
    }

    public Domino peek()
    {
        if (isEmpty())
            throw new EmptyCollectionException();

        return deck[remaining - 1];
    }

    public Domino pop()
    {
        if (isEmpty())
            throw new EmptyCollectionException();

        remaining--;
        return deck[remaining];
    }

    public void shuffle()
    {
        remaining = deck.length;
        Domino temp;
        int swap;
        for (int i = 0; i < remaining; i++)
        {
            swap = i + generator.nextInt(remaining - i);
            temp = deck[swap];
            deck[swap] = deck[i];
            deck[i] = temp;
        }
    }

    public String toString()
    {
        String result = "Max Value: " + maxValue;

        for (int i = 0; i < remaining; i++)
        {
            result += "\n  " + deck[i];
        }

        return result;
    }
}
