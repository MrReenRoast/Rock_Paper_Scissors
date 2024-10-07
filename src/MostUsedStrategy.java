import java.util.HashMap;

public class MostUsedStrategy implements Strategy {
    @Override
    public String determineMove(String[] playerHistory) {
        HashMap<String, Integer> moveCount = new HashMap<>();
        moveCount.put("Rock", 0);
        moveCount.put("Paper", 0);
        moveCount.put("Scissors", 0);

        for (String move : playerHistory) {
            if (move != null) {
                moveCount.put(move, moveCount.get(move) + 1);
            }
        }

        String mostUsed = "Rock";
        if (moveCount.get("Paper") > moveCount.get("Rock")) {
            mostUsed = "Paper";
        }
        if (moveCount.get("Scissors") > moveCount.get(mostUsed)) {
            mostUsed = "Scissors";
        }

        switch (mostUsed) {
            case "Rock":
                return "Paper";
            case "Paper":
                return "Scissors";
            case "Scissors":
                return "Rock";
        }

        return "Rock";
    }
}
