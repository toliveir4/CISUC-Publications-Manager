/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.Comparator;

/**
 *
 * @author Tiago Oliveira
 */
public class ComparatorImpact implements Comparator<Publicacao> {

    /**
     * Construtor da classe ComparatorImpact
     */
    public ComparatorImpact() {
    }

    /**
     * Compara duas publicações pelo impacto, de modo a agrupar todas as
     * publicações por impacto decrescente (A > B > C), depois por ano e por fim
     * por tipo.
     *
     * @param p1 publicação atual
     * @param p2 publicação seguinte
     * @return 1, -1 ou 0
     */
    @Override
    public int compare(Publicacao p1, Publicacao p2) {
        if (p1.calculaImpacto().equals(p2.calculaImpacto())) {
            if (p1.getAnoPub() == p2.getAnoPub()) {
                if (p1.tipo().equals(p2.tipo())) {
                    return 0;
                } else {
                    return p1.tipo().compareTo(p2.tipo());
                }
            } else {
                return p1.getAnoPub() < p2.getAnoPub() ? 1 : -1;
            }
        } else {
            return p1.calculaImpacto().compareTo(p2.calculaImpacto());
        }
    }
}
