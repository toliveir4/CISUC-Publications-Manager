package projeto;

import java.io.Serializable;

/**
 *
 * @author Tiago Oliveira Nº2019219068
 */
public class Data implements Serializable {

    private int dia, mes, ano;

    /**
     * Construtor da classe Data
     *
     * @param dia Dia
     * @param mes Mes
     * @param ano Ano
     */
    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    /**
     *
     * @return int dia
     */
    public int getDia() {
        return dia;
    }

    /**
     *
     * @param dia Dia
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     *
     * @return int mes
     */
    public int getMes() {
        return mes;
    }

    /**
     *
     * @param mes Mes
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     *
     * @return int ano
     */
    public int getAno() {
        return ano;
    }

    /**
     *
     * @param ano Ano
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     *
     * @return String com o dia, o mês (por extenso) e o ano
     */
    @Override
    public String toString() {
        String[] meses = {"janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};
        String m = meses[mes - 1];
        String d = dia + " de " + m + " de " + ano;
        return d;
    }

}
