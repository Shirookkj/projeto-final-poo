import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String nomeEvento;
    private Local local;
    private List<Participante> participantes;
    private String data;
    private String tipoEvento;

    public Evento(String nomeEvento, String tipoEvento, Local local, String data) {
        this.nomeEvento = nomeEvento;
        this.tipoEvento = tipoEvento;
        this.local = local;
        this.participantes = new ArrayList<>();
        this.data = data;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public Local getLocal() {
        return local;
    }

    public String getData() {
        return data;
    }

    public List<Participante> getParticipantes() {
        return new ArrayList<>(participantes);
    }


    public boolean cadastrarParticipante(Participante participante) {
        if (isParticipanteCadastrado(participante)) {
            System.out.println(participante.getNome() + " já está cadastrado(a) no evento " + nomeEvento);
            return false;
        }

        if (participantes.size() < local.getCapacidade()) {
            participantes.add(participante);
            System.out.println(participante.getNome() + " foi cadastrado(a) no evento " + nomeEvento);
            return true;
        } else {
            System.out.println("Não foi possível concluir o cadastro. Capacidade máxima atingida para o evento " + nomeEvento);
            return false;
        }
    }

    public boolean cadastrarParticipante(String nome, String cpf, String email) {
        Participante participante = new Participante(nome, cpf, email);
        return cadastrarParticipante(participante);
    }


    private boolean isParticipanteCadastrado(Participante participante) {
        for (Participante p : participantes) {
            if (p.getEmail().equals(participante.getEmail())) {
                return true;
            }
        }
        return false;
    }

    // Método para gerar relatório básico
    public void gerarRelatorio() {
        System.out.println("Relatório de Participação do Evento " + nomeEvento + " (" + tipoEvento + "):");
        local.exibirLocal(); // Exibe o local do evento
        System.out.println("Data: " + data);
        System.out.println("Total de Participantes: " + participantes.size() + " / " + local.getCapacidade());

        if (participantes.isEmpty()) {
            System.out.println("Nenhum participante inscrito.");
        } else {
            System.out.println("Lista de Participantes:");
            for (Participante participante : participantes) {
                System.out.println(" - " + participante.getNome() + " (" + participante.getEmail() + ")");
            }
        }
    }


    public void gerarRelatorioDetalhado() {
        gerarRelatorio();
    }


    public boolean cancelarInscricao(String email) {
        for (Participante participante : participantes) {
            if (participante.getEmail().equals(email)) {
                participantes.remove(participante);
                System.out.println("Inscrição cancelada para o participante com email: " + email);
                return true;
            }
        }
        System.out.println("Email não encontrado.");
        return false;
    }

    @Override
    public String toString() {
        return "Evento: " + nomeEvento + " (" + tipoEvento + "), Data: " + data + ", Local: " + local.getNomeLocal() + ", Capacidade: " + local.getCapacidade();
    }
}
