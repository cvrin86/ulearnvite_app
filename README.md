# U-learnvite - Plateforme E-learning

## Présentation

**U-learnvite** est une plateforme e-learning moderne développée avec Spring Boot 3.4.4 et Java 21, dédiée à l'apprentissage du développement web. Cette application démontre une architecture en couches sécurisée et suit les bonnes pratiques de développement.

## Technologies utilisées

- **Backend** : Spring Boot 3.4.4, Spring Security 6, Spring Data JPA
- **Base de données** : MySQL 8.0
- **Frontend** : Thymeleaf, Bootstrap 5.3.3, CSS personnalisé
- **Sécurité** : Spring Security avec authentification personnalisée
- **Build** : Maven

## Structure du projet

```
ulearnvite_auth_fonctionelle/
├── ACTIVITE_TYPE_2.md              # Documentation complète de l'activité-type 2
├── captures/                       # Captures d'écran textuelles de l'application
│   ├── homepage.txt               # Page d'accueil
│   ├── login.txt                  # Page de connexion
│   ├── courses.txt                # Liste des cours
│   ├── course-details.txt         # Détails d'un cours
│   └── register.txt               # Page d'inscription
├── src/main/java/com/illan/ulearnvite/
│   ├── UlearnviteApplication.java  # Point d'entrée Spring Boot
│   ├── config/                     # Configuration (sécurité, etc.)
│   ├── controller/                 # Controllers (présentation)
│   ├── model/                      # Modèle métier et données
│   │   ├── entity/                 # Entités JPA
│   │   ├── repository/             # Repositories
│   │   └── service/                # Services métier
│   ├── security/                   # Sécurité
│   ├── dto/                        # Objets de transfert
│   └── enums/                      # Énumérations
├── src/main/resources/
│   ├── templates/                  # Templates Thymeleaf
│   ├── static/                     # Ressources statiques
│   └── application.properties      # Configuration
└── pom.xml                         # Configuration Maven
```

## Fonctionnalités principales

### 1. Authentification et autorisation
- Inscription avec validation des données
- Connexion sécurisée
- Gestion des rôles (ADMIN, USER)
- Déconnexion sécurisée

### 2. Gestion des cours
- Catalogue de cours avec pagination
- Détails des cours avec sections et leçons
- Cours populaires
- Recherche et filtrage

### 3. Administration
- Gestion des utilisateurs
- Administration du contenu
- Statistiques

## Architecture en couches

L'application suit une architecture en couches bien définie :

1. **Couche de présentation** : Controllers Spring MVC
2. **Couche métier** : Services avec logique métier
3. **Couche d'accès aux données** : Repositories Spring Data JPA
4. **Couche de données** : Entités JPA
5. **Couche de sécurité** : Spring Security

## Sécurité

- Authentification personnalisée
- Encodage des mots de passe avec BCrypt
- Gestion des rôles et permissions
- Validation des données avec Bean Validation
- Protection contre les attaques courantes

## Installation et démarrage

### Prérequis
- Java 21
- Maven 3.6+
- MySQL 8.0

### Configuration de la base de données
1. Créer une base de données MySQL nommée `ulearnvite_bd`
2. Configurer les paramètres de connexion dans `application.properties`

### Démarrage
```bash
# Compilation
mvn clean compile

# Démarrage de l'application
mvn spring-boot:run
```

L'application sera accessible à l'adresse : http://localhost:8080

## Documentation

### Activité-type 2
Le fichier `ACTIVITE_TYPE_2.md` contient la documentation complète de l'activité-type 2 avec :

- Analyse détaillée de l'architecture en couches
- Explication de la sécurité implémentée
- Code source commenté
- Captures d'écran textuelles
- Bonnes pratiques utilisées

### Captures d'écran
Les captures d'écran sont disponibles dans le dossier `captures/` sous forme de représentations textuelles ASCII :

- `homepage.txt` : Page d'accueil
- `login.txt` : Page de connexion
- `courses.txt` : Catalogue des cours
- `course-details.txt` : Détails d'un cours
- `register.txt` : Page d'inscription

## Points forts

1. **Architecture en couches** : Séparation claire des responsabilités
2. **Sécurité robuste** : Authentification et autorisation complètes
3. **Interface moderne** : Design responsive avec Bootstrap 5
4. **Code maintenable** : Bonnes pratiques de développement
5. **Base de données optimisée** : Relations bien définies

## Développement

Ce projet a été développé dans le cadre d'une formation en développement web, démontrant la maîtrise des technologies Java/Spring Boot et des concepts d'architecture en couches.

---

**Développé avec :**
- Java 21
- Spring Boot 3.4.4
- Spring Security 6
- Spring Data JPA
- MySQL 8.0
- Thymeleaf
- Bootstrap 5.3.3
- Maven # ulearnvite_app
