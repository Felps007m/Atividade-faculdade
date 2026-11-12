package rpg;

// Qualquer item do jogo. Para criar um item novo basta implementar esta interface (OCP).
public interface Item {
    String getNome();
    String usar(Heroi heroi);
}
