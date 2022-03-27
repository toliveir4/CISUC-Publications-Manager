package projeto;

/**
 *
 * @author Tiago Oliveira Nº2019219068
 */
public class AConferencia extends Publicacao {

    private String nome, local;
    private Data data;

    /**
     * Construtor da classe AConferencia
     *
     * @param titulo Título da publicação
     * @param keywords Palavras-chave da publicação
     * @param resumo Resumo da publicação
     * @param anoPub Ano da publicação
     * @param dimensao Dimensão da publicação
     * @param nome Nome da conferência referida na publicação
     * @param localizacao Local onde se realizou a conferência
     * @param data Data de ocorrência da conferência
     */
    public AConferencia(String titulo, String keywords, String resumo, int anoPub, int dimensao, String nome, String localizacao, Data data) {
        super(titulo, keywords, resumo, anoPub, dimensao);
        this.nome = nome;
        this.local = localizacao;
        this.data = data;
    }

    /**
     *
     * @return Nome da conferência referida na publicação
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome Nome da conferência referida na publicação
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return Local onde se realizou a conferência
     */
    public String getLocal() {
        return local;
    }

    /**
     *
     * @param local Local onde se realizou a conferência
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     *
     * @return Data de ocorrência da Conferência
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @param data Data de ocorrência da Conferência
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
        if (dimensao < 500) {
            impacto = "C";
        } else if (500 <= dimensao && dimensao < 1000) {
            impacto = "B";
        } else if (1000 <= dimensao) {
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
        return super.toString() + "Nome: " + nome + " --- Localização: " + local + " --- Data: " + data + "\n";
    }

    /**
     *
     * @return String com o Tipo da publicação
     */
    @Override
    public String tipo() {
        return "Artigo de Conferência";
    }
}
