import java.util.ArrayList;
import java.util.Scanner;

public class SistemaRestaurante {
    private ArrayList<Mesa> mesas;
    private PedidoService pedidoService;

    public SistemaRestaurante() {
        this.mesas = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            mesas.add(new Mesa(i));
        }
        this.pedidoService = new PedidoService(mesas);
    }

    public void exibirMenu(Scanner leitor) {
        boolean sair = false;

        while (!sair) {
            System.out.println("\n--- Sistema de Restaurante ---");
            System.out.println("1. Realizar Pedido");
            System.out.println("2. Consultar Pedidos");
            System.out.println("3. Cancelar Pedido");
            System.out.println("4. Relatório Geral");
            System.out.println("5. Voltar ao menu principal");

            int opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    pedidoService.realizarPedido(leitor);
                    break;
                case 2:
                    pedidoService.consultarPedidos();
                    break;
                case 3:
                    pedidoService.cancelarPedido(leitor);
                    break;
                case 4:
                    pedidoService.gerarRelatorio();
                    break;
                case 5:
                    sair = true;
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public Mesa getMesaByNumero(int numero) {
        return mesas.stream()
                .filter(m -> m.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Mesa> getMesasDisponiveis() {
        ArrayList<Mesa> mesasDisponiveis = new ArrayList<>();
        for (Mesa mesa : mesas) {
            if (mesa.isDisponivel()) {
                mesasDisponiveis.add(mesa);
            }
        }
        return mesasDisponiveis;
    }

    public void reservarMesa(int numeroMesa) {
        Mesa mesa = getMesaByNumero(numeroMesa);
        if (mesa != null) {
            mesa.setReservada(true);
        }
    }

    public void liberarMesa(int numeroMesa) {
        Mesa mesa = getMesaByNumero(numeroMesa);
        if (mesa != null) {
            mesa.setDisponivel(true);
            mesa.setReservada(false);
        }
    }
}
