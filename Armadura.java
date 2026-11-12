package rpg;

public class Armadura implements Item {
    private final String nome;
    private final int bonus;

    public Armadura(String nome, int bonus) {
        this.nome = nome;
        this.bonus = bonus;
    }

    @Override
    public String getNome() {
        return nome + " (+" + bonus + " defesa)";
    }

    @Override
    public String usar(Heroi heroi) {
        heroi.aumentarDefesa(bonus);
        return heroi.getNome() + " veste " + nome + ". Defesa agora: " + heroi.getDefesa() + ".";
    }
}
