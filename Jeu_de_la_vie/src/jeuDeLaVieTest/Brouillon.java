package jeuDeLaVieTest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//retourne la taille de la queue.
public int calculAsymptotique(LinkedList<Cell> liste, int max){
    LinkedList<Cell> c1 = liste.clone();
    LinkedList<Cell> c2 = liste.clone();
    LinkedList<Cell> c3 = liste.clone();
    int i = 0;

    while (i<=max){
        if (c1.isEmpty()){
            return -i;//mort
        }else if (c2.isEmpty(){
            return -2*i;//mort
        }else {
            c1 = nextGameBoard(c1);
            c3 = nextGameBoard(c2);
            c2 = nextGameBoard(c3);
            i++;
            if (c1.equals(c2)||c1.equals(c3)){
                return i;//periodique (possiblement stable)
            }else if (c1.decale(c2)!=[0,0]) {
                return max + i;//decale
            }
        }
    }
    return 2*max+1;//inconnu
}

public int[] decale(LinkedList<Cell> c1, LinkedList<Cell> c2){
    int[] tab = {0,0};
    Link<Cell> m1 = c1.getHead();
    Link<Cell> m2 = c2.getHead();
    int decaleX = m1.c.x - m2.c.x;
    int decaleY = m1.c.y - m2.c.y;
    while (m1 != null && m2 != null){
        if (m1.c.x - m2.c.x != decaleX || m1.c.y - m2.c.y != decaleY){
            return tab;
        }
        m1 = m1.next;
        m2 = m2.next;
    }
    if (m1 == null && m2 == null){
        tab[0] = decaleX;
        tab[1] = decaleY;
        return tab;
    }else{
        return tab;
    }
}

public int[] periode(LinkedList<Cell> liste, int queue, boolean dec){
    int[] tab = {0,0,0};
    LinkedList<Cell> c1 = liste.clone();
    for (int i = 0; i<queue; i++){
        c1=nextGameBoard(c1);
    }
    LinkedList<Cell> c2 = nextGameBoard(c1.clone());
    int i = 0;
    if (!dec){
        while (!c1.equals(c2)){
            c2=nextGameBoard(c2);
            i++;
        }
        tab[0] = i;
        return tab;
    }else{
        while(c1.decale(c2)==[0,0]){
        c2=nextGameBoard(c2);
        i++;
        }
        t = c1.decale(c2);
        tab[0] = i;
        tab[1] = t[0];
        tab[2] = t[1];à
        return tab;
    }
}

public LinkedList<Cell> loader(String filename){
    LinkedList<Cell> liste = new LinkedList<Cell>();
    boolean lfl = false; //stand for look for letter
    boolean lfbol = false; //stand for look for block origin ligne
    boolean lfboc = false; //stand for look for block origin colonne
    boolean lfe = false; // stand for look for element
    boolean arc = false; // stand for allow return carriage (permet d'éviter le saut de ligne si 2 \n d'affilé)
    int l = 0; // num de la ligne lors de la recherche d'élément
    int c = 0; // num de la colone lors de la recherche d'élément
    int lo = 0; // ligne d'origine d'un bloc
    int co = 0; // colonne d'origine d'un bloc
    int minus = 1; // permet de repérer si le numéro de la ligne, ou de la colonne est négatif.
    File f = new File(filename);
    if (f.exists()&&f.isFile()){
        FileInputStream fInput = new FileInputStream(f);
    }
    try {
        fis = new FileInputStream(new File("test.txt"));
        int n = 0;
        while ((n = fis.read()) >= 0) {
            if((char)n == '#'){
                lfl = true;
                lfbo = false;
                lfe = false;
                arc = false;
                l = 0;
                c = 0;
                lo = 0;
                co = 0;
            } else if(lfl){
                if((char)n == 'P'){
                    lfl = false;
                    lfbol = true;
                }else{
                    lfl = false;
                }
            } else if(lfbol){
                if((char)n == '-') {
                    minus*=-1;
                }else if((char)n == '0' ||(char)n == '1' ||(char)n == '2' ||(char)n == '3' ||(char)n == '4' ||(char)n == '5' ||(char)n == '6' ||(char)n == '7' ||(char)n == '8' ||(char)n == '9'){
                    lo=10*lo+Integer.parseInt(String.valueOf((char)n));
                }else if((char)n = ' '){
                    lfbol = false;
                    lfboc = true;
                    lo *= minus;
                    l = lo;
                    minus = 1;
                }
            } else if(lfboc) {
                if((char)n == '-') {
                    minus*=-1;
                }else if((char)n == '0' ||(char)n == '1' ||(char)n == '2' ||(char)n == '3' ||(char)n == '4' ||(char)n == '5' ||(char)n == '6' ||(char)n == '7' ||(char)n == '8' ||(char)n == '9'){
                    co=10*co+Integer.parseInt(String.valueOf((char)n));
                }else if((char)n = '\n'){
                    lfboc = false;
                    lfe = true;
                    co *= minus
                    c = co;
                    minus = 1;
                }
            } else if (lfe) {
                if((char)n=='.'){
                    arc = true;
                    c=c+1;
                }else if((char)n =='*'){
                    arc = true;
                    liste.add(new Cell(l,c));
                    c=c+1
                }else if((char)n == '\n'){
                    if (arc){
                        arc=false;
                        c=co;
                        l=l+1;
                    }
                }
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (fis != null)
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}