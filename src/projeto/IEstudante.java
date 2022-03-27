package projeto;

import java.util.Random;

/**
 *
 * @author Tiago Oliveira Nº2019219068
 */
public class IEstudante extends Investigador {

    private String tTese;
    private Data dataConclusao;
    private Random random;
    private Investigador orientador;
    private String nomeOrientador;

    /**
     * Construtor da classe IEstudante
     *
     * @param nome Nome do estudante
     * @param e Email do estudante
     * @param tTese Título da tese
     * @param conclusionDate Data de conclusão/entrega da tese
     * @param nomeOrientador Nome do orientador da tese do estudante
     *
     */
    public IEstudante(String nome, String e, String tTese, Data conclusionDate, String nomeOrientador) {
        super(nome, e);
        this.tTese = tTese;
        this.dataConclusao = conclusionDate;
        random = new Random();
        this.nomeOrientador = nomeOrientador;
    }

    /**
     * Associa os orientadores de tese aos estudantes.Têm de ser
     * obrigatóriamente investigadores efetivos. Se no ficheiro não for
     * pré-definido nenhum orientador, ou esse orientador não pertencer ao CISUC
     * é escolhido um ao acaso, sendo apenas considerados os efetivos do devido
     * grupo de investigação
     *
     */
    private Investigador addOrientador() {
        Investigador or = null;
        if (nomeOrientador != null) {
            for (Investigador i : grupo.getInvestigadores()) {
                if (nomeOrientador.equals(i.getNome()) && i.categoria().equals("Efetivo")) {
                    or = i;
                }
            }
        }
        if (or == null || nomeOrientador == null) {
            while (or == null || !or.categoria().equals("Efetivo")) {
                int index = getRandom().nextInt(grupo.getInvestigadores().size());
                or = grupo.getInvestigadores().get(index);
            }
        }
        return or;
    }

    /**
     *
     * @param tTese Título da tese
     */
    public void settTese(String tTese) {
        this.tTese = tTese;
    }

    /**
     *
     * @param dataConclusao Data de conclusão/entrega da tese
     */
    public void setDataConclusao(Data dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    /**
     *
     * @param random variável random a ser usada
     */
    public void setRandom(Random random) {
        this.random = random;
    }

    /**
     *
     * @return variável random
     */
    public Random getRandom() {
        return random;
    }

    /**
     *
     * @return Título da tese
     */
    public String gettTese() {
        return tTese;
    }

    /**
     *
     * @return Data de conclusão/entrega da tese
     */
    public Data getDataConclusao() {
        return dataConclusao;
    }

    /**
     *
     * @return Orientador da tese (Categoria: Efetivo)
     */
    public Investigador getOrientador() {
        return orientador;
    }

    /**
     *
     * @param orientador Orientador da tese (Categoria: Efetivo)
     */
    public void setOrientador(Investigador orientador) {
        this.orientador = addOrientador();
    }

    /**
     *
     * @return Nome do orientador da tese do estudante
     */
    public String getNomeOrientador() {
        return nomeOrientador;
    }

    /**
     *
     * @param nomeOrientador Nome do orientador da tese do estudante
     */
    public void setNomeOrientador(String nomeOrientador) {
        this.nomeOrientador = nomeOrientador;
    }

    /**
     *
     * @return String com as informações dos Estudantes
     */
    @Override
    public String toString() {
        return nome + " -- " + email + " -- " + tTese + " -- " + dataConclusao + " -- Orientador: " + addOrientador().getNome();
    }

    /**
     *
     * @return Categoria do Estudante
     */
    @Override
    public String categoria() {
        return "Estudante";
    }

}
