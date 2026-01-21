public enum Difficulty {
    EASY(1000, 1),    // Slow (1 sec), 1 point per hit
    MEDIUM(700, 2),   // Normal (0.7 sec), 2 points per hit
    HARD(450, 3);     // Fast (0.45 sec), 3 points per hit

    private final int speedDelay;
    private final int scoreMultiplier;

    Difficulty(int speedDelay, int scoreMultiplier) {
        this.speedDelay = speedDelay;
        this.scoreMultiplier = scoreMultiplier;
    }

    public int getSpeedDelay() { return speedDelay; }
    public int getScoreMultiplier() { return scoreMultiplier; }
}