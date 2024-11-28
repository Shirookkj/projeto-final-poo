public class Workshop extends Evento {
    private static final int MAX_PARTICIPANTES = 30;

    public Workshop(String nomeEvento, Local local, String data) {
        super(nomeEvento, "Workshop", local, data);
    }

    @Override
    public boolean cadastrarParticipante(Participante participante) {
        if (getParticipantes().size() >= MAX_PARTICIPANTES) {
            System.out.println("O workshop atingiu o limite máximo de participantes.");
            return false;
        }
        return super.cadastrarParticipante(participante);
    }

    @Override
    public void gerarRelatorio() {
        super.gerarRelatorio();
        System.out.println("Limite de Participantes: até " + MAX_PARTICIPANTES);
    }
}
