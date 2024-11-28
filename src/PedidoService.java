import java.util.ArrayList;
import java.util.Scanner;

public class PedidoService {
    private ArrayList<Pedido> pedidos;
    private ArrayList<Mesa> mesas;

    public PedidoService(ArrayList<Mesa> mesas) {
        this.pedidos = new ArrayList<>();
        this.mesas = mesas;
    }

    public void realizarPedido(Scanner leitor) {
        System.out.println("Digite o número da mesa para o pedido:");
        int numeroMesa = leitor.nextInt();
        leitor.nextLine();

        Mesa mesa = encontrarMesaPorNumero(numeroMesa);
        if (mesa == null) {
            System.out.println("Mesa não encontrada.");
            return;
        }

        if (!mesa.isDisponivel()) {
            System.out.println("Mesa ocupada. Não é possível realizar o pedido.");
            return;
        }

        mesa.setDisponivel(false);
        Pedido pedido = new Pedido(mesa);


        System.out.println("Digite o tipo do item (1- Prato, 2- Bebida, 3- Sobremesa):");
        int tipoItem = leitor.nextInt();
        leitor.nextLine();

        System.out.println("Digite o nome do item:");
        String nomeItem = leitor.nextLine();

        System.out.println("Digite a quantidade:");
        int quantidade = leitor.nextInt();
        leitor.nextLine();

        System.out.println("Digite o preço do item:");
        double preco = leitor.nextDouble();
        leitor.nextLine();

        ItemDoPedido item = null;
        switch (tipoItem) {
            case 1:
                item = new Prato(nomeItem, quantidade, preco);
                break;
            case 2:
                item = new Bebida(nomeItem, quantidade, preco);
                break;
            case 3:
                item = new Sobremesa(nomeItem, quantidade, preco);
                break;
            default:
                System.out.println("Tipo de item inválido.");
                return;
        }

        pedido.adicionarItem(item);
        pedidos.add(pedido);

        System.out.println("Pedido realizado com sucesso!");
    }

    public void consultarPedidos() {
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }

    public void cancelarPedido(Scanner leitor) {
        System.out.println("Digite o número do pedido para cancelar:");
        int numeroPedido = leitor.nextInt();
        leitor.nextLine();

        Pedido pedido = pedidos.stream()
                .filter(p -> p.getMesa().getNumero() == numeroPedido)
                .findFirst()
                .orElse(null);

        if (pedido != null) {
            pedidos.remove(pedido);
            pedido.getMesa().setDisponivel(true); // Libera a mesa
            System.out.println("Pedido cancelado com sucesso!");
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }

    public void gerarRelatorio() {
        double totalVendas = 0;
        int mesaMaisUsada = -1;
        int maxPedidos = 0;

        for (Pedido pedido : pedidos) {
            totalVendas += pedido.calcularTotal();
            int numeroMesa = pedido.getMesa().getNumero();
            int contadorPedidos = (int) pedidos.stream()
                    .filter(p -> p.getMesa().getNumero() == numeroMesa)
                    .count();
            if (contadorPedidos > maxPedidos) {
                maxPedidos = contadorPedidos;
                mesaMaisUsada = numeroMesa;
            }
        }

        System.out.println("Relatório Geral:");
        System.out.println("Total de vendas: R$ " + totalVendas);
        System.out.println("Mesa mais usada: Mesa " + mesaMaisUsada);
    }

    private Mesa encontrarMesaPorNumero(int numeroMesa) {
        return mesas.stream()
                .filter(m -> m.getNumero() == numeroMesa)
                .findFirst()
                .orElse(null);
    }
}
