package src.cartes;

import src.erreurs.ErreurFichier;
import src.jeu.Uno;

import java.io.*;
import java.util.*;


public class PaquetDeCartes implements Iterable<Carte>
{
    private ArrayList<Carte> pdc;

    public PaquetDeCartes()
    {
        pdc = new ArrayList<>();
    }

    public Iterator<Carte> iterator()
    {
        return pdc.iterator();
    }

    public void ajouter(Carte... cartes)
    {
        pdc.addAll(Arrays.asList(cartes));
    }

    public void ajouter(PaquetDeCartes pdc)
    {
        for (int i = 0; i < pdc.getNombreDeCartes(); i++)
        {
            this.pdc.add(pdc.piocher());
        }
    }

    public int getNombreDeCartes()
    {
        return pdc.size();
    }

    public boolean estVide()
    {
        return pdc.isEmpty();
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Paquet :");
        for (int i = 0; i < pdc.size(); i++)
        {
            sb.append(System.lineSeparator()).append(i+1).append(" ").append(pdc.get(i));
        }
        return sb.toString();
    }

    public String getAffichage()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Paquet :");
        for (int i = 0; i < pdc.size(); i++)
        {
            sb.append(System.lineSeparator()).append(i+1).append(") ").append(pdc.get(i).getAffichage());
        }
        return sb.toString();
    }

    public void enlever(Carte carte)
    {
        assert pdc.contains(carte) : "Cette carte n'est pas dans le paquet";
        pdc.remove(carte);
    }

    public void mettreSurTop(Carte carte)
    {
        enlever(carte);
        ajouter(carte);
    }

    public Carte getSommet()
    {
        return pdc.get(pdc.size() - 1);
    }

    public Carte getCarte(int index)
    {
        return pdc.get(index);
    }

    public Carte piocher()
    {
        assert !estVide() : "Le paquet est vide";
        Carte carte = this.getSommet();
        pdc.remove(getNombreDeCartes() - 1);
        return carte;
    }

    public void melanger()
    {
        Collections.shuffle(pdc);
    }

    public int getValeur()
    {
        int sum = 0;
        for (Carte carte : pdc)
        {
            sum += carte.getValeur();
        }
        return sum;
    }

    public void retourner()
    {
        Collections.reverse(pdc);
    }

    public void ecrire(String nomDeFichier) throws ErreurFichier
    {
        try {

            FileWriter flot = new FileWriter(nomDeFichier) ;
            BufferedWriter flotFiltre = new BufferedWriter(flot) ;
            for(Carte c : pdc)
            {
                flotFiltre.write(c.getNom() + " " + c.getValeur() + " " + c.getCouleur());
                flotFiltre.newLine() ;
            }
            flotFiltre.close() ;
            if (!(new File(nomDeFichier).isFile()))
            {
                throw new ErreurFichier("Error happened while creating the file");
            }
        } catch (IOException e) {System.out.println(e.getMessage());}
    }

    public void lire(String nomDeFichier) throws ErreurFichier
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomDeFichier))){
            String line;
            Uno uno = new Uno();
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                String nom = words[0];
                int valeur = Integer.parseInt(words[1]);
                String couleur = words[2];

                switch (nom)
                {
                    case "Chiffre":
                        ajouter(new Chiffre(uno, valeur, Couleur.valueOf(couleur.toUpperCase())));
                        break;
                    case "Plus2":
                        ajouter(new Plus2(uno, Couleur.valueOf(couleur.toUpperCase())));
                        break;
                    case "ChangementDeSens":
                        ajouter(new ChangementDeSens(uno, Couleur.valueOf(couleur.toUpperCase())));
                        break;
                    case "PasseTonTour":
                        ajouter(new PasseTonTour(uno, Couleur.valueOf(couleur.toUpperCase())));
                        break;
                    case "Joker":
                        ajouter(new Joker(uno));
                        break;
                    case "Plus4":
                        ajouter(new Plus4(uno));
                        break;
                }
                if (!(new File(nomDeFichier).isFile())) {
                    throw new ErreurFichier("Error happened while creating the file");
                }
            }
        } catch (IOException e) {System.out.println(e.getMessage());}
    }
}
