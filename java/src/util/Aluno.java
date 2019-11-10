package util;

public class Aluno {

    private Integer matricula;
    private String nome;
    
    public Aluno(Integer matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }
    
    public Integer getMatricula() {
        return matricula;
    }
    
    public String getNome() {
        return nome;
    }
    
    @Override
    public int hashCode() {
        return this.matricula;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Aluno))
            return false;
        
        Aluno test = (Aluno) obj;
        return test.getMatricula().equals(this.matricula);
    }
    
    public String toString() {
        return nome;
    }
    
}
