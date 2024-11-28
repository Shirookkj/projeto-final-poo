import java.util.ArrayList;

public class Pedido {
    private Mesa mesa;
    private ArrayList<ItemDoPedido> itens;
    private boolean finalizado;
    private Pessoa pessoa;

    public Pedido(Mesa mesa) {
        this.mesa = mesa;
        this.itens = new ArrayList<>();
        this.finalizado = false;
    }

    public void adicionarItem(ItemDoPedido item) {
        if (!finalizado) {
            itens.add(item);
        } else {
            System.out.println("O pedido já foi finalizado e não pode ser alterado.");
        }
    }

    public void finalizarPedido() {
        this.finalizado = true;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemDoPedido item : itens) {
            total += item.calcularTotal();
        }


        if (pessoa != null) {
            total -= total * 0.10;
        }

        return total;
    }

    public void associarPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido da Mesa ").append(mesa.getNumero()).append(":\n");

        for (ItemDoPedido item : itens) {
            sb.append(item).append(" - Tipo: ").append(item.getTipoItem()).append("\n");
        }

        sb.append("Total: R$ ").append(calcularTotal()).append("\n");

        if (pessoa != null) {
            sb.append("Desconto aplicado para: ").append(pessoa.getNome()).append("\n");
        }

        sb.append(finalizado ? "Pedido Finalizado" : "Pedido em andamento").append("\n");
        return sb.toString();
    }
}
