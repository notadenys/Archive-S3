package reseau;

import java.util.ArrayList;

import reseau.adresses.Adresse;
import reseau.adresses.Octet;

/**
 * @author martine
 * @version 2022
 */
public class Message {

    private ArrayList<Octet> alo ;

    /**
     * Constructeur d'un message vide
     */
    public Message() {
        alo = new ArrayList<>() ;
    }

    /**
     * Constructeur de copie (copie profonde)
     * @param mess nombre d'éléments à copier
     * @exception AssertionError si mess est null
     */
    public Message(Message mess) {
        assert(mess != null):"mess est null" ;

        alo = new ArrayList<>() ;
        for(Octet occ : mess.alo) {
            alo.add(new Octet(occ)) ;
        }
    } 

    /**
     * Constructeur d'un message à partir des petits entiers (1 petit entier est codé sur un seul octet)
     * @param v des petits entiers qui constituent le message
     * @exception AssertionError si v est null
     */
    public Message(short... v) {
        assert v != null : "v est null";
        alo = new ArrayList<>();
        for(short sh : v) {
            alo.add(new Octet((int)sh));
        }
    }

    /**
     * Constructeur d'un message à partir des entiers (1 entier est codé sur 2 octets)
     * @param v des entiers qui constituent le message
     * @exception AssertionError si v est null
     */
    public Message(int... v) {
        assert v != null : "v est null";
        alo = new ArrayList<>();
        for(int sh : v){
            if (sh > 255){
                alo.add(new Octet(255));
                alo.add(new Octet(sh%255));
            }else{
                alo.add(new Octet(sh));
            }
        }
    }

    /**
     * Constructeur d'un message à partir de la chaîne de caractères
     * @param mot chaîne de caractères qui constitue le message
     * @exception AssertionError si mot est null
     */
    public Message(String mot) {
        assert mot != null : "Le string est null";
        String[] numbers = mot.split("\\.");
        alo = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++)
        {
            assert Integer.parseInt(numbers[i]) < 256 : "Le nombre est trop grand";
            alo.add(new Octet(Integer.parseInt(numbers[i])));
        }
    }

    /**
     * Constructeur d'un message à partir de l'adresse
     * @param adr adresse à placer dans le message
     * @exception AssertionError si adr est null
     */
    public Message(Adresse adr) {
        assert adr != null : "adr est null";
        alo = new ArrayList<>();

        for(int i = 0; i < adr.getNbreOctets(); i++)
        {
            alo.add(adr.getOctet(i));
        }
    }

    /**
    * Retourne le nombre d'octets
    * @return le nombre d'octets
    */
    public int size() {
       return alo.size() ;
    }

    /**
     * Ajouter un petit entier à la fin, entier &ge; 0
     * @param x entier à ajouter
     */
    public void ajouter(short x) {
        alo.add(new Octet((int)x));
    }

    /**
     * Ajouter un entier à la fin, entier &ge; 0
     * @param x entier à ajouter
     */
    public void ajouter(int x) {
        if (x > 255){
            alo.add(new Octet(255));
            alo.add(new Octet(x%255));
            }else{
                alo.add(new Octet(x));
            }
    }

    /**
     * Ajouter un octet à la fin
     * @param o octet à ajouter
     * @exception AssertionError si o est null
     */
    public void ajouter(Octet o) {
        assert(o != null):"o est null" ;
        alo.add(o);
    }

    /**
     * Concaténer un autre message
     * @param mess message à ajouter à la fin
     * @exception AssertionError si mess est null
     */
    public void ajouter(Message mess) {
        assert(mess != null):"mess est null" ;
        alo.addAll(mess.alo);
    }

    /**
     * Ajouter les octets d'une adresse à la fin
     * @param adr adresse à ajouter
     * @exception AssertionError si adr est null
     */
    public void ajouter(Adresse adr) {
        assert( adr != null):"adr est null" ;
    
        int nb = adr.size() ;
        for (int i = 0; i < nb; i++) {
            Octet o = adr.getOctet(i) ;
            alo.add(new Octet(o)) ;
            }
        } 
    
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < size(); i++)
        {
            string.append(alo.get(i).toString()).append(".");
        }
        string.deleteCharAt(string.length()-1);
        return string.toString() ;
    }

    /**
     * Extraire les 2 octets situés en index et index+1 pour en faire un entier
     * octets forts puis faibles (big endian)
     * @param index index du premier octet
     * @exception AssertionError si index ou index+1 n'est pas dans le domaine du tableau
     * @return un entier
     */
    public int extraireEntier(int index) {
        assert index+1 < size() : "index is out of range";
        return alo.get(index).getValue()+alo.get(index+1).getValue();
    }

    /**
     * Extraire les nbOctets premiers octets pour en faire une adresse
     * @param nbOctets nombre d'octets à extraire
     * @exception AssertionError si nbOctets &gt; longueur du message
     * @return une adresse
     */
    public Adresse extraireAdresse(int nbOctets) {
        assert nbOctets <= size() : "La taille est incorrect";
        Octet[] oct = new Octet[size()];
        for (int i = 0; i < nbOctets; i++)
        {
            oct[i] = alo.get(i);
        }
        return new Adresse(oct) ;
    }

    /**
     * Transformer le message en une suite de lettres, si possible 
     * @return null si l'un des octets n'est pas une lettre (maj ou min)
     */
    public String extraireChaine() {
        String str = new String();
        for (Octet oct : alo)
        {
            assert oct.estUneLettre() : "un des octets n'est pas une lettre";
            str += (char)oct.getValue();
        }
        return str ;
    }

    /**
     * Augmenter de i chaque octet dont la valeur est comprise entre bi et bs
     * @param i valeur à ajouter
     * @param bi borne inférieure
     * @param bs borne supérieure
     */
    public void augmenter(int i, int bi, int bs) {
        for (int j = 0; j < size(); j++)
        {
            if (alo.get(j).getValue() > bi && alo.get(j).getValue() < bs)
            {
                alo.set(j, new Octet(alo.get(j).getValue() + i));
            }
        }
    }

    /**
     * On enlève les i premiers éléments
     * @param i nombre d'éléments à enlever
     * @exception AssertionError si i n'est pas dans le domaine du tableau
     */
    public void supprimer(int i) {
        assert i < size() : "i n'est pas dans le domaine du tableau";
        alo.remove(0);
    }

    /**
     * On enlève les éléments entre debut et fin inclus
     * @param debut borne inférieure
     * @param fin borne supérieure
     * @exception AssertionError si on n'a pas 0 &le; debut &le; fin &lt; size()
     */
    public void supprimer(int debut, int fin) {
        assert debut >= 0 && fin >= debut && fin < size() : "les indexes sont incorrectes";
        for(int i = debut ; i <= fin ; i++){
            alo.remove(0) ;
        }
    }
}
