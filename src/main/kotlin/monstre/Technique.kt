package org.example.monstre

import kotlin.math.max
import kotlin.random.Random

class Technique(
    val id : Int,
    val nom : String,
    val precision : Double,
    var multiplicateurDePuissance : Double,
    val estBuff : Boolean,
    val estDebuff : Boolean,
    val estSpecial : Boolean,
    val faireDegat : Boolean,
    val elementTechnique : Element
) {
    /**
     * Calcule si une technique touche sa cible en fonction de sa précision.
     *
     * @param technique La technique utilisée, contenant un attribut `precision` (valeur entre 1 et 100).
     * @return true si la technique touche (aléatoire ≤ précision), false sinon.
     */
    fun calculerPrecision(): Boolean {
        val nb = Random.nextInt(1, 101)
        return nb <= precision
    }

    /**
     * Calcule le bonus STAB (Same Type Attack Bonus) pour un monstre utilisant cette technique.
     *
     * Si le monstre possède le même élément que la technique, le bonus est de +0.15.
     * Sinon, le malus est de -0.15.
     * Le résultat ne peut jamais être inférieur à 0.1.
     *
     * @param monstre Le monstre qui utilise la technique.
     * @return Le multiplicateur de puissance ajusté (ne modifie pas la propriété de la technique).
     */
    fun calculBonusStab(individu: IndividuMonstre): Double{
        var bonus = -0.15
        if (elementTechnique in individu.espece.elements)bonus = 0.15
        return max((bonus + multiplicateurDePuissance),0.1)
    }

    /**
     * Applique l’effet principal de la technique :
     * - inflige des dégâts
     * - (TODO) applique des buffs/debuffs
     *
     * @param attaquant IndividuMonstre utilisateur de la technique
     * @param defenseur IndividuMonstre cible de la technique
     * @return Les dégâts infligés (0.0 si pas de dégâts)
     */
    fun effet(attaquant: IndividuMonstre, defenseur: IndividuMonstre): Double {
        if (estBuff) {
            // TODO : déclencher buff (futur Statut)
            return 0.0
        }

        if (estDebuff) {
            // TODO : déclencher debuff (futur Statut)
            return 0.0
        }

        if (!faireDegat) {
            return 0.0
        }

        val degatBase = if (estSpecial) {
            attaquant.attaqueSpe
        } else {
            attaquant.attaque
        }

        val multiplicateur = calculBonusStab(attaquant)
        var multiElement = elementTechnique.efficaceContre(defenseur.espece.elements[0])

        if (defenseur.espece.elements.size > 1) {
            multiElement *= elementTechnique.efficaceContre(defenseur.espece.elements[1])
        }

        val resultat = degatBase * multiplicateur * multiElement
        return resultat
    }
}