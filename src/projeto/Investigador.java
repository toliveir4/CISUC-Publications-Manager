package projeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Tiago Oliveira Nº2019219068
 */
public abstract class Investigador implements Comparable<Investigador>, Serializable {

    protected String nome, email;
    protected ArrayList<Publicacao> pubI;
    protected GrupoInvestigacao grupo;

    /**
     * Construtor da classe Investigador
     *
     * @param nome Nome do Investigador
     * @param e Email do Investigador
     */
    public Investigador(String nome, String e) {
        pubI = new ArrayList<>();
        this.nome = nome;
        email = e;
    }

    /**
     * Mostra todas as publicações feitas pelo investigador. Percorre as
     * publicações e mostra-as agrupadas de 3 maneiras diferentes. Cabe ao
     * utilizador escolher de que maneira as quer agrupar. Agrupa por ano (sendo
     * que se for igual verifica o tipo e se este também for igual verifica o
     * impacto), por tipo (se for igual verifica o ano e se também coincidir
     * verifica o impacto) ou por impacto (lógica idêntica à dos anteriores).
     */
    public void listaPublicacoes() {
        if (getPubI().isEmpty()) {
            System.out.println("Este investigador não tem publicações.");
        } else if (getPubI().size() == 1) {
            System.out.printf("----- PUBLICAÇÃO DE %s -----\n", getNome().toUpperCase());
            System.out.println(getPubI().get(0));
        } else {
            System.out.println("Agrupar por Ano (A), Tipo (T) ou Fator de Impacto (I)");
            System.out.print("Apenas introduza A, T ou I: ");
            Scanner scan = new Scanner(System.in);
            String s = scan.next();
            System.out.printf("----- LISTA DE PUBLICAÇÕES DE %s -----\n", getNome().toUpperCase());
            switch (s.toUpperCase()) {
                case "A":
                    Collections.sort(getPubI());
                    for (Publicacao pub : getPubI()) {
                        System.out.println(pub.toString());
                    }
                    break;
                case "T":
                    Collections.sort(getPubI(), new ComparatorType());
                    for (Publicacao pub : getPubI()) {
                        System.out.println(pub.toString());
                    }
                    break;
                case "I":
                    Collections.sort(getPubI(), new ComparatorImpact());
                    for (Publicacao pub : getPubI()) {
                        System.out.println(pub.toString());
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     *
     * @return Nome do Investigador
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome Nome do Investigador
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return Email do Investigador
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email Email do Investigador
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return ArrayList com as publicações do investigador
     */
    public ArrayList<Publicacao> getPubI() {
        return pubI;
    }

    /**
     *
     * @param pubI ArrayList com as publicações do investigador
     */
    public void setPubI(ArrayList<Publicacao> pubI) {
        this.pubI = pubI;
    }

    /**
     *
     * @return Grupo a que o investigador pertence
     */
    public GrupoInvestigacao getGrupo() {
        return grupo;
    }

    /**
     *
     * @param grupo Grupo a que o investigador pertence
     */
    public void setGrupo(GrupoInvestigacao grupo) {
        this.grupo = grupo;
    }

    /**
     *
     * @param g Publicação a adicionar ao ArrayList do Investigador
     */
    public void addPub(Publicacao g) {
        pubI.add(g);
    }

    /**
     *
     * @return Categoria do Investigador
     */
    public abstract String categoria();

    /**
     *
     * @param i Investigador a ser comparado
     * @return 1 se a categoria é superior, -1 caso seja inferior ou 0 se for
     * igual
     */
    @Override
    public int compareTo(Investigador i) {
        return this.categoria().compareTo(i.categoria());
    }

}
