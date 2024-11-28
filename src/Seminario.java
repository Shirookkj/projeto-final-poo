public class Seminario extends Evento {
    private static final int MIN_PARTICIPANTES = 10;
    private static final int MAX_PARTICIPANTES = 50;

    public Seminario(String nomeEvento, Local local, String data) {
        super(nomeEvento, "Seminário", local, data);
    }

    @Override
    public boolean cadastrarParticipante(Participante participante) {
        if (getParticipantes().size() >= MAX_PARTICIPANTES) {
            System.out.println("O seminário atingiu o limite máximo de participantes.");
            return false;
        }
        return super.cadastrarParticipante(participante);
    }

    @Override
    public void gerarRelatorio() {
        super.gerarRelatorio();
        System.out.println("Limite de Participantes: " + MIN_PARTICIPANTES + " a " + MAX_PARTICIPANTES);
    }
}
