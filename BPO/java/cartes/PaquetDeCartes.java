package cartes;

import java.util.ArrayList;
import java.util.Random;


public class PaquetDeCartes
{
    private ArrayList<Carte> pdc;

    public PaquetDeCartes()
    {
        pdc = new ArrayList<Carte>();
    }

    public void ajouter(Carte... cartes)
    {
        for (Carte carte : cartes)
        {
            pdc.add(carte);
        }
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
        for (Carte carte : pdc)
        {
            sb.append(System.lineSeparator()).append(carte.toString());
        }
        return sb.toString();
    }

    public void enlever(Carte carte)
    {
        assert pdc.remove(carte) : "Cette carte n'est pas dans le paquet";
    }

    public Carte getSommet()
    {
        return pdc.get(pdc.size() - 1);
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
        Random gen = new Random();
        for (int i = 0; i < getNombreDeCartes(); i++)
        {
            pdc.add(gen.nextInt(getNombreDeCartes()), piocher());
        }
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
        for (int i = getNombreDeCartes(); i > 0; i--)
        {
            pdc.add(getNombreDeCartes()-i, piocher());
        }
    }
}
