package rpg;

import java.util.ArrayList;
import java.util.List;

// Controla a campanha: escolher herói, lutar os 3 encontros e dar recompensas.
public class Jogo {
    private final Tela tela;

    public Jogo(Tela tela) {
        this.tela = tela;
    }

    public void iniciar() {
        tela.mostrar("=====================================");
        tela.mostrar("      RPG DE CONSOLE - A CRIPTA      ");
        tela.mostrar("=====================================");

        Heroi heroi = escolherHeroi();
        for (int i = 0; i < 3; i++) {
            heroi.adicionarItem(new Pocao(40));
        }

        int totalEncontros = 3;
        for (int numero = 1; numero <= totalEncontros; numero++) {
            tela.mostrar("\n>>> Encontro " + numero + " de " + totalEncontros);
            Batalha batalha = new Batalha(heroi, criarInimigos(numero), tela);

            if (!batalha.lutar()) {
                tela.mostrar("\n" + heroi.getNome() + " caiu em combate. FIM DE JOGO.");
                return;
            }
            if (numero < totalEncontros) {
                tela.mostrar("\nVitória! Você descansa e recupera " + heroi.curar(25) + " de vida.");
                Item premio = criarPremio(numero);
                heroi.adicionarItem(premio);
                tela.mostrar("Você encontrou: " + premio.getNome());
            }
        }
        tela.mostrar("\nPARABÉNS, " + heroi.getNome() + "! Você derrotou o chefe e venceu o jogo!");
    }

    private Heroi escolherHeroi() {
        String nome = tela.lerTexto("Qual é o nome do seu herói? ");
        tela.mostrar("\nEscolha sua classe:");
        tela.mostrar("1) Guerreiro (vida alta, resistente)");
        tela.mostrar("2) Mago (frágil, muito dano)");
        tela.mostrar("3) Arqueiro (equilibrado, chance de crítico)");
        int opcao = tela.perguntar("Classe: ", 1, 3);

        Heroi heroi;
        if (opcao == 1) {
            heroi = new Guerreiro(nome);
        } else if (opcao == 2) {
            heroi = new Mago(nome);
        } else {
            heroi = new Arqueiro(nome);
        }
        tela.mostrar("\nVocê é " + heroi.status() + " (" + heroi.getClasse() + ")");
        return heroi;
    }

    private List<Inimigo> criarInimigos(int encontro) {
        List<Inimigo> lista = new ArrayList<>();
        if (encontro == 1) {
            lista.add(new Inimigo("Goblin A", 25, 10, 1));
            lista.add(new Inimigo("Goblin B", 25, 10, 1));
        } else if (encontro == 2) {
            lista.add(new Inimigo("Orc Brutamontes", 55, 15, 3));
        } else {
            lista.add(new Chefe("Dragão Ancião", 100, 19, 4));
        }
        return lista;
    }

    private Item criarPremio(int encontro) {
        if (encontro == 1) {
            return new Arma("Espada Afiada", 3);
        }
        return new Armadura("Armadura de Ferro", 3);
    }
}
