package rpg;

import java.util.ArrayList;
import java.util.List;

// Só cuida de uma batalha em turnos (responsabilidade única).
public class Batalha {
    private final Heroi heroi;
    private final List<Inimigo> inimigos;
    private final Tela tela;
    private final List<Inimigo> jaAnunciados = new ArrayList<>();

    public Batalha(Heroi heroi, List<Inimigo> inimigos, Tela tela) {
        this.heroi = heroi;
        this.inimigos = inimigos;
        this.tela = tela;
    }

    // Retorna true se o herói venceu.
    public boolean lutar() {
        tela.mostrar("\n=== BATALHA INICIADA ===");
        while (heroi.estaVivo() && temInimigoVivo()) {
            mostrarStatus();
            turnoDoHeroi();
            anunciarMortes();
            if (!temInimigoVivo()) {
                break;
            }
            turnoDosInimigos();
        }
        return heroi.estaVivo();
    }

    private boolean temInimigoVivo() {
        for (Inimigo inimigo : inimigos) {
            if (inimigo.estaVivo()) {
                return true;
            }
        }
        return false;
    }

    private void mostrarStatus() {
        tela.mostrar("\n--- Status ---");
        tela.mostrar("Você:    " + heroi.status());
        for (Inimigo inimigo : inimigos) {
            if (inimigo.estaVivo()) {
                tela.mostrar("Inimigo: " + inimigo.status());
            } else {
                tela.mostrar("Inimigo: " + inimigo.getNome() + " (derrotado)");
            }
        }
    }

    private void turnoDoHeroi() {
        boolean agiu = false;
        while (!agiu) {
            tela.mostrar("\n1) Atacar   2) " + heroi.getNomeHabilidade()
                    + " (" + heroi.getUsosHabilidade() + " usos)   3) Usar item");
            int opcao = tela.perguntar("Escolha sua ação: ", 1, 3);

            if (opcao == 1) {
                tela.mostrar(heroi.atacar(escolherAlvo()));
                agiu = true;
            } else if (opcao == 2) {
                if (heroi.podeUsarHabilidade()) {
                    tela.mostrar(heroi.usarHabilidade(escolherAlvo()));
                    agiu = true;
                } else {
                    tela.mostrar("Sua habilidade não tem mais usos.");
                }
            } else {
                agiu = usarItem();
            }
        }
    }

    private Inimigo escolherAlvo() {
        List<Inimigo> vivos = new ArrayList<>();
        for (Inimigo inimigo : inimigos) {
            if (inimigo.estaVivo()) {
                vivos.add(inimigo);
            }
        }
        if (vivos.size() == 1) {
            return vivos.get(0);
        }
        tela.mostrar("Escolha o alvo:");
        for (int i = 0; i < vivos.size(); i++) {
            tela.mostrar((i + 1) + ") " + vivos.get(i).status());
        }
        return vivos.get(tela.perguntar("Alvo: ", 1, vivos.size()) - 1);
    }

    // Retorna true se o herói realmente usou um item.
    private boolean usarItem() {
        List<Item> itens = heroi.getItens();
        if (itens.isEmpty()) {
            tela.mostrar("Seu inventário está vazio.");
            return false;
        }
        tela.mostrar("Inventário (0 para voltar):");
        for (int i = 0; i < itens.size(); i++) {
            tela.mostrar((i + 1) + ") " + itens.get(i).getNome());
        }
        int escolha = tela.perguntar("Item: ", 0, itens.size());
        if (escolha == 0) {
            return false;
        }
        Item item = itens.get(escolha - 1);
        tela.mostrar(item.usar(heroi));
        heroi.removerItem(item);
        return true;
    }

    private void turnoDosInimigos() {
        for (Inimigo inimigo : inimigos) {
            if (inimigo.estaVivo() && heroi.estaVivo()) {
                tela.mostrar(inimigo.atacar(heroi));
            }
        }
    }

    private void anunciarMortes() {
        for (Inimigo inimigo : inimigos) {
            if (!inimigo.estaVivo() && !jaAnunciados.contains(inimigo)) {
                jaAnunciados.add(inimigo);
                tela.mostrar("x " + inimigo.getNome() + " foi derrotado!");
            }
        }
    }
}
