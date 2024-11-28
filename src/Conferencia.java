public class Conferencia extends Evento {
    private static final int MIN_PARTICIPANTES = 50;
    private static final int MAX_PARTICIPANTES = 200;

    public Conferencia(String nomeEvento, Local local, String data) {
        super(nomeEvento, "Conferência", local, data);
    }

    @Override
    public boolean cadastrarParticipante(Participante participante) {
        if (getParticipantes().size() >= MAX_PARTICIPANTES) {
            System.out.println("A conferência atingiu o limite máximo de participantes.");
            return false;
        }
        if (getParticipantes().size() < MIN_PARTICIPANTES) {
            System.out.println("A conferência precisa de mais participantes para ser realizada.");
        }
        return super.cadastrarParticipante(participante);
    }

    @Override
    public void gerarRelatorio() {
        super.gerarRelatorio();
        System.out.println("Limite de Participantes: " + MIN_PARTICIPANTES + " a " + MAX_PARTICIPANTES);
    }
}
