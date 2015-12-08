public class Domino
{
    public static final int MAX_VALUE = 12;

    private int left;
    private int right;

    public static void main(String[] args)
    {
        Domino d1 = new Domino(8, 3);
        Domino d2 = new Domino(9, 3);

        System.out.println(d1.connects(d2));
    }

    public Domino(int left, int right)
    {
        if (left > MAX_VALUE || right > MAX_VALUE)
            throw new IllegalArgumentException("value cannot be greater than "
                + MAX_VALUE);

        this.left = left;
        this.right = right;
    }

    public Domino flip()
    {
        int temp = right;
        right = left;
        left = temp;

        return this;
    }

    public boolean connects(Domino other)
    {
        boolean result = this.isDouble() && other.isDouble();

        if (this.right == other.left)
        {
            result = true;
        }
        else if (this.right == other.right)
        {
            other.flip();
            result = true;
        }
        return result;
    }

    public boolean isDouble()
    {
        return left == right;
    }

    public int getLeft()
    {
        return left;
    }

    public int getRight()
    {
        return right;
    }

    public Domino clone()
    {
        return new Domino(left, right);
    }

    public boolean equals(Domino other)
    {
        return (this.left == other.left && this.right == other.right)
            || (this.right == other.left && this.left == other.right);
    }

    public String toString()
    {
        return "[" + left + ", " + right + "]";
    }
}
