package projeto;

import java.util.Comparator;

/**
 *
 * @author Tiago Oliveira
 */
public class ComparatorType implements Comparator<Publicacao> {

    /**
     * Construtor da classe ComparatorImpact
     */
    public ComparatorType() {
    }

    /**
     * Compara duas publicações pelo tipo, de modo a agrupar todas as
     * publicações por tipos, posteriormente por ano e por fim por impacto.
     *
     * @param p1 publicação atual
     * @param p2 publicação seguinte
     * @return 1, -1, ou 0
     */
    @Override
    public int compare(Publicacao p1, Publicacao p2) {
        if (p1.tipo().equals(p2.tipo())) {
            if (p1.getAnoPub() == p2.getAnoPub()) {
                if (p1.calculaImpacto().equals(p2.calculaImpacto())) {
                    return 0;
                } else {
                    return p1.calculaImpacto().compareTo(p2.calculaImpacto());
                }
            } else {
                return p1.getAnoPub() < p2.getAnoPub() ? 1 : -1;
            }
        } else {
            return p1.tipo().compareTo(p2.tipo());
        }
    }
}
