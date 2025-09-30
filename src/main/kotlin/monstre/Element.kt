package org.example.monstre

class Element (
    val id : Int,
    val nom : String,
){
    val forces = mutableListOf<String>()
    val faiblesses = mutableListOf<String>()
    val immunises = mutableListOf<String>()

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