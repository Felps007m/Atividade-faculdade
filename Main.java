package rpg;

public class Main {
    public static void main(String[] args) {
        Tela tela = new Console();   // aqui escolhemos a implementação concreta
        new Jogo(tela).iniciar();
    }
}
