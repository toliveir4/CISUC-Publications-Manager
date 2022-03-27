package projeto;

/**
 *
 * @author Tiago Oliveira
 */
public class Livro extends Publicacao {

    protected String editora, isbn;

    /**
     * Construtor da classe Livro
     *
     * @param titulo Título da publicação
     * @param keywords Palavras-chave da publicação
     * @param resumo Resumo da publicação
     * @param anoPub Ano da publicação
     * @param dimensao Dimensão da publicação
     * @param editora Editora do livro referenciado na publicação
     * @param isbn ISBN do livro
     */
    public Livro(String titulo, String keywords, String resumo, int anoPub, int dimensao, String editora, String isbn) {
        super(titulo, keywords, resumo, anoPub, dimensao);
        this.editora = editora;
        this.isbn = isbn;
    }

    /**
     *
     * @return Editora do livro referenciado na publicação
     */
    public String getEditora() {
        return editora;
    }

    /**
     *
     * @param editora Editora do livro referenciado na publicação
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     *
     * @return ISBN do livro
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     *
     * @param isbn ISBN do livro
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
     * @return String com as informações específicas de uma publicação
     */
    @Override
    public String toString() {
        return super.toString() + "Editora: " + editora + " ----- ISBN: " + isbn + "\n";
    }

    /**
     *
     * @return String com o Tipo da publicação
     */
    @Override
    public String tipo() {
        return "Livro";
    }
}
