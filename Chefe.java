package rpg;

import java.util.Random;

// O Chefe pode ser usado em qualquer lugar onde um Inimigo é usado (Liskov).
// Ele só muda o ataque: às vezes dá um golpe duplo.
public class Chefe extends Inimigo {
    private final Random sorteio = new Random();

    public Chefe(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
    }

    @Override
    public String atacar(Personagem alvo) {
        if (sorteio.nextInt(100) < 25) {
            int dano = alvo.receberDano(getAtaque() * 2);
            return getNome() + " usa SOPRO DEVASTADOR em " + alvo.getNome() + " e causa " + dano + " de dano!";
        }
        return super.atacar(alvo);
    }
}
