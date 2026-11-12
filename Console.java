package rpg;

import java.util.Scanner;

// Implementação da Tela usando o terminal.
public class Console implements Tela {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void mostrar(String texto) {
        System.out.println(texto);
    }

    @Override
    public int perguntar(String texto, int min, int max) {
        while (true) {
            System.out.print(texto);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // cai na mensagem abaixo
            }
            System.out.println("Opção inválida. Digite um número de " + min + " a " + max + ".");
        }
    }

    @Override
    public String lerTexto(String texto) {
        System.out.print(texto);
        String linha = scanner.nextLine().trim();
        return linha.isEmpty() ? "Herói" : linha;
    }
}
