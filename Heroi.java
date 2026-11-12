package rpg;

import java.util.ArrayList;
import java.util.List;

// Herói jogável. Tem uma lista de itens (composição) e uma habilidade especial
// que cada classe de herói implementa do seu jeito.
public abstract class Heroi extends Personagem implements Curavel {
    private final List<Item> itens = new ArrayList<>();
    private int usosHabilidade = 3;

    public Heroi(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
    }

    public abstract String getClasse();
    public abstract String getNomeHabilidade();
    protected abstract String habilidadeEspecial(Personagem alvo);

    public boolean podeUsarHabilidade() {
        return usosHabilidade > 0;
    }

    public int getUsosHabilidade() {
        return usosHabilidade;
    }

    public String usarHabilidade(Personagem alvo) {
        usosHabilidade--;
        return habilidadeEspecial(alvo);
    }

    @Override
    public int curar(int quantidade) {
        return recuperarVida(quantidade);
    }

    public List<Item> getItens() { return itens; }
    public void adicionarItem(Item item) { itens.add(item); }
    public void removerItem(Item item) { itens.remove(item); }
}
