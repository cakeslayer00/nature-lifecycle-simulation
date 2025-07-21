package dev.vladsv.util;

public enum Sprites {
    TREE("\uD83C\uDF32"),
    HERBIVORE("\uD83D\uDC37"),
    CARNIVORE("\uD83D\uDC3A"),
    ROCK("⛰️"),
    GRASS("\uD83C\uDF31"),
    EMPTY("\uD83C\uDFDE");

    private final String symbol;

    Sprites(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}