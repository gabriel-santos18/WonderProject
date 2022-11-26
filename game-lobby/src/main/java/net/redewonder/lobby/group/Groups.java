package net.redewonder.lobby.group;

public enum Groups {

    MASTER('a', "&6[Master] "),
    GERENTE('b', "&3[Gerente] "),
    ADMIN('c', "&c[Admin] "),
    MODERADOR('d', "&2[Moderador] "),
    AJUDANTE('e', "&e[Ajudante] "),
    WATER('f', "&5[Water] "),
    RAIN('g', "&2[Rain] "),
    CLOUD('h', "&b[Cloud] "),
    MEMBRO ('i', "&7");

    private char orderSymbol;
    private String display;

    Groups(char orderSymbol, String display) {
        this.orderSymbol = orderSymbol;
        this.display = display;
    }

    public char getOrderSymbol() {
        return orderSymbol;
    }

    public String getDisplay() {
        return display;
    }
}
