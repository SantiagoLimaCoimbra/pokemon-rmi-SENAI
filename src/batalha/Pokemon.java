package batalha;

import java.util.Random;

public class Pokemon {
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;
    private Random random;

    public Pokemon(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.random = new Random();
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int calcularDano() {
        int variacao = random.nextInt(5) - 2;
        return ataque + variacao;
    }

    public void receberDano(int dano) {
        this.vida -= dano;
    }
}
