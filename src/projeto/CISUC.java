package projeto;

import java.io.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Tiago Oliveira Nº2019219068
 */
public class CISUC {

    /**
     * Carrega os ficheiros e executa a interface Menu
     *
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        CISUC c = new CISUC();
        boolean sair = false;
        while (!sair) {
            c.showMenuOptions();
            sair = c.menu();
        }
    }

    private ArrayList<GrupoInvestigacao> grupos;
    private Random random;

    /**
     * construtor da classe CISUC
     */
    public CISUC() {
        this.grupos = new ArrayList<>();
        random = new Random();
        readFichObj();
        gereGrupos();
    }

    /**
     * Apresenta todas as opções disponíveis e que serão realizadas pelo
     * programa
     */
    public void showMenuOptions() {
        System.out.println("\n---------------------------MENU---------------------------");
        System.out.println("1 - Informações gerais do CISUC");
        System.out.println("2 - Lista de publicações, dos últimos 5 anos, de um grupo");
        System.out.println("3 - Lista de membros de um grupo");
        System.out.println("4 - Lista de publicações de um investigador");
        System.out.println("5 - Informações de cada grupo");
        System.out.println("0 - Sair");
        System.out.println("----------------------------------------------------------");
        System.out.print("\nEscolha uma ação a realizar: ");
    }

    /**
     * Dá return false se não tiver sido escolhida a opção de sair. Caso seja
     * escolhida essa opcção dá return true, fazendo o programa acabar.
     *
     * @return false ou true
     */
    public boolean menu() {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextInt()) {

            int num = scan.nextInt();

            switch (num) {
                case 1:
                    infoCISUC();
                    break;
                case 2:
                    listaPubGrupo();
                    break;

                case 3:
                    listaMembrosGrupo();
                    break;
                case 4:
                    listaPubInvestigador();
                    break;
                case 5:
                    infoGrupos();
                    break;
                case 0:
                    writeFichObj();
                    return true;
                default:
                    System.out.println("Ação Inválida");
                    break;
            }
        } else {
            System.out.println("Ação Inválida");
            scan.nextLine();
        }
        return false;
    }

    /**
     *
     * @param d Data a ser verificada se é passado ou futuro
     * @param referencia Data considerada como referência
     * @return true se for futuro
     * @return false se for passado
     */
    private boolean FutureOrPast(Data d, Data referencia) {
        if (d.getAno() > referencia.getAno()) {
            return true;
        } else if (d.getAno() == referencia.getAno()) {
            if (d.getMes() > referencia.getMes()) {
                return true;
            } else if (d.getMes() == referencia.getMes()) {
                return d.getDia() > referencia.getDia();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     *
     * @param d data de publicação do artigo referente à publicação
     * @param ano da publicação
     * @return data possível para a publicação do artigo que é feito referência
     */
    private Data checkDataPub(Data d, int ano) {
        Data ref;
        if (ano == Year.now().getValue()) {  //Se a publicação for do ano corrente considera-se a data de referência como a atual
            ref = new Data(MonthDay.now().getDayOfMonth(), MonthDay.now().getMonthValue(), ano);
            if (FutureOrPast(d, ref)) {
                if (ref.getMes() - 1 < 0) {
                    d = new Data(d.getDia(), (ref.getMes() - 1) % 12, ref.getAno() - 1);
                    System.out.println("11");
                } else if (ref.getMes() - 1 == 0) { //Quando o valor é 0 assume-se 12
                    d = new Data(d.getDia(), 12, ref.getAno() - 1);
                } else {
                    d = new Data(d.getDia(), (ref.getMes() - 1) % 12, ref.getAno());
                    System.out.println("22");
                }
            }
        } else {                                //No caso de ser de anos passados considera que essa publicação seja válida desde que o artigo tenho sido feito nesse ano
            ref = new Data(31, 12, ano);
            if (FutureOrPast(d, ref)) {
                d = new Data(d.getDia(), d.getMes(), ano);
            }
        }
        return d;
    }

    /**
     *
     * @param d Data que irá verificar se o dia e o mês são válidos
     */
    private void checkMesDia(Data d) {
        if (d.getMes() <= 12 && d.getMes() >= 1) {
        } else if (d.getMes() > 12) {
            d.setMes(12);
        } else {
            d.setMes(1);
        }
        if (d.getMes() == 4 || d.getMes() == 6 || d.getMes() == 9 || d.getMes() == 11) {
            if (d.getDia() > 30) {
                d.setDia(30);
            } else if (d.getDia() <= 0) {
                d.setDia(1);
            }
        } else if (d.getMes() == 2) {
            if (d.getAno() % 4 == 0) {  //Anos bissextos
                if (d.getDia() > 29) {
                    d.setDia(29);
                } else if (d.getDia() <= 0) {
                    d.setDia(1);
                }
            } else {
                if (d.getDia() > 28) {
                    d.setDia(28);
                } else if (d.getDia() <= 0) {
                    d.setDia(1);
                }
            }
        } else {
            if (d.getDia() > 31) {
                d.setDia(31);
            } else if (d.getDia() <= 0) {
                d.setDia(1);
            }
        }
    }

    private void addGrupos(GrupoInvestigacao g) {
        grupos.add(g);
    }

    /**
     *
     * @param g grupo a ser verificado
     * @return true se o grupo ainda não existir
     * @return false se o grupo já existir
     */
    private boolean checkGrupos(GrupoInvestigacao g) {
        if (getGrupos().isEmpty()) {
            return true;
        } else {
            int i = 0;
            while (!grupos.get(i).getNome().equals(g.getNome()) && i < getGrupos().size() - 1) {
                i++;
            }
            return !grupos.get(i).getNome().equals(g.getNome());
        }
    }

    /**
     * Se um grupo não tiver nenhum membro ou, no caso de existirem membros, não
     * existir nenhum membro efetivo, o grupo é removido. No caso de existirem
     * membros efetivos e se não existir nenhum responsável é escolhido, entre
     * os efetivos disponíveis, um ao acaso para o cargo.
     */
    private void gereGrupos() {
        for (int i = 0; i < getGrupos().size(); i++) {
            if (getGrupos().get(i).getInvestigadores().isEmpty()) {
                getGrupos().remove(getGrupos().get(i));
                i--;
            } else {
                Investigador inv = null;
                for (int j = 0; j < getGrupos().get(i).getInvestigadores().size(); j++) {
                    if (getGrupos().get(i).getInvestigadores().get(j).categoria().equals("Efetivo")) {
                        inv = getGrupos().get(i).getInvestigadores().get(j);
                    }
                }
                if (inv == null) {
                    getGrupos().remove(getGrupos().get(i));
                    System.out.printf("Grupo %s removido com sucesso. (Não existiam membros efetivos no grupo)\n", getGrupos().get(i).getAcro());
                    i--;
                } else {
                    if (getGrupos().get(i).getResponsavel() == null) {
                        ArrayList<Investigador> aux = new ArrayList<>();
                        Collections.sort(getGrupos().get(i).getInvestigadores());
                        for (Investigador in : getGrupos().get(i).getInvestigadores()) {
                            if (in.categoria().equals("Efetivo")) {
                                aux.add(in);
                            }
                        }
                        int index = getRandom().nextInt(aux.size());
                        Investigador res = aux.get(index);
                        getGrupos().get(i).setResponsavel(res);
                    }
                }
            }
        }
    }

    private GrupoInvestigacao procuraGrupo(String s) {
        GrupoInvestigacao g = null;
        for (GrupoInvestigacao grupo : getGrupos()) {
            if (s.toUpperCase().equals(grupo.getAcro())) {
                g = grupo;
            }
        }
        return g;
    }

    private Investigador procuraInvestigador(String s) {
        Investigador i = null;
        for (GrupoInvestigacao grupo : getGrupos()) {
            for (Investigador inv : grupo.getInvestigadores()) {
                if (s.toUpperCase().equals(inv.getNome().toUpperCase())) {
                    i = inv;
                }
            }
        }
        return i;
    }

    /**
     *
     * @param i investigador a verificar se já existe
     * @param lista de investigadores associado a um objeto (grupo ou CISUC)
     * @return true se o investigador não existe ou se a lista está vazia
     * @return false se o investigador já está existe na lista
     */
    private boolean checkInvestigadores(Investigador i, ArrayList<Investigador> lista) {
        if (lista.isEmpty()) {
            return true;
        } else {
            int j = 0;
            while (!lista.get(j).getNome().equals(i.getNome()) && j < lista.size() - 1) {
                j++;
            }
            return !lista.get(j).getNome().equals(i.getNome());
        }
    }

    /**
     *
     * @param p publicação a verificar
     * @param lista de publicações associadas a um objeto (grupo ou
     * investigador)
     * @return true se a publicação não existe ou se a lista está vazia
     * @return false se a publicação já existe na lista
     */
    private boolean checkPublicacoes(Publicacao p, ArrayList<Publicacao> lista) {
        if (lista.isEmpty()) {
            return true;
        } else {
            int i = 0;
            while (!lista.get(i).getTitulo().equals(p.getTitulo()) && i < lista.size() - 1) {
                i++;
            }
            return !lista.get(i).getTitulo().equals(p.getTitulo());
        }
    }

    /**
     * @return variável random
     */
    public Random getRandom() {
        return random;
    }

    /**
     * @param random variável random a ser usada
     */
    public void setRandom(Random random) {
        this.random = random;
    }

    /**
     *
     * @return arraylist com os grupos do CISUC e todas as informações
     * associadas
     */
    public ArrayList<GrupoInvestigacao> getGrupos() {
        return grupos;
    }

    /**
     *
     * @param g arraylist com os grupos do CISUC e todas as informações
     * associadas
     */
    public void setGrupos(ArrayList<GrupoInvestigacao> g) {
        this.grupos = g;
    }

    private void openFichGrupos() {
        File f = new File("grupos.txt");
        if (f.exists() && f.isFile()) { //Verifica se o ficheiro existe e se é um ficheiro de texto
            String[] info;
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    info = linha.split("#");
                    GrupoInvestigacao g = new GrupoInvestigacao(info[0], info[1]);
                    if (getGrupos().isEmpty()) {
                        addGrupos(g);
                    } else {
                        if (checkGrupos(g)) {
                            addGrupos(g);
                        } else {
                            br.readLine();
                        }
                    }
                }
                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        } else {
            System.out.println("Ficheiro não existe.");
        }
    }

    private void openFichInvestigadores() {
        File f = new File("investigadores.txt");
        if (f.exists() && f.isFile()) { //Verifica se o ficheiro existe e se é um ficheiro de texto
            String[] info;
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String linha;
                Investigador i = null;
                String responsavel = "";
                String orientador;
                while ((linha = br.readLine()) != null) {
                    info = linha.split(" # ");
                    switch (info[0].toUpperCase()) {
                        case "P":
                            //'P' e 'E' são tokens usados para verificar se os investigadores são efetivos (P == Efetivo) ou estudantes (E == Estudante)
                            int gabineteNum = Integer.parseInt(info[3]);
                            double telemovelNum = Double.parseDouble(info[4]);
                            i = new IEfetivo(info[1], info[2], gabineteNum, telemovelNum);
                            if (info.length == 7) {
                                responsavel = info[6];
                            } else {
                                responsavel = "";
                            }
                            break;
                        case "E":
                            int dia = Integer.parseInt(info[4].split("-")[0]);
                            int mes = Integer.parseInt(info[4].split("-")[1]);
                            int ano = Integer.parseInt(info[4].split("-")[2]);  //Mesmo que o ano introduzido seja 0 não é necessário filtrar esse valor, pois a verificação seguinte já o faz
                            Data d = new Data(dia, mes, ano);
                            Data hoje = new Data(MonthDay.now().getDayOfMonth(), MonthDay.now().getMonthValue(), Year.now().getValue());
                            checkMesDia(d);
                            // Apenas se consideram membros estudantes do CISUC se ainda estiverem na Universidade (data de entrega da tese futura)
                            if (!FutureOrPast(d, hoje)) {   //Caso a data da tese tenha expirado adiciona 6 meses à data atual (alterar o dia é irrelevante)
                                if ((hoje.getMes() + 6) % 12 == 0) {    //Evita que o mês fique com o valor 0
                                    d = new Data(dia, 12, Year.now().getValue() + 1);
                                } else if (hoje.getMes() < 6) {
                                    d = new Data(dia, (hoje.getMes() + 6) % 12, Year.now().getValue());
                                } else {
                                    d = new Data(dia, (hoje.getMes() + 6) % 12, Year.now().getValue() + 1);
                                }
                            }

                            if (info.length == 7) {
                                orientador = info[6];
                            } else {
                                orientador = "";
                            }
                            i = new IEstudante(info[1], info[2], info[3], d, orientador);
                            break;

                        default:
                            //Se nenhum dos token definidos for lido a linha é passada à frente e mostra a seguinte mensagem de erro
                            System.out.printf("%s não é um investigador efetivo nem estudante.\nImpossível adicionar ao CISUC.\n", info[1]);
                            br.readLine();
                            break;
                    }

                    String grupo = info[5];

                    GrupoInvestigacao g = null;
                    for (GrupoInvestigacao gr : getGrupos()) {
                        if (gr.getAcro().equals(grupo)) {
                            g = gr;
                        }
                    }
                    if (g != null) {    //O investigador só é aceite se estiver associado um grupo existente
                        if (checkInvestigadores(i, g.getInvestigadores())) {
                            g.addInvestigadores(i);
                            i.setGrupo(g);
                            if (responsavel.toUpperCase().equals("R") && g.getResponsavel() == null) {  //Se o investigador tiver o token de responsável do grupo
                                g.setResponsavel(i);                                                    //e ainda não houver nenhum definido, este é considerado responsável
                            }
                        } else {
                            br.readLine();
                        }
                    }
                }
                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        } else {
            System.out.println("Ficheiro não existe.");
        }
    }

    private void openFichPub() {
        File f = new File("publicacoes.txt");
        if (f.exists() && f.isFile()) { //Verifica se o ficheiro existe e se é um ficheiro de texto
            String[] info;
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String linha;
                Publicacao p = null;

                while ((linha = br.readLine()) != null) {
                    info = linha.split(" # ");
                    int anoPub = Integer.parseInt(info[4]);
                    if (anoPub > Year.now().getValue()) {
                        anoPub = Year.now().getValue();
                    } else if (anoPub < 1991) { //Ano de criação do CISUC. Logo, não podem exitir publicações anteriores a este ano
                        anoPub = 1991;
                    }
                    int dim = Integer.parseInt(info[5]);
                    String[] autores = {};
                    if (null != info[0].toUpperCase()) {
                        switch (info[0].toUpperCase()) {
                            case "AC": {    //Token para Artigos de Conferência
                                int dia = Integer.parseInt(info[8].split("-")[0]);
                                int mes = Integer.parseInt(info[8].split("-")[1]);
                                int ano = Integer.parseInt(info[8].split("-")[2]);
                                Data data = new Data(dia, mes, ano);
                                checkMesDia(data);

                                data = checkDataPub(data, anoPub);

                                p = new AConferencia(info[1], info[2], info[3], anoPub, dim, info[6], info[7], data);
                                if (info[9] != null) {
                                    autores = info[9].split(" and ");
                                }

                                break;
                            }
                            case "AR": {    //Token para Artigos de Revista
                                int dia = Integer.parseInt(info[8].split("-")[0]);
                                int mes = Integer.parseInt(info[8].split("-")[1]);
                                int ano = Integer.parseInt(info[8].split("-")[2]);
                                int numRev = Integer.parseInt(info[7]);
                                Data data = new Data(dia, mes, ano);
                                checkMesDia(data);

                                data = checkDataPub(data, anoPub);

                                p = new ARevista(info[1], info[2], info[3], anoPub, dim, info[6], numRev, data);
                                if (info[9] != null) {
                                    autores = info[9].split(" and ");
                                }
                                break;
                            }
                            case "L": {     //Token para Livros
                                p = new Livro(info[1], info[2], info[3], anoPub, dim, info[6], info[7]);
                                if (info[8] != null) {
                                    autores = info[8].split(" and ");
                                }
                                break;
                            }
                            case "LAC": {   //Token para Livros de Artigos de Conferência
                                int nArt = Integer.parseInt(info[9]);
                                p = new LivroConferencia(info[1], info[2], info[3], anoPub, dim, info[6], info[7], info[8], nArt);
                                if (info[10] != null) {
                                    autores = info[10].split(" and ");
                                }
                                break;
                            }
                            case "LC": {    //Token para Capítulos de Livros
                                int pagI = Integer.parseInt(info[9].split("--")[0]);
                                int pagF = Integer.parseInt(info[9].split("--")[1]);
                                p = new Capitulo(info[1], info[2], info[3], anoPub, dim, info[6], info[7], info[8], pagI, pagF);

                                if (info[10] != null) {
                                    autores = info[10].split(" and ");
                                }
                                break;
                            }
                            default:
                                break;
                        }
                    }

                    for (GrupoInvestigacao g : getGrupos()) {
                        for (Investigador i : g.getInvestigadores()) {
                            for (String a : autores) {
                                if (i.getNome().equals(a)) {    //Se o autor corresponder a um investigador do grupo
                                    p.addAutores(i);            //associa esse investigador como autor da publicação
                                    if (checkPublicacoes(p, i.getPubI())) { //Verifica se a publicação já não está associada ao investigador
                                        i.addPub(p);
                                    }
                                    if (checkPublicacoes(p, g.getPubG())) { //Verifica se a publicação já não está associada ao grupo, por meio de outro investigador do mesmo grupo
                                        g.addPubG(p);
                                    }
                                }
                            }
                        }
                    }
                }
                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        } else {
            System.out.println("Ficheiro não existe.");
        }
    }

    /**
     * Escreve o ficheiro de objetos com todas as informações necessárias para o
     * funcionamento do programa
     *
     * @return ArrayList com todas as informações dos grupos e todas as
     * informações associadas aos mesmos
     */
    public ArrayList<GrupoInvestigacao> writeFichObj() {
        //Escreve o ficheiro de objetos
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("cisuc.obj")))) {
            oos.writeObject(getGrupos());
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro objeto.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro objeto.");
        }
        return getGrupos();
    }

    private ArrayList<GrupoInvestigacao> readFichObj() {
        //Lê o ficheiro de objetos e carrega os objetos
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("cisuc.obj")))) {
            setGrupos((ArrayList<GrupoInvestigacao>) ois.readObject());
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a abrir ficheiro objeto.");
            System.out.println("Os dados serão carregados dos ficheiros de texto.");
            openFichGrupos();
            openFichInvestigadores();
            openFichPub();
        } catch (IOException ex) {
            System.out.println("Erro a ler ficheiro objeto.");
            System.out.println("Os dados serão carregados dos ficheiros de texto.");
            openFichGrupos();
            openFichInvestigadores();
            openFichPub();
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto.");
            System.out.println("Os dados serão carregados dos ficheiros de texto.");
            openFichGrupos();
            openFichInvestigadores();
            openFichPub();
        }
        return getGrupos();
    }

    /**
     * Mostra todas as informações do CISUC. Primeiro mostra o número de membros
     * e vê quantos existem por categoria. Depois mostra o número de publicações
     * dos últimos 5 anos e vê quantas existem de cada tipo.
     */
    public void infoCISUC() {
        System.out.println("----- INFORMAÇÕES DO CISUC -----");

        int efetivos = 0, estudantes = 0;
        int numR = 0, numC = 0, numL = 0, numCL = 0, numLAC = 0;
        ArrayList<Publicacao> tipo = new ArrayList<>(); //Auxiliar array
        for (GrupoInvestigacao g : getGrupos()) {
            efetivos += g.nInvPorCategoria()[0];
            estudantes += g.nInvPorCategoria()[1];
            for (Publicacao p : g.pubUltimos5Anos()) {
                if (checkPublicacoes(p, tipo)) {
                    tipo.add(p);
                }
            }
        }
        for (Publicacao t : tipo) {
            switch (t.tipo()) {
                case "Artigo de Revista":
                    numR++;
                    break;
                case "Artigo de Conferência":
                    numC++;
                    break;
                case "Livro":
                    numL++;
                    break;
                case "Capítulo de Livro":
                    numCL++;
                    break;
                default:
                    numLAC++;
                    break;
            }
        }
        System.out.printf("%d Membros:\n\t%d efetivo(s)\n\t%d estudante(s)\n", (efetivos + estudantes), efetivos, estudantes);
        System.out.printf("%d publicações nos últimos 5 anos:\n", tipo.size());
        System.out.printf("\t%d Artigo(s) de Revista\n\t%d Artigo(s) de Conferência\n\t%d Livro(s)\n\t%d Capítulo(s) de Livro(s)\n\t%d Livro(s) de Artigo(s) de Conferência\n", numR, numC, numL, numCL, numLAC);
    }

    /**
     * Mostra a lista de publicações dos últimos 5 anos do grupo escolhido pelo
     * utilizador. Organiza as publicações por ano, no caso deste coincidir
     * organiza por tipo e no caso de também coincidir organiza por impacto (A >
     * B > C).
     */
    public void listaPubGrupo() {
        System.out.print("Acrónimo do grupo de investigação: ");
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        GrupoInvestigacao g = procuraGrupo(s);

        if (g == null) {
            System.out.println("O grupo de investigação não existe.");
            return;
        }
        System.out.print(g.listaPublicacoes());
    }

    /**
     * Mostra o nome dos elementos do grupo escolhido pelo utilizador. Os
     * investigadores são separados por categoria.
     */
    public void listaMembrosGrupo() {
        System.out.print("Acrónimo do grupo de investigação: ");
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        GrupoInvestigacao g = procuraGrupo(s);

        if (g == null) {
            System.out.println("Esse grupo não existe.");
            return;
        }
        System.out.printf("----- LISTA DE MEMBROS DO GRUPO %s -----\n", g.getNome().toUpperCase());
        System.out.print(g.listaMembros());
    }

    /**
     * Mostra todas as publicações feitas pelo investigador escolhido pelo
     * utilizador. Verifica se esse investigador existe, no caso de existir
     * percorre as publicações e mostra-as agrupadas de 3 maneiras diferentes.
     * Cabe ao utilizador escolher de que maneira as quer agrupar. Agrupa por
     * ano (sendo que se for igual verifica o tipo e se este também for igual
     * verifica o impacto), por tipo (se for igual verifica o ano e se também
     * coincidir verifica o impacto) ou por impacto (lógica idêntica à dos
     * anteriores).
     */
    public void listaPubInvestigador() {
        System.out.print("Nome do investigador: ");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        Investigador i = procuraInvestigador(s);

        if (i == null) {
            System.out.println("O investigador não existe.");
            return;
        }
        i.listaPublicacoes();
    }

    /**
     * Mostra todas as informações de cada grupo do CISUC. Percorre a lista de
     * grupos do CISUC e posteriormente a lista de investigadores associados a
     * cada grupo, se não existir nenhum membro obviamente não existirá nenhuma
     * publicação. Verifica a categoria do membro e faz uma contagem de membros
     * por categoria. Mostra o número de publicações, agrupadas, por ano, tipo e
     * impacto. Quando existe apenas uma publicação é mostrado o ano, tipo e
     * impacto da mesma.
     */
    public void infoGrupos() {
        for (GrupoInvestigacao g : getGrupos()) {
            System.out.printf("----- %s -----\n", g.getNome().toUpperCase());
            int ef = g.nInvPorCategoria()[0];
            int es = g.nInvPorCategoria()[1];
            System.out.printf("%d Membros:\n\t%d efetivo(s)\n\t%d estudante(s)\n", g.getInvestigadores().size(), ef, es);

            if (g.getPubG().isEmpty()) {
                System.out.println("O grupo não tem publicações (desde sempre).");
            } else if (g.pubUltimos5Anos().isEmpty()) {
                System.out.println("O grupo não tem publicações nos últimos 5 anos.");
            } else if (g.pubUltimos5Anos().size() == 1) {
                System.out.printf("1 Publicação nos últimos 5 anos:\n");
                System.out.printf("\tAno: %d, Tipo: %s, Impacto: %s\n", g.pubUltimos5Anos().get(0).getAnoPub(), g.pubUltimos5Anos().get(0).tipo(), g.pubUltimos5Anos().get(0).calculaImpacto());

            } else {
                System.out.printf("%d Publicações nos últimos 5 anos:\n", g.pubUltimos5Anos().size());
                System.out.println("\tNº por Ano:");
                System.out.print(g.nPubPorAno());

                System.out.println("\tNº por Tipo:");
                System.out.print(g.nPubPorTipo());

                System.out.println("\tNº por Impacto:");
                System.out.print(g.nPubPorImpacto());
            }
            System.out.println();
        }
    }
}
