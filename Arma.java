package rpg;

public class Arma implements Item {
    private final String nome;
    private final int bonus;

    public Arma(String nome, int bonus) {
        this.nome = nome;
        this.bonus = bonus;
    }

    @Override
    public String getNome() {
        return nome + " (+" + bonus + " ataque)";
    }

    @Override
    public String usar(Heroi heroi) {
        heroi.aumentarAtaque(bonus);
        return heroi.getNome() + " equipa " + nome + ". Ataque agora: " + heroi.getAtaque() + ".";
    }
}
