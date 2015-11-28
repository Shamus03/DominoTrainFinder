public class DominoTrain
{
    private static final int DEFAULT_SIZE = 10;

    private Domino start;
    private int length;
    private Domino[] train;

    public static void main(String[] args)
    {
        DominoDeck deck = new DominoDeck(6);
        DominoTrain train = new DominoTrain(5);

        while (train.length() < 8 && !deck.isEmpty())
            if (train.connects(deck.peek()))
                train.push(deck.pop());
            else
                deck.pop();

        System.out.println(train);
    }

    public DominoTrain(int start)
    {
        if (start < 0)
            throw new IllegalArgumentException("start must be at least 0");

        this.start = new Domino(-1, start);
        this.length = 0;
        train =  new Domino[DEFAULT_SIZE];
    }

    public DominoTrain(Domino start)
    {
        this(start.getRight());
    }

    public void push(Domino next)
    {
        if (length == train.length)
            expandCapacity();

        train[length] = next;
        length++;
    }

    private void expandCapacity()
    {
        Domino[] newTrain = new Domino[train.length * 2];
        for (int i = 0; i < length; i++)
            newTrain[i] = train[i];

        train = newTrain;
    }

    public Domino[] getDominos()
    {
        Domino[] dominos = new Domino[length];
        for (int i = 0; i < length; i++)
            dominos[i] = train[i].clone();

        return dominos;
    }

    public Domino peek()
    {
        if (isEmpty())
            throw new EmptyCollectionException();

        return train[length - 1];
    }

    public boolean isEmpty()
    {
        return length == 0;
    }

    public int length()
    {
        return length;
    }

    public boolean connects(Domino next)
    {
        if (length == 0)
        {
            return start.connects(next);
        }
        else
        {
            return train[length - 1].connects(next);
        }
    }

    public int getStart()
    {
        return start.getRight();
    }

    public DominoTrain clone()
    {
        DominoTrain copy = new DominoTrain(start.clone());
        for (int i = 0; i < length; i++)
            copy.push(train[i].clone());
        
        return copy;
    }

    public String toString()
    {
        String result = "Train Start:  " + start.getRight();
            result += "\nTrain length: " + length;

        for (int i = 0; i < length; i++)
            result += "\n  " + train[i];

        return result;
    }
}
