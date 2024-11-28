import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        ClinicaMedica clinicaMedica = new ClinicaMedica();
        SistemaEventos sistemaEventos = new SistemaEventos();
        SistemaRestaurante sistemaRestaurante = new SistemaRestaurante();


        while (true) {
            System.out.println("\n=== SISTEMA DE GERENCIAMENTO ===");
            System.out.println("1. Sistema de Clínica Médica");
            System.out.println("2. Sistema de Eventos");
            System.out.println("3. Sistema de Restaurante");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (escolha) {
                case 1:
                    clinicaMedica.exibirMenu(scanner);
                    break;
                case 2:
                    sistemaEventos.exibirMenu(scanner);
                    break;
                case 3:
                    sistemaRestaurante.exibirMenu(scanner);
                    break;
                case 4:
                    System.out.println("Encerrando o sistema. Até mais!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}
