package rpg;

// Interface pequena (ISP): só quem pode ser curado implementa. Inimigos não.
public interface Curavel {
    int curar(int quantidade);
}
