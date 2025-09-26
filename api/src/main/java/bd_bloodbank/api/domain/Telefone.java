package bd_bloodbank.api.domain;

public class Telefone {
    private int id;         
    private String telefone;

    public Telefone() {}

    public Telefone(int id, String telefone) {
        this.id = id;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
