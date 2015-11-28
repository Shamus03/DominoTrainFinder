public class DominoHand
{
    private Domino[] hand;
    private int remaining;

    public DominoHand(int size)
    {
        if (size < 1)
            throw new IllegalArgumentException("size must be at least 1");

        hand = new Domino[size];
        remaining = 0;
    }

    public void add(Domino toadd)
    {
        if (remaining == hand.length)
            throw new FilledCollectionException();

        hand[remaining] = toadd;
        remaining++;
    }

    public Domino get(int index)
    {
        if (index < 0 || index >= remaining)
            throw new ArrayIndexOutOfBoundsException("invalid card index");

        return hand[index];
    }

    public int remaining()
    {
        return remaining;
    }

    public Domino remove(int index)
    {
        if (index < 0 || index >= remaining)
            throw new ArrayIndexOutOfBoundsException("invalid card index");

        Domino result = hand[index];
        remaining--;
        if (remaining > 0)
        {
            hand[index] = hand[remaining];
            hand[remaining] = null;
        }
        else
        {
            hand[index] = null;
        }

        return result;
    }

    public DominoHand clone()
    {
        DominoHand copy = new DominoHand(hand.length);
        for (int i = 0; i < remaining; i++)
            copy.add(hand[i].clone());

        return copy;
    }

    public String toString()
    {
        String result = "Hand Size: " + hand.length;

        for (int i = 0; i < remaining; i++)
            result += "\n  " + hand[i];

        return result;
    }
}
