public class Bebida extends ItemDoPedido {
    public Bebida(String nome, int quantidade, double preco) {
        super(nome, quantidade, preco);
    }

    @Override
    public String getTipoItem() {
        return "Bebida";
    }
}