import java.util.ArrayList;

public class Mesa {
    private int numero;
    private boolean disponivel;
    private boolean reservada;
    private ArrayList<Pedido> pedidos;

    public Mesa(int numero) {
        this.numero = numero;
        this.disponivel = true;
        this.reservada = false;
        this.pedidos = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public boolean isDisponivel() {
        return disponivel && !reservada;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setReservada(boolean reservada) {
        this.reservada = reservada;
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void ocuparMesa() {
        this.disponivel = false;
    }

    public void liberarMesa() {
        this.disponivel = true;
    }

    @Override
    public String toString() {
        return "Mesa " + numero + " - " + (disponivel ? "Disponível" : "Ocupada");
    }
}
