public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private String dataConsulta;
    private boolean cancelada;

    public Consulta(Paciente paciente, Medico medico, String dataConsulta) {
        this.paciente = paciente;
        this.medico = medico;
        this.dataConsulta = dataConsulta;
        this.cancelada = false;
        medico.setDisponibilidade(false);
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void cancelarConsulta() {
        this.cancelada = true;
        this.medico.setDisponibilidade(true);
    }

    @Override
    public String toString() {
        return "Consulta [Paciente: " + paciente.getNome() + ", Médico: " + medico.getNome() + ", Data: " + dataConsulta + ", Cancelada: " + cancelada + "]";
    }
}
