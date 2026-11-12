package rpg;

public class Mago extends Heroi {
    public Mago(String nome) {
        super(nome, 85, 16, 2);
    }

    @Override public String getClasse() { return "Mago"; }
    @Override public String getNomeHabilidade() { return "Bola de Fogo"; }

    @Override
    protected String habilidadeEspecial(Personagem alvo) {
        int dano = alvo.receberDano(getAtaque() * 2 + 6);
        return getNome() + " lança Bola de Fogo em " + alvo.getNome() + " e causa " + dano + " de dano!";
    }
}
