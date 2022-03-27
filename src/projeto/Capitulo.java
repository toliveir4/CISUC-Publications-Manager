package projeto;

/**
 *
 * @author Tiago Oliveira Nº2019219068
 */
public class Capitulo extends Livro {

    private String nomeCap;
    private int pagInicio, pagFim;

    /**
     * Construtor da classe Capitulo
     *
     * @param titulo Título da publicação
     * @param keywords Palavras-chave da publicação
     * @param resumo Resumo da publicação
     * @param anoPub Ano da publicação
     * @param dimensao Dimensão da publicação
     * @param editora Editora do livro referenciado na publicação
     * @param isbn ISBN do livro
     * @param nomeCap Nome do capítulo
     * @param pagInicio Página de início do capítulo
     * @param pagFim Página de fim do capítulo
     */
    public Capitulo(String titulo, String keywords, String resumo, int anoPub, int dimensao, String editora, String isbn, String nomeCap, int pagInicio, int pagFim) {
        super(titulo, keywords, resumo, anoPub, dimensao, editora, isbn);
        this.nomeCap = nomeCap;
        this.pagInicio = pagInicio;
        this.pagFim = pagFim;
    }

    /**
     *
     * @return Nome do capítulo
     */
    public String getNomeCap() {
        return nomeCap;
    }

    /**
     *
     * @param nomeCap Nome do capítulo
     */
    public void setNomeCap(String nomeCap) {
        this.nomeCap = nomeCap;
    }

    /**
     *
     * @return Página de início do capítulo
     */
    public int getPagInicio() {
        return pagInicio;
    }

    /**
     *
     * @param pagInicio Página de início do capítulo
     */
    public void setPagInicio(int pagInicio) {
        this.pagInicio = pagInicio;
    }

    /**
     *
     * @return Página de fim do capítulo
     */
    public int getPagFim() {
        return pagFim;
    }

    /**
     *
     * @param pagFim Página de fim do capítulo
     */
    public void setPagFim(int pagFim) {
        this.pagFim = pagFim;
    }

    /**
     *
     * @return String com o Fator de Impacto da publicação
     */
    @Override
    public String calculaImpacto() {
        String impacto = null;
        if (dimensao < 5000) {
            impacto = "C";
        } else if (5000 <= dimensao && dimensao < 10000) {
            impacto = "B";
        } else if (10000 <= dimensao) {
            impacto = "A";
        }
        return impacto;
    }

    /**
     *
     * @return String com o Tipo da publicação
     */
    @Override
    public String tipo() {
        return "Capítulo de Livro";
    }

    /**
     *
     * @return String com as informações específicas de uma publicação
     */
    @Override
    public String toString() {
        return super.toString() + "Nome do Capítulo: " + nomeCap + " ----- Página inicial: " + pagInicio + " ----- Página final: " + pagFim + "\n";
    }
}
