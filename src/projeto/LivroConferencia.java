package projeto;

/**
 *
 * @author Tiago Oliveira4 Nº2019219068
 */
public class LivroConferencia extends Livro {

    private String nomeConf;
    private int numArtigos;

    /**
     * Construtor da classe LivroConferencia
     *
     * @param titulo Título da publicação
     * @param keywords Palavras-chave da publicação
     * @param resumo Resumo da publicação
     * @param anoPub Ano da publicação
     * @param dimensao Dimensão da publicação
     * @param editora Editora do livro referenciado na publicação
     * @param isbn ISBN do livro
     * @param nomeConf Nome da Conferência descrita no livro
     * @param numArtigos Número de artigos existentes no livro
     */
    public LivroConferencia(String titulo, String keywords, String resumo, int anoPub, int dimensao, String editora, String isbn, String nomeConf, int numArtigos) {
        super(titulo, keywords, resumo, anoPub, dimensao, editora, isbn);
        this.nomeConf = nomeConf;
        this.numArtigos = numArtigos;
    }

    /**
     *
     * @return Nome da Conferência descrita no livro
     */
    public String getNomeConf() {
        return nomeConf;
    }

    /**
     *
     * @param nomeConf Nome da Conferência descrita no livro
     */
    public void setNomeConf(String nomeConf) {
        this.nomeConf = nomeConf;
    }

    /**
     *
     * @return Número de artigos existentes no livro
     */
    public int getNumArtigos() {
        return numArtigos;
    }

    /**
     *
     * @param numArtigos Número de artigos existentes no livro
     */
    public void setNumArtigos(int numArtigos) {
        this.numArtigos = numArtigos;
    }

    /**
     *
     * @return String com o Fator de Impacto da publicação
     */
    @Override
    public String calculaImpacto() {
        String impacto = null;
        if (dimensao < 2500) {
            impacto = "C";
        } else if (2500 <= dimensao && dimensao < 7500) {
            impacto = "B";
        } else if (7500 <= dimensao) {
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
        return "Livro de Artigos de Conferência";
    }

    /**
     *
     * @return String com as informações específicas de uma publicação
     */
    @Override
    public String toString() {
        return super.toString() + "Nome da Conferência: " + nomeConf + " ----- Nº de artigos: " + numArtigos + "\n";
    }
}
