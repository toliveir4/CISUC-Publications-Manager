package projeto;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Tiago Oliveira Nº2019219068
 */
public class GrupoInvestigacao implements Serializable {

    private String nome, acro;
    private Investigador responsavel;
    private ArrayList<Investigador> investigadores;
    private ArrayList<Publicacao> pubG;

    /**
     * construtor da classe GrupoInvestigacao
     *
     * @param nome nome do grupo
     * @param ac acrónimo do grupo
     */
    public GrupoInvestigacao(String nome, String ac) {
        this.pubG = new ArrayList<>();
        this.investigadores = new ArrayList<>();
        this.nome = nome;
        this.acro = ac;
    }

    /**
     * Adiciona um investigador ao ArrayList com os investigadores pertencentes
     * ao grupo
     *
     * @param i Investigador a adicionar
     */
    public void addInvestigadores(Investigador i) {
        investigadores.add(i);
    }

    /**
     * Adiciona uma publicação ao ArrayList com as publicações do grupo
     *
     * @param p Publicação a adicionar
     */
    public void addPubG(Publicacao p) {
        pubG.add(p);
    }

    /**
     *
    * @return String com todos os investigadores do grupo
     */
    public String listaMembros() {
        String s = "";
        Collections.sort(investigadores);
        s += "Responsável: " + responsavel.getNome() + "\n";
        s += "Efetivos:\n";
        for (Investigador i : investigadores) {
            if (i.categoria().equals("Efetivo")) {
                s += "\t" + i.getNome() + "\n";
            }
        }
        s += "Estudantes:\n";
        for (Investigador i : investigadores) {
            if (i.categoria().equals("Estudante")) {
                s += "\t" + i.getNome() + "\n";
            }
        }
        return s;
    }

    /**
     *
     * @return Array de inteiros, onde a primeira posição é o número de efetivos
     * do grupo e a segunda (e última) é o número de estudantes
     */
    public int[] nInvPorCategoria() {
        int count_ef = 0, count_es = 0;
        Collections.sort(investigadores);
        for (Investigador i : investigadores) {
            if (i.categoria().equals("Efetivo")) {
                count_ef++;
            } else {
                count_es++;
            }
        }
        return new int[]{count_ef, count_es};
    }

    /**
     * A String apenas contém as publicações dos últimos 5 anos
     *
     * @return String com todas as publicações do grupo
     */
    public String listaPublicacoes() {
        String s = "";
        if (pubG.isEmpty()) {
            return "Este grupo não tem publicações.\n";
        } else {
            System.out.printf("----- LISTA DE PUBLICAÇÕES DOS ÚLTIMOS 5 ANOS DE %s -----\n", nome.toUpperCase());
            Collections.sort(pubG);
            for (Publicacao pub : pubUltimos5Anos()) {
                s += pub.toString() + "\n";
            }
            return s;
        }
    }

    /**
     * O ArrayList apenas contém as publicações dos últimos 5 anos
     *
     * @return ArrayList das publicações
     */
    public ArrayList<Publicacao> pubUltimos5Anos() {
        ArrayList<Publicacao> pub = new ArrayList<>();
        for (Publicacao p : pubG) {
            if (Year.now().compareTo(Year.of(p.getAnoPub())) <= 5 && Year.now().compareTo(Year.of(p.getAnoPub())) >= 0) {
                pub.add(p);
            }
        }
        return pub;
    }

    /**
     * Apenas retorna os números das publicações dos últimos 5 anos
     *
     * @return String com os valores para cada ano de publicação e os devidos
     * anos
     */
    public String nPubPorAno() {
        int count = 1;
        String s = "";
        Collections.sort(pubG);                         //Faz a filtragem já agrupados da maneira correta
        for (int i = 1; i < pubUltimos5Anos().size(); i++) {
            int t1 = pubUltimos5Anos().get(i).getAnoPub();
            int t2 = pubUltimos5Anos().get(i - 1).getAnoPub();
            if (i == pubUltimos5Anos().size() - 1) {
                if (t1 != t2) {
                    s += "\t\t" + count + "  " + t2 + "\n";
                    count = 1;
                    s += "\t\t" + count + "  " + t1 + "\n";
                } else {
                    count++;
                    s += "\t\t" + count + "  " + t2 + "\n";
                }
            } else if (t1 != t2 && i < pubUltimos5Anos().size() - 1) {
                s += "\t\t" + count + "  " + t2 + "\n";
                count = 1;
            } else {
                count++;
            }
        }
        return s;
    }

    /**
     * Apenas retorna os números das publicações dos últimos 5 anos
     *
     * @return String com os valores para cada tipo de publicação e os devidos
     * tipos
     */
    public String nPubPorTipo() {
        int count = 1;
        String s = "";
        Collections.sort(pubG, new ComparatorType());   //Faz a filtragem já agrupados da maneira correta
        for (int i = 1; i < pubUltimos5Anos().size(); i++) {
            String t1 = pubUltimos5Anos().get(i).tipo();
            String t2 = pubUltimos5Anos().get(i - 1).tipo();
            if (i == pubUltimos5Anos().size() - 1) {
                if (!t1.equals(t2)) {
                    s += "\t\t" + count + "  " + t2 + "\n";
                    count = 1;
                    s += "\t\t" + count + "  " + t1 + "\n";
                } else {
                    count++;
                    s += "\t\t" + count + "  " + t1 + "\n";
                }

            } else if (!t1.equals(t2) && i != pubUltimos5Anos().size() - 1) {
                s += "\t\t" + count + "  " + t2 + "\n";
                count = 1;
            } else {
                count++;
            }
        }
        return s;
    }

    /**
     * Apenas retorna os números das publicações dos últimos 5 anos
     *
     * @return String com os valores para cada impacto de publicação e os
     * devidos impactos
     */
    public String nPubPorImpacto() {
        int count = 1;
        String s = "";
        Collections.sort(pubG, new ComparatorImpact()); //Faz a filtragem já agrupados da maneira correta
        for (int i = 1; i < pubUltimos5Anos().size(); i++) {
            String t1 = pubUltimos5Anos().get(i).calculaImpacto();
            String t2 = pubUltimos5Anos().get(i - 1).calculaImpacto();
            if (i == pubUltimos5Anos().size() - 1) {
                if (!t1.equals(t2)) {
                    s += "\t\t" + count + " Impacto " + t2 + "\n";
                    count = 1;
                    s += "\t\t" + count + " Impacto " + t1 + "\n";
                } else {
                    count++;
                    s += "\t\t" + count + " Impacto " + t2 + "\n";
                }
            } else if (!t1.equals(t2) && i != pubUltimos5Anos().size() - 1) {
                s += "\t\t" + count + " Impacto " + t2 + "\n";
                count = 1;
            } else {
                count++;
            }
        }
        return s;
    }

    /**
     *
     * @return Nome do grupo
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome Nome do grupo
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return Acrónimo do grupo
     */
    public String getAcro() {
        return acro;
    }

    /**
     *
     * @param acro Acrónimo do grupo
     */
    public void setAcro(String acro) {
        this.acro = acro;
    }

    /**
     *
     * @return Investigador responsável do grupo
     */
    public Investigador getResponsavel() {
        return responsavel;
    }

    /**
     *
     * @param responsavel Investigador responsável do grupo
     */
    public void setResponsavel(Investigador responsavel) {
        this.responsavel = responsavel;
    }

    /**
     *
     * @return ArrayList com os investigadores pertencentes ao grupo
     */
    public ArrayList<Investigador> getInvestigadores() {
        return investigadores;
    }

    /**
     *
     * @param investigadores ArrayList com os investigadores pertencentes ao
     * grupo
     */
    public void setInvestigadores(ArrayList<Investigador> investigadores) {
        this.investigadores = investigadores;
    }

    /**
     *
     * @return ArrayList com as publicações associadas ao grupo
     */
    public ArrayList<Publicacao> getPubG() {
        return pubG;
    }

    /**
     *
     * @param pubG ArrayList com as publicações associadas ao grupo
     */
    public void setPubG(ArrayList<Publicacao> pubG) {
        this.pubG = pubG;
    }

    /**
     *
     * @return String com as informações do grupo de investigação
     */
    @Override
    public String toString() {
        return "----- " + acro + " (" + nome + ") -----" + "\nResponsável: " + responsavel.getNome() + "\n";
    }

}
