package org.example.monde
import org.example.jeu.CombatMonstre
import org.example.joueur
import kotlin.random.Random


import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre

/**
 * Représente une zone du monde dans laquelle le joueur peut se déplacer et rencontrer des monstres.
 *
 * Une zone possède un identifiant, un nom, une valeur d'expérience indicative,
 * une liste d'espèces de monstres qu'on peut y rencontrer, ainsi que des liens vers les zones
 * précédente et suivante pour permettre la navigation.
 *
 * @property id Identifiant unique de la zone.
 * @property nom Nom de la zone (ex : Forêt de Lune, Grotte Obscure...).
 * @property expZone Niveau ou valeur d’expérience recommandée pour cette zone.
 * @property especeMonstres Liste des espèces de monstres disponibles dans cette zone.
 * @property zoneSuivante Référence vers la zone suivante (ou null si aucune).
 * @property zonePrecedente Référence vers la zone précédente (ou null si aucune).
 *
 * @see EspeceMonstre pour les espèces de monstres référencées.
 * @see genereMonstre pour génerer un monstre sauvage dans cette zone.
 * @see rencontreMonstre pour génerer un monstre sauvage et lancer un combat avec celui-ci.
 */
class Zone(
    var id : Int,
    var nom : String,
    var expZone : Int,
    var especeMonstres : MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante : Zone? = null,
    var zonePrecedente : Zone? = null
) {
    /**
     * Génère un individu monstre appartenant à une espèce choisie aléatoirement parmi celles disponibles dans la zone.
     * L'expérience du monstre est calculée comme l'expérience de la zone ajustée de ±20%.
     *
     * @return Un nouvel IndividuMonstre sauvage, sans entraîneur, avec un id à 0 et le nom de son espèce.
     * @throws IllegalArgumentException si la liste des espèces de monstres est vide.
     */
    fun genereMonstre(): IndividuMonstre{
        require(especeMonstres.isNotEmpty()) { "Aucune espèce disponible dans cette zone." }
        // Choisir une espèce au hasard
        val especeAleatoire : EspeceMonstre = especeMonstres.random()

        // Générer une variation de +/- 20%
        val variation : Double = Random.nextDouble(0.8, 1.2)
        val experienceMonstre : Double = expZone * variation

        // Retourner le monstre généré (monstre sauvage sans entraineur, id 0 et nom de l'espèce par défaut)
        return IndividuMonstre(
            id = 0,
            nom = especeAleatoire.nom,
            espece = especeAleatoire,
            entraineur = null,
            expInit = experienceMonstre
        )
    }

    /**
     * Gère la rencontre avec un monstre sauvage.
     *
     * Cette méthode génère un monstre sauvage et cherche le premier monstre vivant dans l'équipe du joueur.
     * Si aucun monstre n'est disponible pour combattre, elle affiche un message d'erreur.
     * Sinon, elle lance un combat entre le premier monstre du joueur et le monstre sauvage généré.
     */
    fun rencontreMonstre(){
        genereMonstre()
        val monstreSauvage = genereMonstre()
        var premierMonstre: IndividuMonstre? = null

        for (monstre in joueur.equipeMonstre){
            if (monstre.pv > 0) {
                premierMonstre = monstre
                break
            }
        }
        if (premierMonstre == null) {
            println("Aucun monstre disponible pour combattre !")
            return
        }

        val combat = CombatMonstre(premierMonstre,monstreSauvage)
        combat.lanceCombat()
    }
}