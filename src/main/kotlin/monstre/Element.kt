package org.example.monstre
/**
 * Classe représentant un élément avec :
 *
 * Deux propriétés dans le constructeur principal :
 *   - id : identifiant unique de l'élément (de type Int)
 *   - nom : nom de l'élément (de type String)
 *
 * Trois propriétés définies dans le corps de la classe :
 *   - forces : liste mutable contenant les noms des éléments contre lesquels cet élément est fort
 *   - faiblesses : liste mutable contenant les noms des éléments contre lesquels cet élément est faible
 *   - immunises : liste mutable contenant les noms des éléments contre lesquels cet élément est immunisé
 */
class Element (
    val id : Int,
    val nom : String,
){
    val forces = mutableListOf<Element>()
    val faiblesses = mutableListOf<Element>()
    val immunises = mutableListOf<Element>()

    /**
     * Détermine l'efficacité d'un élément courant contre un élément cible.
     *
     * Cette méthode compare l'élément cible avec les forces et faiblesses
     * de l'élément courant et retourne un multiplicateur d'efficacité.
     *
     * @param elementCible L'élément contre lequel l'efficacité est évaluée.
     * @return Un multiplicateur indiquant l'efficacité :
     *  2.0 si l'élément cible est dans les forces.
     *  0.5 si l'élément cible est dans les faiblesses.
     *  0.0 si l'élément cible est dans les éléments immunisés.
     *  1.0 si l'élément cible n'est ni une force ni une faiblesse.
     */
    fun efficaceContre(elementCible: Element): Double{
        when(elementCible) {
            forces -> return 2.0
            faiblesses -> return 0.5
            immunises -> return 0.0
            else -> return 1.0
        }
    }




}