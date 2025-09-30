package org.example.monstre

import kotlin.random.Random

class Technique(
    val id : Int,
    val nom : String,
    val precision : Double,
    var multiplicateurDePuissance : Double,
    val estBuff : Boolean,
    val estDebuff : Boolean,
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
    fun calculBonusStab(monstre: IndividuMonstre): Double {
        val bonus = if (elementTechnique in monstre.espece.elements) {
            0.15
        } else {
            -0.15
        }
        val resultat = multiplicateurDePuissance + bonus

        return if (resultat < 0.1) 0.1 else resultat
    }
}