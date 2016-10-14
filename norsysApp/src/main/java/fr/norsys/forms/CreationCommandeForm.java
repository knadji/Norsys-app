package fr.norsys.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class CreationCommandeForm {

    private static final String       CHAMP_MONTANT          = "montantCommande";
    private static final String       CHAMP_MODE_PAIEMENT    = "modePaiementCommande";
    private static final String       CHAMP_STATUT_PAIEMENT  = "statutPaiementCommande";
    private static final String       CHAMP_MODE_LIVRAISON   = "modeLivraisonCommande";
    private static final String       CHAMP_STATUT_LIVRAISON = "statutLivraisonCommande";

    private final Map<String, String> erreurs                = new HashMap<String, String>();
    private String                    resultat;

    private String                    montantCommande;
    private final String              modePaiementCommande;
    private final String              statutPaiementCommande;
    private final String              modeLivraisonCommande;
    private final String              statutLivraisonCommande;

    public CreationCommandeForm( final HttpServletRequest request ) {

        // Recuperation des informations formulaire
        montantCommande = request.getParameter( CHAMP_MONTANT );
        modePaiementCommande = request.getParameter( CHAMP_MODE_PAIEMENT );
        statutPaiementCommande = request.getParameter( CHAMP_STATUT_PAIEMENT );
        modeLivraisonCommande = request.getParameter( CHAMP_MODE_LIVRAISON );
        statutLivraisonCommande = request.getParameter( CHAMP_STATUT_LIVRAISON );

        // Verification des donnees du formulaire
        virifierInfoCommande();

        if ( erreurs.isEmpty() ) {
            resultat = "Succ�s de la cr�ation du client.";
        } else {
            resultat = "Echec de la cr�ation du client.";
        }
    }

    private void virifierInfoCommande() {

        // Verification du Montant
        try {
            if ( !( montantCommande != null && Double.parseDouble( montantCommande ) > 0 ) ) {
                setErreur( CHAMP_MONTANT, "Le montant doit �tre un nombre entier positif." );
            }
        } catch ( final NumberFormatException e ) {
            montantCommande = String.valueOf( -1 );
            setErreur( CHAMP_MONTANT, "Le montant doit �tre un nombre." );
        }

        // Verification du mode de paiement
        if ( !( modePaiementCommande != null && modePaiementCommande.length() > 2 ) ) {
            setErreur( CHAMP_MODE_PAIEMENT, "Le mode de paiement doit contenir au moins 3 caract�res" );
        }

        // Verification du statut de paiement
        if ( !( statutPaiementCommande != null && statutPaiementCommande.length() > 2 ) ) {
            setErreur( CHAMP_STATUT_PAIEMENT, "Le statut de paiement doit contenir au moins 3 caract�res" );
        }

        // Verification du mode de livraison commande
        if ( !( modeLivraisonCommande != null && modeLivraisonCommande.length() > 2 ) ) {
            setErreur( CHAMP_MODE_LIVRAISON, "Le mode de livraison doit contenir au moins 3 caract�res" );
        }

        // Verification du statut de livraison commande
        if ( !( statutLivraisonCommande != null && statutLivraisonCommande.length() > 2 ) ) {
            setErreur( CHAMP_STATUT_LIVRAISON, "Le statut de livraison doit contenir au moins 3 caract�res" );
        }
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    private void setErreur( final String champ, final String message ) {
        erreurs.put( champ, message );
    }

    public String getMontantCommande() {
        return montantCommande;
    }

    public String getModePaiementCommande() {
        return modePaiementCommande;
    }

    public String getStatutPaiementCommande() {
        return statutPaiementCommande;
    }

    public String getModeLivraisonCommande() {
        return modeLivraisonCommande;
    }

    public String getStatutLivraisonCommande() {
        return statutLivraisonCommande;
    }
}
