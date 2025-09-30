package org.example.monstre

import java.io.File

/**
 * Représente une espèce de monstre avec ses caractéristiques de base et ses modificateurs.
 *
 * Chaque monstre possède un ensemble de statistiques, des modificateurs de croissance,
 * ainsi que des descriptions textuelles pour enrichir sa personnalité et son comportement.
 *
 * @property id Identifiant unique du monstre.
 * @property nom Nom de l'espèce du monstre.
 * @property type Type du monstre (ex: Graine, Animal, Meteo, etc.).
 *
 * @property baseAttaque Attaque physique de base.
 * @property baseDefense Défense physique de base.
 * @property baseVitesse Vitesse de base, utilisée pour déterminer l'ordre d'action.
 * @property baseAttaqueSpe Attaque spéciale de base (par exemple : magie, feu, eau...).
 * @property baseDefenseSpe Défense spéciale de base.
 * @property basePv Points de vie de base.
 *
 * @property modAttaque Modificateur de croissance pour l'attaque physique.
 * @property modDefense Modificateur de croissance pour la défense physique.
 * @property modVitesse Modificateur de croissance pour la vitesse.
 * @property modAttaqueSpe Modificateur pour l'attaque spéciale.
 * @property modDefenseSpe Modificateur pour la défense spéciale.
 * @property modPv Modificateur pour les points de vie.
 *
 * @property description Brève description narrative du monstre.
 * @property particularites Particularités ou caractéristiques physiques/comportementales uniques.
 * @property caractères Traits de personnalité du monstre (ex: timide, joueur, etc.).
 */
class EspeceMonstre (
    var id : Int,
    var nom: String,
    var type: String,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val basePv: Int,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,
    val description: String = "",
    val particularites: String = "",
    val caractères: String = "",
) {
    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false).
     *               La valeur par défaut est true.
     * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
     *         L'art est lu à partir d'un fichier texte dans le dossier resources/art.
     */
    fun afficheArt(deFace: Boolean = true): String {
        val nomFichier = if (deFace) "front" else "back";
        val art = File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }
}

