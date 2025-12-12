package modelo;

public class Casilla implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4871472866075983575L;
	private boolean mina;
    private boolean descubierta;
    private boolean marcada;
    private int numMinasAdyacentes;

    public Casilla() {
        this.mina = false;
        this.descubierta = false;
        this.marcada = false;
        this.numMinasAdyacentes = 0;
    }

    // Getters y setters
    public boolean tieneMina() { return mina; }
    public void colocarMina() { this.mina = true; }
    public boolean estaDescubierta() { return descubierta; }
    public void descubrir() { this.descubierta = true; }
    public boolean estaMarcada() { return marcada; }
    public void toggleMarca() { this.marcada = !this.marcada; }
    public int getNumMinasAdyacentes() { return numMinasAdyacentes; }
    public void setNumMinasAdyacentes(int num) { this.numMinasAdyacentes = num; }

    @Override
    public String toString() {
        if (marcada) return "P";
        if (!descubierta) return ".";
        if (mina) return "X";
        if (numMinasAdyacentes == 0) return " ";
        return String.valueOf(numMinasAdyacentes);
    }
}
