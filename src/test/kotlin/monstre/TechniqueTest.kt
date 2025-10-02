package monstre

import org.example.eau
import org.example.especeFlamkip
import org.example.especeSpringLeaf
import org.example.feu
import org.example.insecte
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre
import org.example.monstre.Technique
import org.example.normal
import org.example.plante
import org.example.roche
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest

class TechniqueTest {
    @BeforeTest
    fun valorisation() {
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
        especeFlamkip.elements.add(feu)
        especeSpringLeaf.elements.add(plante)
    }
    @Test
    fun calculerPrecision() {
        // Créer les techniques
        val technique100 = Technique(1,"Griffe",100.0,30.0,true,false,true,true,normal)
        val technique0 = Technique(2,"Guezz",0.0,0.0,false,true,false, false,normal)
        val technique50 = Technique(3,"Amendes",50.0,100.0,true,false,true,false,normal)

        var compteurT50 = 0

        // Boucle 100 fois
        repeat(100) {
            // technique100 doit toujours réussir
            assertTrue(technique100.calculerPrecision(), "technique100 doit toujours réussir")

            // technique0 doit toujours échouer
            assertFalse(technique0.calculerPrecision(), "technique0 doit toujours échouer")

            // technique50 peut réussir ou échouer
            if (technique50.calculerPrecision()) {
                compteurT50++
            }
        }

        println("compteurT50 = $compteurT50")

        // Vérifier que compteurT50 est entre 20 et 80
        assertTrue(compteurT50 > 20 && compteurT50 < 80, "compteurT50 doit être entre 20 et 80")
    }

    @Test
    fun testcalculerBonusStab(){
        val techFeu = Technique(
            1,
            "Flammeche",
            100.0,
            1.0,
            false,
            false,
            false,
            true,
            feu
        )
        val monstreFeu = IndividuMonstre(
            2,
            "flamkip",
            especeFlamkip,
            null,
            1500.0
        )
        val monstrePlante = IndividuMonstre(
            1,
            "springleaf",
            especeSpringLeaf,
            null,
            1500.0
        )

        val bonusFeu = techFeu.calculBonusStab(monstreFeu)
        val bonusPlante = techFeu.calculBonusStab(monstrePlante)

        // Assertions
        assertEquals(bonusFeu, 1.15, "Le bonus pour le monstre de type Feu devrait être de $bonusFeu")
        println("Assertion réussie : bonusFeu == $bonusFeu")
        assertEquals(bonusPlante, 0.85, "Le bonus pour le monstre de type Plante devrait être de $bonusPlante")
        println("Assertion réussie : bonusPlante == $bonusPlante")
    }

    @Test
    fun testEffetInfligeDegats() {
        val techPlante = Technique(
            3,
            "Technique plante",
            100.0,
            1.0,
            false,
            false,
            false,
            true,
            plante
        )

        val techSpePlante = Technique(
            3,
            "Technique plante",
            100.0,
            1.0,
            false,
            false,
            true,
            true,
            plante
        )

        val monstreFeu = IndividuMonstre(
            1,
            "attaquant",
            especeFlamkip,
            null,
            1500.0
        )
        val monstrePlante = IndividuMonstre(
            2,
            "defenseur",
            especeSpringLeaf,
            null,
            1500.0
        )

        //Pour eviter les variations de score d'attaque
        monstreFeu.attaque = 10
        monstrePlante.attaque = 10
        monstreFeu.attaqueSpe = 15
        monstrePlante.attaqueSpe = 15

        val degats1 = techPlante.effet(monstreFeu, monstrePlante)
        //degats1 = (10 * 0.85) * 1.0 = 8.5
        println(degats1)
        assertTrue(degats1 == 8.5)

        val degats2 = techPlante.effet(monstrePlante, monstreFeu)
        //degat2 = (10 * 1.15) * 0.5 = 5.75
        println(degats2)
        assertTrue(degats2 == 5.75)

        val degatsSpe1 = techSpePlante.effet(monstreFeu, monstrePlante)
        //degats1 = (15 * 0.85) * 1.0 = 12.75
        println(degatsSpe1)
        assertTrue(degatsSpe1 == 12.75)

        val degatsSpe2 = techSpePlante.effet(monstrePlante, monstreFeu)
        //degat2 = (15 * 1.15) * 0.5 = 8.625
        println(degatsSpe2)
        assertTrue(degatsSpe2 == 8.625)
    }
}

