package projeto;

/**
 *
 * @author Tiago Oliveira Nº2019219068
 */
public class IEfetivo extends Investigador {

    private int nGabinete;
    private double nTelefone;

    /**
     * Construtor da classe IEfetivo
     *
     * @param nome Nome do Investigador
     * @param e Email do Investigador
     * @param officeN Nº do gabinete do Investigador (Efetivo)
     * @param phoneN Nº de telefone do Investigador (Efetivo)
     */
    public IEfetivo(String nome, String e, int officeN, double phoneN) {
        super(nome, e);
        this.nGabinete = officeN;
        this.nTelefone = phoneN;
    }

    /**
     *
     * @return Nº do gabinete do Investigador (Efetivo)
     */
    public int getnGabinete() {
        return nGabinete;
    }

    /**
     *
     * @return Nº de telefone do Investigador (Efetivo)
     */
    public double getnTelefone() {
        return nTelefone;
    }

    /**
     *
     * @param nGabinete Nº do gabinete do Investigador (Efetivo)
     */
    public void setnGabinete(int nGabinete) {
        this.nGabinete = nGabinete;
    }

    /**
     *
     * @param nTelefone Nº de telefone do Investigador (Efetivo)
     */
    public void setnTelefone(double nTelefone) {
        this.nTelefone = nTelefone;
    }

    /**
     *
     * @return String com as informações dos Efetivos
     */
    @Override
    public String toString() {
        return "Professor " + nome + " -- " + email + " -- " + nGabinete + " -- " + nTelefone;
    }

    /**
     *
     * @return Categoria do Efetivo
     */
    @Override
    public String categoria() {
        return "Efetivo";
    }
}
