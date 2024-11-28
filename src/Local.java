public class Local {
    private String nomeLocal;
    private String endereco;
    private int capacidade;

    public Local(String nomeLocal, String endereco, int capacidade) {
        this.nomeLocal = nomeLocal;
        this.endereco = endereco;
        this.capacidade = capacidade;
    }

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    // Método para exibir o local
    public void exibirLocal() {
        System.out.println("Local: " + nomeLocal);
        System.out.println("Endereço: " + endereco);
        System.out.println("Capacidade: " + capacidade + " pessoas");
    }
}
