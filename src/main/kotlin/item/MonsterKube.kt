package org.example.item

import org.example.joueur
import org.example.monstre.IndividuMonstre
import kotlin.uuid.Uuid.Companion.random

class MonsterKube(
    id : Int,
    nom : String,
    description : String,
    var chanceCapture : Double
) : Item(id,nom,description), Utilisable{
//    override fun utiliser(cible: IndividuMonstre): Boolean {
//        println("Vous lancez le Monster Kube !")
//        if (cible.entraineur != null){
//            println("Le monstre ne peut pas être capturé.")
//        }
//            val ratioVie = cible.pv / cible.pvMax
//            var chanceEffective = chanceCapture * (1.5 - ratioVie)
//            chanceEffective = 5.0
//            var nbAleatoire =(0..100).random()
//        if(nbAleatoire > chanceEffective){
//            println("Presque! Le kube n'a pas pu capturer le monstre !")
//            return false
//        }
//        println("Le monstre est capturé !")
//        cible.renommer(cible)
//        if (joueur.equipeMonstre.size >= 6){
//            joueur.equipeMonstre.add(cible)
//            return true
//        }else {
//            println("L'équipe est pleine, le monstre est envoyé dans la boîte.")
//        }
//        return true
//    }

    override fun utiliser(cible: IndividuMonstre): Boolean {
        println("\nVous lancez la Monster Kube !")
        if (cible.entraineur != null) println("\nLe monstre ne peut pas être capturé.")
        else {
            val ratioVie = cible.pv/cible.pvMax
            var chanceEffective  = chanceCapture * (1.5 - ratioVie)
            if (chanceEffective < 5) chanceEffective = 5.0
            val nbAleatoire = (0..100).random()
            if (nbAleatoire < chanceEffective){
                println("\nLe monstre est capturé !")
                cible.renommer(cible).toString()
                if (joueur.equipeMonstre.size <= 6) joueur.equipeMonstre.add(cible)
                cible.entraineur = joueur
                return true
            }
            else {
                println("\nPresque ! Le Kube n'a pas pu capturer le monstre !")
            }
        }
        return false
    }
}

