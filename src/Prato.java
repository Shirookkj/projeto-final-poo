public class Prato extends ItemDoPedido {
    public Prato(String nome, int quantidade, double preco) {
        super(nome, quantidade, preco);
    }

    @Override
    public String getTipoItem() {
        return "Prato";
    }
}
