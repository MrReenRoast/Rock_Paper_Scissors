import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class RockPaperScissorsFrame extends JFrame {
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    private JTextField playerWinsField, computerWinsField, tiesField;
    private JTextArea gameResultsArea;
    private JScrollPane scrollPane;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLayout(new BorderLayout());

        add(createButtonPanel(), BorderLayout.NORTH);
        add(createStatsPanel(), BorderLayout.CENTER);
        add(createResultsPanel(), BorderLayout.SOUTH);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose your move"));

        JButton rockButton = new JButton(new ImageIcon("src/rock.jpg"));
        JButton paperButton = new JButton(new ImageIcon("src/paper.jpg"));
        JButton scissorsButton = new JButton(new ImageIcon("src/scissors.jpg"));
        JButton quitButton = new JButton("Quit");

        rockButton.addActionListener(e -> playRound("Rock"));
        paperButton.addActionListener(e -> playRound("Paper"));
        scissorsButton.addActionListener(e -> playRound("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);

        return buttonPanel;
    }

    private JPanel createStatsPanel() {
        JPanel statsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Game Stats"));

        JLabel playerWinsLabel = new JLabel("Player Wins:");
        playerWinsField = new JTextField("0");
        playerWinsField.setEditable(false);

        JLabel computerWinsLabel = new JLabel("Computer Wins:");
        computerWinsField = new JTextField("0");
        computerWinsField.setEditable(false);

        JLabel tiesLabel = new JLabel("Ties:");
        tiesField = new JTextField("0");
        tiesField.setEditable(false);

        statsPanel.add(playerWinsLabel);
        statsPanel.add(playerWinsField);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(computerWinsField);
        statsPanel.add(tiesLabel);
        statsPanel.add(tiesField);

        return statsPanel;
    }

    private JPanel createResultsPanel() {
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Game Results"));

        gameResultsArea = new JTextArea(10, 50);
        gameResultsArea.setEditable(false);

        scrollPane = new JScrollPane(gameResultsArea);
        resultsPanel.add(scrollPane, BorderLayout.CENTER);

        return resultsPanel;
    }

    private void playRound(String playerMove) {
        playerHistory.add(playerMove);

        Strategy strategy = strategies[random.nextInt(strategies.length)];
        String computerMove = strategy.determineMove(playerHistory.toArray(new String[0]));

        String result;

        if (playerMove.equals(computerMove)) {
            result = "It's a tie! (" + strategy.getClass().getSimpleName() + ")";
            ties++;
            tiesField.setText(String.valueOf(ties));
        } else if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            result = playerMove + " beats " + computerMove + ". Player wins! (" + strategy.getClass().getSimpleName() + ")";
            playerWins++;
            playerWinsField.setText(String.valueOf(playerWins));
        } else {
            result = computerMove + " beats " + playerMove + ". Computer wins! (" + strategy.getClass().getSimpleName() + ")";
            computerWins++;
            computerWinsField.setText(String.valueOf(computerWins));
        }

        gameResultsArea.append(result + "\n");
    }
    private List<String> playerHistory = new ArrayList<>();
    private Strategy[] strategies = {
            new RandomStrategy(),
            new LeastUsedStrategy(),
            new MostUsedStrategy(),
            new LastUsedStrategy(),
            new CheatStrategy()
    };

    private Random random = new Random();
}