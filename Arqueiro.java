package rpg;

import java.util.Random;

public class Arqueiro extends Heroi {
    private final Random sorteio = new Random();

    public Arqueiro(String nome) {
        super(nome, 100, 15, 4);
    }

    @Override public String getClasse() { return "Arqueiro"; }
    @Override public String getNomeHabilidade() { return "Tiro Duplo"; }

    @Override
    protected String habilidadeEspecial(Personagem alvo) {
        int primeiro = alvo.receberDano(getAtaque() + 4);
        int segundo = alvo.receberDano(getAtaque() + 4);
        return getNome() + " dispara Tiro Duplo em " + alvo.getNome()
                + " causando " + primeiro + " + " + segundo + " de dano!";
    }

    // Polimorfismo: o arqueiro tem 25% de chance de acerto crítico.
    @Override
    public String atacar(Personagem alvo) {
        boolean critico = sorteio.nextInt(100) < 25;
        int dano = alvo.receberDano(critico ? getAtaque() * 2 : getAtaque());
        return getNome() + " atira em " + alvo.getNome() + " e causa " + dano
                + " de dano" + (critico ? " (CRÍTICO!)." : ".");
    }
}
