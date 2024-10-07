import java.util.Random;

public class RandomStrategy implements Strategy {
    @Override
    public String determineMove(String[] playerHistory) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        return moves[new Random().nextInt(moves.length)];
    }
}