import java.util.Scanner;

public class LongestTrain
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the starting domino's value: ");
        int startValue = scan.nextInt();

        System.out.print("Enter the number of dominos you have: ");
        int numDominos = scan.nextInt();

        DominoHand hand = new DominoHand(numDominos);
        
        int i;
        System.out.println("Enter your dominos:");
        Domino next;
        for (i = 0; i < numDominos; i++)
        {
            next = new Domino(scan.nextInt(), scan.nextInt());
            hand.add(next);
            System.out.println(next);
        }

        System.out.println("\nYour dominos are:\n" + hand);

        DominoTrain best = bestTrain(startValue, hand);

        System.out.println("\nBest Train:\n" + best);
    }

    public static DominoTrain bestTrain(int start, DominoHand hand)
    {
        return recursiveTrain(hand, new DominoTrain(start));
    }

    private static DominoTrain recursiveTrain(DominoHand origHand,
                                            DominoTrain origTrain)
    {
        DominoTrain bestTrain = origTrain;
        DominoTrain tryTrain;
        DominoHand hand;
        Domino next;
        DominoTrain train;
        int i;
        for (i = 0; i < origHand.remaining(); i++)
        {
            hand = origHand.clone();
            next = hand.remove(i);
            train = origTrain.clone();

            if (train.connects(next))
            {
                train.push(next);
                tryTrain = recursiveTrain(hand, train);
                if (tryTrain.length() > bestTrain.length())
                {
                    bestTrain = tryTrain;
                }
            }
        }

        return bestTrain;
    }
}
