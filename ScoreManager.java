import java.io.*;
import java.util.Scanner;

public class ScoreManager {
    private static final String FILE_NAME = "highscore.txt";

    public int loadHighScore() {
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            return 0;
        }
        return 0;
    }

    public void saveHighScore(int score) {
        int currentHigh = loadHighScore();
        if (score > currentHigh) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
                writer.println(score);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}