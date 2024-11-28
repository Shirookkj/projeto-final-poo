import java.util.ArrayList;
import java.util.Scanner;

public class ClinicaMedica {

    private ArrayList<Paciente> pacientes = new ArrayList<>();
    private ArrayList<Medico> medicos = new ArrayList<>();
    private ArrayList<Consulta> consultas = new ArrayList<>();

    public void exibirMenu(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Sistema de Clínica Médica ---");
            System.out.println("1. Cadastrar Paciente");
            System.out.println("2. Cadastrar Médico");
            System.out.println("3. Marcar Consulta");
            System.out.println("4. Buscar Consultas de Paciente");
            System.out.println("5. Gerar Relatório de Consultas");
            System.out.println("6. Modificar Consulta");
            System.out.println("7. Cancelar Consulta");
            System.out.println("8. Relatórios Avançados");
            System.out.println("9. Voltar ao menu principal");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarPaciente(scanner);
                    break;
                case 2:
                    cadastrarMedico(scanner);
                    break;
                case 3:
                    marcarConsulta(scanner);
                    break;
                case 4:
                    buscarConsultasPaciente(scanner);
                    break;
                case 5:
                    gerarRelatorioConsultas();
                    break;
                case 6:
                    modificarConsulta(scanner);
                    break;
                case 7:
                    cancelarConsulta(scanner);
                    break;
                case 8:
                    exibirMenuRelatorios(scanner);
                    break;
                case 9:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 9);
    }

    private void cadastrarPaciente(Scanner scanner) {
        System.out.print("Nome do paciente: ");
        String nome = scanner.nextLine();
        System.out.print("Idade do paciente: ");
        String idade = scanner.nextLine();
        System.out.print("CPF do paciente: ");
        String cpf = scanner.nextLine();
        if (!validarCpf(cpf)) {
            System.out.println("CPF inválido. Tente novamente.");
            return;
        }

        pacientes.add(new Paciente(nome, cpf, idade));
        System.out.println("Paciente cadastrado com sucesso!");
    }

    private void cadastrarMedico(Scanner scanner) {
        System.out.print("Nome do médico: ");
        String nome = scanner.nextLine();
        System.out.print("CRM do médico: ");
        String crm = scanner.nextLine();
        System.out.print("Especialidade do médico: ");
        String especialidade = scanner.nextLine();
        System.out.print("O médico está disponível? (true/false): ");
        boolean disponibilidade = scanner.nextBoolean();
        scanner.nextLine();

        medicos.add(new Medico(nome, crm, especialidade, disponibilidade));
        System.out.println("Médico cadastrado com sucesso!");
    }

    private void marcarConsulta(Scanner scanner) {
        System.out.print("Digite o CPF do paciente: ");
        String cpfPaciente = scanner.nextLine();
        Paciente paciente = buscarPacientePorCpf(cpfPaciente);
        if (paciente == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.print("Digite a especialidade do médico: ");
        String especialidadeMedico = scanner.nextLine();
        Medico medico = buscarMedicoPorEspecialidade(especialidadeMedico);
        if (medico == null) {
            System.out.println("Nenhum médico disponível com essa especialidade.");
            return;
        }

        System.out.print("Digite a data da consulta (formato AAAA-MM-DD): ");
        String data = scanner.nextLine();
        if (!validarData(data)) {
            System.out.println("Data inválida. Tente novamente.");
            return;
        }

        Consulta consulta = new Consulta(paciente, medico, data);
        consultas.add(consulta);
        System.out.println("Consulta marcada com sucesso!");
    }

    private Paciente buscarPacientePorCpf(String cpf) {
        for (Paciente p : pacientes) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }

    private Medico buscarMedicoPorEspecialidade(String especialidade) {
        for (Medico m : medicos) {
            if (m.getEspecialidade().equalsIgnoreCase(especialidade) && m.isDisponibilidade()) {
                return m;
            }
        }
        return null;
    }

    private void buscarConsultasPaciente(Scanner scanner) {
        System.out.print("Digite o CPF do paciente: ");
        String cpfPaciente = scanner.nextLine();
        listarConsultasPacientes(cpfPaciente);
    }

    private void gerarRelatorioConsultas() {
        System.out.println("\nRelatório de consultas:\n");
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta realizada.");
        } else {
            for (Consulta consulta : consultas) {
                Paciente paciente = consulta.getPaciente();
                Medico medico = consulta.getMedico();
                System.out.println("Consulta: \nPaciente: " + paciente.getNome() +
                        " | CPF: " + paciente.getCpf() +
                        " | Médico: " + medico.getNome() +
                        " | Especialidade: " + medico.getEspecialidade() +
                        " | Data: " + consulta.getDataConsulta());
            }
        }
    }

    private void modificarConsulta(Scanner scanner) {
        System.out.print("Digite o CPF do paciente: ");
        String cpfPaciente = scanner.nextLine();
        System.out.print("Digite a data da consulta: ");
        String dataConsulta = scanner.nextLine();

        Consulta consulta = encontrarConsulta(cpfPaciente, dataConsulta);
        if (consulta == null) {
            System.out.println("Consulta não encontrada.");
            return;
        }

        System.out.print("Digite a nova data da consulta: ");
        String novaData = scanner.nextLine();
        if (!validarData(novaData)) {
            System.out.println("Data inválida. Tente novamente.");
            return;
        }

        consulta.setDataConsulta(novaData);
        System.out.println("Consulta modificada com sucesso!");
    }

    private void cancelarConsulta(Scanner scanner) {
        System.out.print("Digite o CPF do paciente: ");
        String cpfPaciente = scanner.nextLine();
        System.out.print("Digite a data da consulta: ");
        String dataConsulta = scanner.nextLine();

        Consulta consulta = encontrarConsulta(cpfPaciente, dataConsulta);
        if (consulta == null) {
            System.out.println("Consulta não encontrada.");
            return;
        }

        consulta.cancelarConsulta();
        System.out.println("Consulta cancelada com sucesso!");
    }

    private Consulta encontrarConsulta(String cpfPaciente, String dataConsulta) {
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente().getCpf().equals(cpfPaciente) && consulta.getDataConsulta().equals(dataConsulta)) {
                return consulta;
            }
        }
        return null;
    }

    private boolean validarCpf(String cpf) {
        return cpf.length() == 11;
    }

    private boolean validarData(String data) {
        return data.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    private void listarConsultasPacientes(String cpfPaciente) {
        System.out.println("Consultas do paciente com CPF: " + cpfPaciente);
        boolean encontrou = false;
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente().getCpf().equals(cpfPaciente)) {
                System.out.println(consulta);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma consulta encontrada para este paciente!");
        }
    }

    // Métodos de Relatório Avançado
    private void exibirMenuRelatorios(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Relatórios Avançados ---");
            System.out.println("1. Relatório por Médico");
            System.out.println("2. Relatório por Especialidade");
            System.out.println("3. Relatório por Data");
            System.out.println("4. Voltar ao menu principal");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do médico: ");
                    String nomeMedico = scanner.nextLine();
                    gerarRelatorioPorMedico(nomeMedico);
                    break;
                case 2:
                    System.out.print("Digite a especialidade: ");
                    String especialidade = scanner.nextLine();
                    gerarRelatorioPorEspecialidade(especialidade);
                    break;
                case 3:
                    System.out.print("Digite a data da consulta (formato AAAA-MM-DD): ");
                    String data = scanner.nextLine();
                    gerarRelatorioPorData(data);
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 4);
    }

    private void gerarRelatorioPorMedico(String nomeMedico) {
        System.out.println("\nRelatório de consultas para o médico: " + nomeMedico);
        boolean encontrou = false;
        for (Consulta consulta : consultas) {
            if (consulta.getMedico().getNome().equalsIgnoreCase(nomeMedico)) {
                System.out.println(consulta);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma consulta encontrada para este médico!");
        }
    }

    private void gerarRelatorioPorEspecialidade(String especialidade) {
        System.out.println("\nRelatório de consultas para a especialidade: " + especialidade);
        boolean encontrou = false;
        for (Consulta consulta : consultas) {
            if (consulta.getMedico().getEspecialidade().equalsIgnoreCase(especialidade)) {
                System.out.println(consulta);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma consulta encontrada para esta especialidade!");
        }
    }

    private void gerarRelatorioPorData(String dataConsulta) {
        System.out.println("\nRelatório de consultas na data: " + dataConsulta);
        boolean encontrou = false;
        for (Consulta consulta : consultas) {
            if (consulta.getDataConsulta().equals(dataConsulta)) {
                System.out.println(consulta);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma consulta encontrada para esta data!");
        }
    }
}