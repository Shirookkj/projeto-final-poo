public class Paciente extends Pessoa {
    private String idade;

    public Paciente(String nome, String cpf, String idade) {
        super(nome, cpf);
        this.idade = idade;
    }
    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }


    @Override
    public String toString() {
        return super.toString() + ", Idade: " + idade;
    }
}
