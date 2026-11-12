package rpg;

// Classe base de heróis e inimigos. Os atributos são privados (encapsulamento).
public abstract class Personagem {
    private final String nome;
    private final int vidaMaxima;
    private int vida;
    private int ataque;
    private int defesa;

    public Personagem(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vidaMaxima = vida;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }

    public boolean estaVivo() {
        return vida > 0;
    }

    // Retorna o dano que realmente foi causado (o mínimo é 1).
    public int receberDano(int dano) {
        int danoReal = Math.max(1, dano - defesa);
        vida = Math.max(0, vida - danoReal);
        return danoReal;
    }

    protected int recuperarVida(int quantidade) {
        int antes = vida;
        vida = Math.min(vidaMaxima, vida + quantidade);
        return vida - antes;
    }

    public void aumentarAtaque(int valor) { ataque += valor; }
    public void aumentarDefesa(int valor) { defesa += valor; }

    // Ataque básico. As subclasses podem mudar (polimorfismo).
    public String atacar(Personagem alvo) {
        int dano = alvo.receberDano(ataque);
        return nome + " ataca " + alvo.getNome() + " e causa " + dano + " de dano.";
    }

    public String status() {
        return nome + " [Vida " + vida + "/" + vidaMaxima + " | Ataque " + ataque + " | Defesa " + defesa + "]";
    }
}
