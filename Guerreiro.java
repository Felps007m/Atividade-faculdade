package rpg;

public class Guerreiro extends Heroi {
    public Guerreiro(String nome) {
        super(nome, 130, 14, 6);
    }

    @Override public String getClasse() { return "Guerreiro"; }
    @Override public String getNomeHabilidade() { return "Golpe Poderoso"; }

    @Override
    protected String habilidadeEspecial(Personagem alvo) {
        int dano = alvo.receberDano(getAtaque() * 2);
        return getNome() + " usa Golpe Poderoso em " + alvo.getNome() + " e causa " + dano + " de dano!";
    }
}
