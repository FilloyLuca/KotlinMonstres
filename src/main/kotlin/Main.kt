package org.example

import org.example.dresseur.Entraineur
import org.example.item.Badge
import org.example.item.MonsterKube
import org.example.jeu.CombatMonstre
import org.example.jeu.Partie
import org.example.monde.Zone
import org.example.monstre.Element
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre
import kotlin.math.E

var joueur = Entraineur(1,"Sacha",100)
var rival = Entraineur(2,"Regis",200)

var especeSpringLeaf = EspeceMonstre(
    1,
    "Springleaf",
    "Graine",
    60,
    9,
    11,
    10,
    12,
    50,
    34.0,
    6.5,
    9.0,
    8.0,
    7.0,
    30.0,
    "Petit monstre espiègle rond comme une graine, adore le soleil.",
    "Sa feuille sur la tête indique son humeur.",
    "Curieux, amical, timide"
)
var especeFlamkip = EspeceMonstre(
    4,
    "Flamkip",
    "Animal ",
    50,
    8,
    13,
    13,
    16,
    7,
    22.0,
    10.0,
    5.5,
    9.5,
    9.5,
    6.5,
    "Petit animal entouré de flammes, déteste le froid.",
    "Sa flamme change d’intensité selon son énergie.",
    "Impulsif, joueur, loyal"
)
var especeAquamy = EspeceMonstre(
    7,
    "Aquamy",
    "Meteo",
    14,
    10,
    11,
    9,
    14,
    14,
    7.0,
    9.0,
    10.0,
    7.5,
    12.0,
    12.0,
    "Créature vaporeuse semblable à un nuage, produit des gouttes pures.",
    "Fait baisser la température en s’endormant.",
    "Calme, rêveur, mystérieux"
)
var especeLaoumi = EspeceMonstre(
    8,
    "Laoumi",
    "Animal",
    11,
    10,
    9,
    8,
    11,
    23,
    11.0,
    8.0,
    7.0,
    6.0,
    11.5,
    23.0,
    "Petit ourson au pelage soyeux, aime se tenir debout.",
    "Son grognement est mignon mais il protège ses amis.",
    "Affectueux, protecteur, gourmand"
)
var especeBugsyface = EspeceMonstre(
    10,
    "Bugsyface",
    "Insecte",
    10,
    13,
    8,
    7,
    13,
    45,
    7.0,
    11.0,
    6.5,
    8.0,
    11.5,
    21.0,
    "Insecte à carapace luisante, se déplace par bonds et vibre des antennes.",
    "Sa carapace devient plus dure après chaque mue.",
    "Travailleur, sociable, infatigable"
)
var especeGalum = EspeceMonstre(
    13,
    "Galum",
    "Minéral",
    12,
    15,
    6,
    8,
    12,
    55,
    9.0,
    13.0,
    4.0,
    6.5,
    10.5,
    13.0,
    "Golem ancien de pierre, yeux lumineux en garde.",
    "Peut rester immobile des heures comme une statue.",
    "Sérieux, stoïque, fiable"
)

var route1 = Zone(
    1,
    "Route 1",
    1500,
    mutableListOf(especeSpringLeaf)
)
var route2 = Zone(
    2,
    "Route 2",
    2000,
    mutableListOf(especeSpringLeaf,especeAquamy)
)

val monstre1 = IndividuMonstre(
    1,
    "springleaf",
    especeSpringLeaf,
    null,
    1500.0
)
val monstre2 = IndividuMonstre(
    2,
    "flamkip",
    especeFlamkip,
    null,
    1500.0
)
val monstre3 = IndividuMonstre(
    3,
    "aquamy",
    especeAquamy,
    null,
    1500.0
)
val monstre4 = IndividuMonstre(
    8,
    "Laoumi",
    especeLaoumi,
    null,
    1500.0
)
val monstre5 = IndividuMonstre(
    10,
    "Bugsyface",
    especeBugsyface,
    null,
    1500.0
)
val monstre6 = IndividuMonstre(
    13,
    "Galum",
    especeGalum,
    null,
    1500.0
)

val monsterKube1 = MonsterKube(1,"Kube","Kube de monstre",0.5)

fun nouvellePartie(): Partie {
    println("Bienvenue dans le monde des Monstres !")
    println("Comment t'appelles-tu ?")
    print("> ")

    val saisie = readLine()?.trim().orEmpty()
    if (saisie.isNotEmpty()) {
        joueur.nom = saisie
    } else {
        println("Aucun nom saisi, le nom du joueur reste: ${joueur.nom}")
    }

    println("Enchanté, ${joueur.nom} ! Bonne aventure.")
    return Partie(1, joueur, zone = route1)
}

var plante = Element(1,"Plante")
var feu = Element(2,"Feu")
var eau = Element(3,"Eau")
var insecte = Element(4,"Insecte")
var roche = Element(5,"Roche")
var normal = Element(6,"Normal")

fun main() {
////Sprint1
route1.zoneSuivante = route2
route2.zonePrecedente = route1
joueur.sacAItems.add(monsterKube1)


val partie = nouvellePartie()
partie.choisirStarter()
partie.jouer()

////Sprint2
// 🔥 Feu
feu.forces.addAll(listOf(plante, insecte))
feu.faiblesses.addAll(listOf(eau, roche,feu))

// 🌱 Plante
plante.forces.addAll(listOf(eau, roche))
plante.faiblesses.addAll(listOf(feu, insecte))

// 💧 Eau
eau.forces.addAll(listOf(feu, roche))
eau.faiblesses.addAll(listOf(plante))

// 🐞 Insecte
insecte.forces.addAll(listOf(plante))
insecte.faiblesses.addAll(listOf(feu, roche))

// 🪨 Roche
roche.forces.addAll(listOf(feu, insecte))
roche.faiblesses.addAll(listOf(eau, plante))

// ⚪ Normal
normal.faiblesses.add(roche)

}