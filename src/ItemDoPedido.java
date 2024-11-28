public abstract class ItemDoPedido {
    protected String nome;
    protected int quantidade;
    protected double preco;

    public ItemDoPedido(String nome, int quantidade, double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public abstract String getTipoItem();

    public double calcularTotal() {
        return preco * quantidade;
    }

    @Override
    public String toString() {
        return quantidade + "x " + nome + " - R$ " + preco;
    }
}
