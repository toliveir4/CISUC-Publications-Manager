package projeto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Tiago Oliveira Nº2019219068
 */
public abstract class Publicacao implements Comparable<Publicacao>, Serializable {

    protected String titulo, keywords, resumo;
    protected int anoPub, dimensao;
    protected ArrayList<Investigador> autores;

    /**
     * Construtor da classe Publicacao
     *
     * @param titulo Título da publicação
     * @param keyw Palavras-chave da publicação
     * @param resumo Resumo da publicação
     * @param anoPub Ano da publicação
     * @param dimensao Dimensão da publicação
     */
    public Publicacao(String titulo, String keyw, String resumo, int anoPub, int dimensao) {
        autores = new ArrayList<>();
        this.titulo = titulo;
        keywords = keyw;
        this.resumo = resumo;
        this.anoPub = anoPub;
        this.dimensao = dimensao;
    }

    /**
     *
     * @param i Investigador que escreveu (ou ajudou a escrever) a publicação
     */
    public void addAutores(Investigador i) {
        autores.add(i);
    }

    /**
     *
     * @return Nome dos autores da publicação (existentes no CISUC)
     */
    public String NomeAutores() {
        String s = "";
        for (Investigador autor : autores) {
            String[] nomes = autor.getNome().split(" ");
            if (autor.categoria().equals("Efetivo")) {
                s += "Professor " + nomes[0] + " " + nomes[nomes.length - 1] + " e ";
            } else {
                s += autor.getNome().charAt(0) + ". " + nomes[nomes.length - 1] + " e ";
            }
        }
        return s.substring(0, s.length() - 3);  //Remove " e " do fim da String
    }

    /**
     *
     * @return Título da publicação
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     *
     * @param titulo Título da publicação
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     *
     * @return Ano da publicação
     */
    public int getAnoPub() {
        return anoPub;
    }

    /**
     *
     * @param anoPub Ano da publicação
     */
    public void setAnoPub(int anoPub) {
        this.anoPub = anoPub;
    }

    /**
     *
     * @return ArrayList com os autores das publicações
     */
    public ArrayList<Investigador> getAutores() {
        return autores;
    }

    /**
     *
     * @param autores ArrayList com os autores das publicações
     */
    public void setAutores(ArrayList<Investigador> autores) {
        this.autores = autores;
    }

    /**
     *
     * @return Palavras-chave da publicação
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     *
     * @param keywords Palavras-chave da publicação
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     *
     * @return Resumo da publicação
     */
    public String getResumo() {
        return resumo;
    }

    /**
     *
     * @param resumo Resumo da publicação
     */
    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    /**
     *
     * @return Dimensão da publicação
     */
    public int getDimensao() {
        return dimensao;
    }

    /**
     *
     * @param dimensao Dimensão da publicação
     */
    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    /**
     *
     * @return String com o Fator de Impacto da publicação
     */
    public abstract String calculaImpacto();

    /**
     *
     * @return String com o Tipo da publicação
     */
    public abstract String tipo();

    /**
     *
     * @return String com as informações gerais de uma publicação
     */
    @Override
    public String toString() {
        return tipo() + " -- " + calculaImpacto() + " -- \"" + titulo + "\"" + ", por " + NomeAutores() + ", " + anoPub + "\n" + "Keywords - " + getKeywords() + " -- Resumo - " + getResumo() + "\n";
    }

    /**
     * Compara as publicações pelo ano, de modo a agrupar todas as publicações
     * por anos, posteriormente por tipo e por fim por impacto.
     *
     * @param p Publicação a ser comparada
     * @return 1, -1 ou 0
     */
    @Override
    public int compareTo(Publicacao p) {
        if (this.getAnoPub() == p.getAnoPub()) {
            if (this.tipo().equals(p.tipo())) {
                if (this.calculaImpacto().equals(p.calculaImpacto())) {
                    return 0;
                } else {
                    return this.calculaImpacto().compareTo(p.calculaImpacto());
                }
            } else {
                return this.tipo().compareTo(p.tipo());
            }
        } else {
            return this.getAnoPub() < p.getAnoPub() ? 1 : -1;
        }
    }
}
