public class Sobremesa extends ItemDoPedido {
    public Sobremesa(String nome, int quantidade, double preco) {
        super(nome, quantidade, preco);
    }

    @Override
    public String getTipoItem() {
        return "Sobremesa";
    }
}
