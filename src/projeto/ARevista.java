package projeto;

/**
 *
 * @author Tiago Oliveira Nº2019219068
 */
public class ARevista extends Publicacao {

    private String nome;
    private int nRevista;
    private Data data;

    /**
     * Construtor da classe ARevista
     *
     * @param titulo Título da publicação
     * @param keywords Palavras-chave da publicação
     * @param resumo Resumo da publicação
     * @param anoPub Ano da publicação
     * @param dimensao Dimensão da publicação
     * @param nome Nome da Revista referida na publicação
     * @param nRevista Número da Revista
     * @param data Data de publicação da Revista
     */
    public ARevista(String titulo, String keywords, String resumo, int anoPub, int dimensao, String nome, int nRevista, Data data) {
        super(titulo, keywords, resumo, anoPub, dimensao);
        this.nome = nome;
        this.nRevista = nRevista;
        this.data = data;
    }

    /**
     *
     * @return Nome da Revista referida na publicação
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome Nome da Revista referida na publicação
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return Número da Revista
     */
    public int getnRevista() {
        return nRevista;
    }

    /**
     *
     * @param nRevista Número da Revista
     */
    public void setnRevista(int nRevista) {
        this.nRevista = nRevista;
    }

    /**
     *
     * @return Data de publicação da Revista
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @param data Data de publicação da Revista
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     *
     * @return String com o Fator de Impacto da publicação
     */
    @Override
    public String calculaImpacto() {
        String impacto = null;
        if (dimensao < 200) {
            impacto = "C";
        } else if (200 <= dimensao && dimensao < 500) {
            impacto = "B";
        } else if (500 <= dimensao) {
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
        return "Artigo de Revista";
    }

    /**
     *
     * @return String com as informações específicas de uma publicação
     */
    @Override
    public String toString() {
        return super.toString() + "Nome: " + nome + " --- Revista Nº: " + nRevista + " --- Data: " + data + "\n";
    }

}
