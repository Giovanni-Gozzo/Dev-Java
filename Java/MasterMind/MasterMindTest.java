import java.util.Scanner;

public class MasterMindTest {

    //.........................................................................
    // OUTILS DE BASE
    //.........................................................................
    
    // fonctions classiques sur les tableaux

    /** pré-requis : nb >= 0
	résultat : un tableau de nb entiers égaux à val
    */
    public static int[] initTab(int nb, int val){
        int[] tab=new int[nb];
        for(int i=0;i<nb;i++){
            tab[i]=val;
        }
        return tab;
    }

    public static void afficherTableau(int[] T) {
        for (int i = 0; i < T.length; i++) {
            System.out.println(T[i]);
        }
    }
    /**
     * Donnée : 1 tableau
     * Résultat :renvoie rien mais affiche l'entierete d'un tableau en ligne.
     */

    /** pré-requis : aucun
     résultat : une copie de tab
     */
    public static int[] copieTab(int[] tab){
        int[] tabcopie=new int[tab.length];
        for(int i=0;i<tab.length;i++){
            tabcopie[i]=tab[i];
        }
        return tabcopie;
    }
    public static String listElem(char[] t){
        String list="";
        for (int i = 0; i < t.length; i++) {
            if(i==0){
                list+="(";
               list+=t[i];
                list+=")";
            }
            else{
                list+=",(";
                list+=t[i];
                list+=")";
            }
        }
        return list;
    }
    /** pré-requis : aucun
     résultat : le plus grand indice d'une case de t contenant c s'il existe, -1 sinon
     */
    public static int plusGrandIndice(char[] t, char c){
        int indice=-1;
        for(int i=0;i<t.length;i++){
            if(t[i]==c){
                indice=i;
            }
        }
        return indice;
    }
    /** pré-requis : aucun
     résultat : vrai ssi c est un élément de t
     stratégie : utilise la fonction plusGrandIndice
     */
    public static boolean estPresent(char[] t, char c){
        boolean r=false;
        if(plusGrandIndice(t,c)>-1){
            r=true;
        }
        return r;
    }

    /** pré-requis : aucun
     action : affiche un doublon et 2 de ses indices dans t s'il en existe
     résultat : vrai ssi les éléments de t sont différents
     stratégie : utilise la fonction plusGrandIndice
     */
    public static boolean elemDiff(char[] t){
        boolean r=true;
        for(int i=0;i<t.length;i++){
            if(plusGrandIndice(t,t[i])!=i){
                r=false;
            }
        }
        return r;
    }
    /** pré-requis : t1.length = t2.length
     résultat : vrai ssi t1 et t2 contiennent la même suite d'entiers
     */
    public static boolean sontEgaux(int[] t1, int[] t2){
        boolean r=true;
        for(int i=0;i< t1.length;i++){
            if(t1[i]!=t2[i]){
                r=false;
            }
        }


        return r;
    }

    //______________________________________________

    // Dans toutes les fonctions suivantes, on a comme pré-requis implicites sur les paramètres lgCode, nbCouleurs et tabCouleurs :
    // lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0 et les éléments de tabCouleurs sont différents

    // fonctions sur les codes pour la manche Humain

    /** pré-requis : aucun
     résultat : un tableau de lgCode entiers choisis aléatoirement entre 0 et nbCouleurs-1
     */
    public static int[] codeAleat(int lgCode, int nbCouleurs){
        int[] codeA =new int[lgCode];
        int n=0;
        for(int i=0;i<lgCode;i++) {
            n=(int)(Math.random() * nbCouleurs-1);
            codeA[i]=n;
        }
        return codeA;
    }

    /** pré-requis : aucun
     action : si codMot n'est pas correct, affiche pourquoi
     résultat : vrai ssi codMot est correct, c'est-à-dire de longueur lgCode et ne contenant que des éléments de tabCouleurs
     */
    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs){
        boolean bool=true;
        int j=0;
        char[] charat=new char[codMot.length()];
        for(int i = 0;i<codMot.length();i++){
            charat[i] = codMot.charAt(i);
        }
        while(bool && j<tabCouleurs.length){
            bool=estPresent(tabCouleurs, charat[j]);
            j++;
        }
        if(bool==false){
            System.out.println("un caractere dans codMot ne correspond pas a tabCouleur codMot doit avoir seulement comme caractere les suivant :");
            afficherTableau.afficherTableauChar(tabCouleurs);
            System.out.println();
        }
        if (codMot.length()!=lgCode){
            bool=false;
            System.out.println("la longueur de codMot est différente de lgCode, elle doit avoir "+lgCode+" caracteres.");
        }
        return bool;
    }
    /** pré-requis : les caractères de codMot sont des éléments de tabCouleurs
     résultat : le code codMot sous forme de tableau d'entiers en remplaçant chaque couleur par son indice dans tabCouleurs
     */
    public static int[] motVersEntiers(String codMot, char[] tabCouleurs){
        int[] indicet=new int[codMot.length()];
        char[] charat=new char[codMot.length()];
        for(int i = 0;i<codMot.length();i++){
            charat[i] = codMot.charAt(i);
        }
        for(int i = 0;i<codMot.length();i++){
            indicet[i] = plusGrandIndice(tabCouleurs,charat[i]);
        }
        return indicet;
    }

    /** pré-requis : aucun
     action : demande au joueur humain de saisir la (nbCoups + 1)ème proposition de code sous forme de mot, avec re-saisie éventuelle jusqu'à ce
     qu'elle soit correcte (le paramètre nbCoups ne sert que pour l'affichage)
     résultat : le code saisi sous forme de tableau d'entiers
     */
    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs){
        nbCoups+=1;
        Scanner scanner=new Scanner(System.in);
        System.out.println("écris ta proposition "+nbCoups+".");
        String mot=scanner.nextLine();
        while(codeCorrect(mot,lgCode,tabCouleurs)!=true){
            mot=scanner.nextLine();
        }
        System.out.println("la proposition "+nbCoups+" est "+mot);
        return motVersEntiers(mot,tabCouleurs);
    }
    /** pré-requis : cod1.length = cod2.length
     résultat : le nombre d'éléments communs de cod1 et cod2 se trouvant au même indice
     Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 1 (le "0" à l'indice 3)
     */
    public static int nbBienPlaces(int[] cod1,int[] cod2){
        int r=0;
        for(int i=0;i<cod1.length;i++){
            if(cod1[i]==cod2[i]){
                r++;
            }
        }
        return r;
    }

    /** pré-requis : les éléments de cod sont des entiers de 0 à nbCouleurs-1
     résultat : un tableau de longueur nbCouleurs contenant à chaque indice i le nombre d'occurrences de i dans cod
     Par exemple, si cod = (1,0,2,0) et nbCouleurs = 6 la fonction retourne (2,1,1,0,0,0)
     */
    public static int[] tabFrequence(int[] cod, int nbCouleurs){
        int[] tab=new int[nbCouleurs];
        for(int i=0;i<cod.length;i++){
            tab[cod[i]]+=1;
        }
        return tab;
    }

    /** pré-requis : les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
     résultat : le nombre d'éléments communs de cod1 et cod2, indépendamment de leur position
     Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 3 (2 "0" et 1 "1")
     */
    public static int nbCommuns(int[] cod1,int[] cod2, int nbCouleurs){
        int r=0;
        int []tab1=MasterMindTest.tabFrequence(cod1,nbCouleurs);
        int []tab2=MasterMindTest.tabFrequence(cod2,nbCouleurs);
        for(int i=0;i<nbCouleurs;i++){
            if(tab1[i]==tab2[i]){
                r+=tab1[i];
            }
            else-if(tab1[i]<tab2[i]){
                r+=tab1[i];
            }
            else{
                r+=tab2[i];
            }
        }
        return r;
    }

} // fin MasterMindBase

