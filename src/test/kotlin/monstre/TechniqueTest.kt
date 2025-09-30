package monstre

import org.example.monstre.Technique
import org.example.normal
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TechniqueTest {

    @Test
    fun calculerPrecision() {
        // Créer les techniques
        val technique100 = Technique(1,"Griffe",100.0,1.0,true,false,true,normal)
        val technique0 = Technique(2,"Guezz",0.0,0.0,false,true,false, normal)
        val technique50 = Technique(3,"Amendes",50.0,1.0,true,false,true,normal)

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
}



