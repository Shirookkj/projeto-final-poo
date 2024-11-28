public class Medico extends Pessoa {
    private String especialidade;

    private boolean disponibilidade;


    public Medico(String nome, String cpf, String especialidade, boolean disponibilidade) {
        super(nome, cpf);
        this.especialidade = especialidade;
        this.disponibilidade = disponibilidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    @Override
    public String toString() {
        return super.toString() + ", Especialidade: " + especialidade + ", Disponível: " + disponibilidade;
    }
}
