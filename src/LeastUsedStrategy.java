import java.util.HashMap;

public class LeastUsedStrategy implements Strategy {
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

        String leastUsed = "Rock"; // Default
        if (moveCount.get("Paper") < moveCount.get("Rock")) {
            leastUsed = "Paper";
        }
        if (moveCount.get("Scissors") < moveCount.get(leastUsed)) {
            leastUsed = "Scissors";
        }

        switch (leastUsed) {
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
