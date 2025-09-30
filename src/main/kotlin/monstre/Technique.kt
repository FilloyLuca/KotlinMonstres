package org.example.monstre

import kotlin.random.Random

class Technique(
    val id : Int,
    val nom : String,
    val precision : Double,
    val multiplicateurDePuissance : Double,
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
    fun calculerPrecision(technique : Technique) : Boolean{
        val nb = Random.nextInt(1, 101)  // 101 est exclusif, donc génère de 1 à 100
        if (nb <= technique.precision) return true
        else return false
    }
}