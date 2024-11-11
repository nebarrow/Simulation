package Sprite;

public enum EntitySprite {
    EARTH("\uD83D\uDFEB"),
    PLANT("\uD83E\uDD55"),
    HERBIVORE("\uD83D\uDC30"),
    PREDATOR("\uD83E\uDD8A"),
    TREE("\uD83C\uDF33"),
    ROCK("\uD83E\uDEA8"),
    BACKGROUND("\033[48;5;114m"),
    RESET("\033[0m");

    private final String symbol;

    EntitySprite(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
