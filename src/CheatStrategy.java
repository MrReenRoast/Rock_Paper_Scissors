import java.util.Random;

public class CheatStrategy implements Strategy {
    @Override
    public String determineMove(String[] playerHistory) {
        if (new Random().nextInt(10) == 0) { // 10% chance
            String lastMove = playerHistory[playerHistory.length - 1];
            if (lastMove != null) {
                switch (lastMove) {
                    case "Rock":
                        return "Paper";
                    case "Paper":
                        return "Scissors";
                    case "Scissors":
                        return "Rock";
                }
            }
        }
        return new RandomStrategy().determineMove(playerHistory);
    }
}
