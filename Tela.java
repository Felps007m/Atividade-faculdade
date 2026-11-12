package rpg;

// Abstração de entrada e saída (DIP): a Batalha e o Jogo não conhecem o console.
public interface Tela {
    void mostrar(String texto);
    int perguntar(String texto, int min, int max);
    String lerTexto(String texto);
}
