🎮Contexte 

Description du jeu

Kotlin Monsters est un jeu inspiré des jeux Pokémon (surtout les anciennes générations).
 Vous incarnez un dresseur de monstres dont les objectifs sont :
 
 -> Obtenir tous les badges d’arène.


 -> Capturer tous les monstres du monde.


Vous pouvez explorer différentes zones (routes, cavernes, mers, etc.), capturer des monstres, combattre dans les arènes pour obtenir des badges, et acheter des objets pour progresser dans l’aventure.


🎯 Objectifs pédagogiques


->Approfondir l’apprentissage de la POO (Programmation Orientée Objet).

->Apprendre à travailler en mode projet.

->Mettre en place les bonnes pratiques (commentaires, noms explicites, architecture).

->Travailler sur la résolution de problèmes et de bugs.

->Apprendre à réaliser des tests unitaires et fonctionnels.

->Découvrir l’utilisation des DAO pour la persistance en base de données.


🔧 Analyse fonctionnelle

Utilisateur

Un seul type d’utilisateur : le joueur.

Fonctionnalités principales

Sprint 1


->Se déplacer dans différentes zones.

->Rencontrer des monstres sauvages.

->Combattre ou capturer des monstres sauvages.

->Consulter et organiser son équipe de monstres.

->Utiliser des objets pendant et hors combat.



Sprint 2


->Combattre des dresseurs dans les zones ou arènes.

->Acheter des objets en ville.

->Faire évoluer ses monstres et leur faire apprendre de nouvelles techniques.

->Implémenter un système de forces/faiblesses (ex. Eau > Feu > Plante > Eau).

->Ajouter des effets de statut (poison, sommeil, paralysie, etc.).



Sprint 3


->Intégration d’une BDD via JDBC.

->Gestion des sauvegardes via fichiers.


💻 Analyse technologique 


Langage principal : Kotlin

Gestion de projet et dépendances : Gradle

Bibliothèques :


->JUnit pour les tests (Sprint 2)


->JDBC pour la persistance en base (Sprint 3)


->Interface : console texte (ASCII Art pour les monstres, 15 lignes max / 50 caractères max).


📚 Lexique / Vocabulaire métier 

🎭 Acteurs principaux


->Entrainneur

  Personnage contrôlé par le joueur ou un PNJ. Possède une équipe de monstres, une boîte de stockage et un sac d’objets. C’est l’équivalent d’un dresseur.
  
  Partie
  
->Représente une session de jeu. Contient le joueur (Entrainneur), la zone actuelle et l’état global de la progression.

🐾 Monstres

  ->EspeceMonstre
  
    Décrit les caractéristiques de base d’un type de monstre (comme un « modèle » ou une espèce).
    Contient ses statistiques de base, ses multiplicateurs de croissance, son nom, son type et éventuellement son art ASCII.
    
  ->IndividuMonstre
  
    Instance unique d’un monstre appartenant à un joueur ou rencontré dans la nature.
    Possède un nom (qui peut être différent de son espèce), un niveau, des stats générées avec un petit facteur aléatoire, de l’expérience et des points de vie.
    Peut attaquer, monter de niveau, être renommé et afficher ses détails.
    Un monstre sauvage est un monstre qui appartient à aucun dresseur et donc un monstre que l’on peut capturer.
    
  ->Potentiel
  
    Facteur multiplicatif qui influence la croissance des statistiques lors d’un level-up (ex. un monstre avec un fort potentiel devient plus puissant). Correspond aux étoiles      dans les jeux Pokémon.
    
  ->Elément
  
    Chaque monstre a 1 ou 2 éléments (exemple : Eau, Feu, Plante, Insecte, Psy ...) 
    Les techniques ont aussi un élément.  
    Si un monstre utilise une technique qui a un élément qui ne correspond pas à ses éléments alors son attaque subit un malus de dégâts.  
    Chaque élément a des forces et des faiblesses contre les autres éléments. 
    Le feu et fort contre les plantes : 
     ->Si un monstre feu se fait attaquer avec une technique plante il subit seulement la moitié des dégâts. 
     ->Si un monstre plante se fait attaquer avec une technique feu il subit 2 fois plus de dégâts. 


🌍 Monde de jeu

->Zone

 Lieu où le joueur peut se déplacer et rencontrer des monstres.
 Contient une liste d’espèces de monstres qui peuvent apparaître et l’expérience moyenne des monstres de cette zone.
 Peut pointer vers une zone précédente et une suivante, créant une carte navigable.
 
->Ville 

 Endroit où le joueur peut se reposer, accéder à l’arène, acheter des objets, gérer sa boite à monstres.
 Arène 
 Lieu où le joueur affronte un champion pour obtenir un Badge.
 
->Badge 

 Récompense donnée au joueur après avoir battu une arène. Sert à débloquer du contenu ou prouver la progression.

⚔ Combat

->CombatMonstre 

 Représente un combat entre l’équipe de monstre du joueur et un monstre sauvage.
 Gère les tours, l’ordre d’action et l’affichage du combat.
 
->Technique 

 Attaque ou compétence qu’un monstre peut utiliser. A une précision, une puissance et peut être physique ou spéciale.
Un monstre connaît 4 techniques, s'il veut en apprendre d'autres il faut remplacer une technique existante.

->PalierTechnique

 Décrit à quel niveau un monstre apprend une technique.
 
->Statut 

 État particulier affectant un monstre (poison, sommeil…). Peut durer un certain nombre de tours et avoir des effets.
 
->Chance 

 Indique une probabilité d’événement (ex. chance d’infliger un statut).

🎒 Objets

->MonsterKube

 Objet permettant de capturer un monstre sauvage. A une probabilité de réussite.
 
->Potion 

 Objet soignant les PV d’un monstre.
 
->Item Anti-Statut 

 Objet permettant de retirer un statut (poison, paralysie, etc.).
 
->Capsule Technique 

 Objet permettant d’apprendre une nouvelle technique à un monstre.
 
->Inventaire 

 Structure gérant les quantités d’objets possédés.
 
->Magasin

Lieu dans une ville ou on peut acheter des objets.


🧮 Système

->exp / expZone

 Quantité d’expérience accumulée par un monstre ou donnée par les monstres de la zone.
 
->niveau

 Niveau actuel du monstre. Conditionne ses statistiques.
 
->pv / pvMax

 Points de vie actuels et maximums du monstre.
 
->modAttaque / modVitesse…

 Modificateurs de croissance pour chaque statistique, appliqués lors des montées de niveau.
 
->Atq / Atq Spe et Def / Def Spe 

 Certaines techniques sont dites spéciales dans ce cas elle utilisé Atq Spé plutôt que Atq. De même, un monstre qui se défend contre une attaque spéciale se défend avec Def Spé  et non Def.



Organisation du projet 

Le mini projet aura lieu sur 3 périodes où Sprints : 

Sprint 1 :  Création du projet et codage des fonctionnalités principales du projet

Sprint 2 :  Travail sur un ou plusieurs modules d’améliorations du projet 

Sprint 3 :  Utilisation de JDBC pour interagir avec la BDD et utilisation de fichiers pour gérer les sauvegardes.  

Le sprint 1 est individuel. Les sprints 2 et 3 correspondent à du travail en équipe. 

Pour les sprints 2 et 3, les groupes sont constitués de 2 personnes. 
