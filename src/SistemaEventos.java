import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaEventos {
    private List<Evento> eventos = new ArrayList<>();

    public void exibirMenu(Scanner leitor) {
        boolean sair = false;

        while (!sair) {
            System.out.println("\n--- Sistema de Eventos ---");
            System.out.println("1. Cadastrar Evento");
            System.out.println("2. Fazer Cadastro em um Evento");
            System.out.println("3. Gerar Relatório");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarEvento(leitor);
                    break;
                case 2:
                    fazerCadastro(leitor);
                    break;
                case 3:
                    gerarRelatorio(leitor);
                    break;
                case 4:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarEvento(Scanner leitor) {

        System.out.print("Digite o nome do local: ");
        String nomeLocal = leitor.nextLine();

        System.out.print("Digite o endereço do local: ");
        String endereco = leitor.nextLine();

        System.out.print("Digite a capacidade do local: ");
        int capacidade = leitor.nextInt();
        leitor.nextLine();


        Local local = new Local(nomeLocal, endereco, capacidade);


        System.out.print("Digite o nome do evento: ");
        String nomeEvento = leitor.nextLine();

        System.out.print("Digite o tipo do evento (Seminário, Workshop, Conferência): ");
        String tipoEvento = leitor.nextLine();

        System.out.print("Digite a data do evento (yyyy-MM-dd): ");
        String data = leitor.nextLine();


        Evento evento;
        switch (tipoEvento.toLowerCase()) {
            case "seminário":
                evento = new Seminario(nomeEvento, local, data);
                break;
            case "workshop":
                evento = new Workshop(nomeEvento, local, data);
                break;
            case "conferência":
                evento = new Conferencia(nomeEvento, local, data);
                break;
            default:
                System.out.println("Tipo de evento inválido!");
                return;
        }


        eventos.add(evento);
        System.out.println("Evento cadastrado com sucesso!");
    }

    private void fazerCadastro(Scanner leitor) {
        System.out.print("Digite o nome do evento: ");
        String nomeEvento = leitor.nextLine();

        for (Evento evento : eventos) {
            if (evento.getNomeEvento().equalsIgnoreCase(nomeEvento)) {
                System.out.print("Digite o nome do participante: ");
                String nome = leitor.nextLine();

                System.out.print("Digite o CPF do participante: ");
                String cpf = leitor.nextLine();

                System.out.print("Digite o email do participante: ");
                String email = leitor.nextLine();

                evento.cadastrarParticipante(nome, cpf, email);
                return;
            }
        }
        System.out.println("Evento não encontrado!");
    }

    private void gerarRelatorio(Scanner leitor) {
        System.out.print("Digite o nome do evento para gerar o relatório: ");
        String nomeEvento = leitor.nextLine();

        for (Evento evento : eventos) {
            if (evento.getNomeEvento().equalsIgnoreCase(nomeEvento)) {
                evento.gerarRelatorio();
                return;
            }
        }
        System.out.println("Evento não encontrado!");
    }
}
