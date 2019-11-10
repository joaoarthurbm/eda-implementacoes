package tabelahash;

import java.util.HashSet;
import java.util.Set;

import util.Aluno;

/**
 * Esta classe representa a implementação de uma Tabela Hash que usa endereçamento aberto (sondagem linear)
 * para resolver colisões.
 * 
 * A implementação abaixo apenas lida com objetos do tipo aluno. Essa foi uma decisão para fins didáticos. 
 * Naturalmente, por ser uma estrutura de propósito geral, uma tabela hash deve ser capaz de manipular objetos
 * de qualquer tipo.
 *  
 * @author https://github.com/joaoarthurbm/
 * Computação @ UFCG
 */
public class TabelaHashEnderecamentoAberto {

    private Aluno[] tabela;
    private Set<Integer> chaves;
    private Set<Aluno> valores;
    private int size;
    private double fatorDeCarga;

    public static final int CAPACIDADE_DEFAULT = 20;

    /*
     *  Quão preenchida a tabela pode ficar. Se alcançar esse valor,
     *  o método resize é acionado.
     */
    public static final double FATOR_DE_CARGA_DEFAULT = 0.75;

    // flag para lidar com posições ocupadas anteriormente.
    // não são permitidos valores de chaves negativas, por isso a escolha.
    private static final Aluno APAGADO = new Aluno(Integer.MIN_VALUE, "APAGADO");

    /**
     * Cria uma nova tabela com capacidade inicial 20 e fator de carga 0.75.
     */
    public TabelaHashEnderecamentoAberto() {
        this(CAPACIDADE_DEFAULT, FATOR_DE_CARGA_DEFAULT);
    }

    /**
     * Cria uma nova tabela com a capacidade inicial e o fator de carga passados como 
     * parâmetros.
     * @param capacidade A capacidade inicial da tabela.
     * @param fatorDeCarga Um valor entre 0 e 1 representando a taxa de ocupação máxima da tabela.
     */
    public TabelaHashEnderecamentoAberto(int capacidade, double fatorDeCarga) {
        this.tabela = new Aluno[capacidade];
        this.chaves = new HashSet<Integer>();
        this.valores = new HashSet<Aluno>();
        this.fatorDeCarga = fatorDeCarga;
        this.size = 0;
    }

    /**
     * Calcula o hash utilizando o método da divisão.
     * @param chave A chave para a qual o hash deve ser calculado.
     * @return O hash calculado.
     */
    private int hash(Integer chave) {
        return chave % this.tabela.length;
    }

    /**
     * Calcula o hash utilizando o método da multiplicação.
     * @param chave A chave para a qual o hash deve ser calculado.
     * @return O hash calculado.
     */
    private int hashMultiplicacao(Integer chave) {
        double a = 0.617648934;
        double hash = chave*a;
        hash = (hash % 1) * this.tabela.length;
        return (int)hash;        
    }

    /**
     * @return O conjunto de chaves na tabela.
     */
    public Set<Integer> getKeys() {
        return this.chaves;
    }

    /**
     * @return O conjunto de valores presentes na tabela.
     */
    public Set<Aluno> getValue() {
        return this.valores;
    }

    /**
     * Recupera o aluno cuja chave é igual a passada como parâmetro.
     * @param chave a matrícula do aluno.
     * @return o aluno com a matrícula passada como parâmetro. null caso
     * nenhum aluno presente na tabela tenha a matrícula igual a passada como
     * parâmetro.
     */
    public Aluno get(Integer chave) {
        int sondagem = 0;
        int hash;
        while (sondagem < tabela.length) {

            hash = (hash(chave) + sondagem) % tabela.length;

            if (tabela[hash] == null) {
                return null;
            }

            if (tabela[hash].getMatricula().equals(chave)) {
                return tabela[hash];
            }

            sondagem += 1;

        }

        return null;
    }  

    /**
     * Adiciona o par chave, valor na tabela.
     * @param chave a matrícula do aluno a ser adicionado.
     * @param valor o objeto Aluno a ser adicionado na tabela.
     */
    public void put(Integer chave, Aluno valor) {

        // atingiu o limite. resize.
        if (this.size / this.tabela.length >= this.fatorDeCarga 
                || this.size == this.tabela.length) {

            // nova tabela
            Aluno[] novaTabela = new Aluno[this.tabela.length * 2];
            reinicializaTabela();

            for (Aluno aluno : tabela) {
                if (aluno != null) {
                    put(novaTabela, aluno.getMatricula(), aluno);
                }
            }

            put(novaTabela, valor.getMatricula(), valor);
            this.tabela = novaTabela;

        } else {
            this.put(this.tabela, chave, valor);
        }

    }  

    /**
     * Reinicia a tabela. Esse método é utilizado quando
     * é preciso aumentar o tamanho da tabela para acomodar mais
     * elementos.
     */
    private void reinicializaTabela() {
        this.size = 0;
        this.chaves = new HashSet<Integer>();
        this.valores = new HashSet<Aluno>();
    }

    /**
     * Adiciona o par chave, valor na tabela passada como parâmetro. Esse método
     * é privado e apenas usado internamente para simplificar o método resize.
     * @param tabela a tabela na qual o par chave,valor deve ser adicionado.
     * @param chave a chave do elemento a ser adicionado.
     * @param valor o objeto Aluno a ser adicionado.
     */
    private void put(Aluno[] tabela, Integer chave, Aluno valor) {
        int sondagem = 0;
        int hash;
        while (sondagem < tabela.length) {

            hash = (hash(chave) + sondagem) % tabela.length;
            Aluno tmpAluno = tabela[hash];
            if (tmpAluno == null || 
                    tmpAluno.getMatricula().equals(chave) ||
                    tmpAluno.equals(APAGADO)) {
                tabela[hash] = valor;
                this.chaves.add(chave);
                this.valores.add(valor);
                this.size += 1;
                return;
            }

            sondagem += 1;

        }

    }

    /**
     * Remove o aluno cuja matrícula é igual a chave passada como parâmetro. Importante destacar
     * que a posição em que o elemento está não é atribuida ao valor null, mas sim a um 
     * objeto flag APAGADO. Esse recurso é utilizado para pemitir que a sondagem consiga
     * diferenciar posições livres e decidir se deve seguir ou não.
     * @param chave A matrícula do aluno a ser removido.
     * @return O aluno a ser removido. null caso não haja aluno com a matrícula
     * passada como parâmetro. 
     */
    public Aluno remove(int chave) {
        int sondagem = 0;
        int hash;
        while (sondagem < tabela.length) {
                hash = (hash(chave) + sondagem) % tabela.length;

            if (tabela[hash] != null && tabela[hash].getMatricula().equals(chave)) {
                Aluno aluno = tabela[hash];  
                tabela[hash] = APAGADO;
                this.chaves.remove(chave);
                this.valores.remove(aluno);
                this.size -= 1;
                return aluno;
            } 

            sondagem += 1;

        }

        return null;
    }

    /**
     * Retorna a quantidade de elementos presentes na tabela. 
     * @return o número de elementos armazenados na tabela.
     */
    public int size() {
        return this.size;
    }

}
