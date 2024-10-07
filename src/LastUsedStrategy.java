public class LastUsedStrategy implements Strategy {
    @Override
    public String determineMove(String[] playerHistory) {
        String lastMove = playerHistory[playerHistory.length - 1];
        if (lastMove == null) {
            return new RandomStrategy().determineMove(playerHistory);
        }

        return lastMove;
    }
}
