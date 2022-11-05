import java.util.*;
import java.lang.*;

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

} // fin MasterMindBase