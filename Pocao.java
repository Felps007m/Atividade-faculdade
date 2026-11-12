package rpg;

public class Pocao implements Item {
    private final int cura;

    public Pocao(int cura) {
        this.cura = cura;
    }

    @Override
    public String getNome() {
        return "Poção de Cura (+" + cura + " vida)";
    }

    @Override
    public String usar(Heroi heroi) {
        int curado = heroi.curar(cura);
        return heroi.getNome() + " bebe a poção e recupera " + curado + " de vida.";
    }
}
