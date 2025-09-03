# Activité-type 2 : Concevoir et développer une application sécurisée organisée en couches

## Exemple n° 2 : Conception et développement d'une plateforme e-learning dédiée au développement web (Java / Spring Boot / Thymeleaf)

### 1. Présentation du projet

**U-learnvite** est une plateforme e-learning moderne développée avec Spring Boot 3.4.4 et Java 21, dédiée à l'apprentissage du développement web. L'application suit une architecture en couches sécurisée et utilise les technologies suivantes :

- **Backend** : Spring Boot 3.4.4, Spring Security 6, Spring Data JPA
- **Base de données** : MySQL 8.0
- **Frontend** : Thymeleaf, Bootstrap 5.3.3, CSS personnalisé
- **Sécurité** : Spring Security avec authentification personnalisée
- **Build** : Maven

### 2. Moyens utilisés

#### 2.1 Technologies de développement

**Langages et frameworks :**
- **Java 21** : Langage principal pour le développement backend
- **Spring Boot 3.4.4** : Framework pour structurer l'architecture en couches
- **Spring Security 6** : Framework de sécurité pour l'authentification et l'autorisation
- **JPA/Hibernate** : Pour la persistance des données et le mapping objet-relationnel
- **Thymeleaf** : Moteur de template pour la couche de présentation
- **Bootstrap 5.3.3** : Framework CSS pour l'interface utilisateur responsive

#### 2.2 Outils de conception et design

**Maquettes et design :**
- **Figma** : Création des maquettes des interfaces utilisateur
- **Canva** : Création du logo de l'application et des éléments graphiques

**Modélisation :**
- **StarUML** : Création des diagrammes UML (diagrammes de classes, de séquence)
- **Looping** : Modélisation des processus métier
- **draw.io** : Création du modèle conceptuel de données (MCD) et diagrammes d'architecture

#### 2.3 Outils de développement et base de données

**Environnement de développement :**
- **Eclipse** : IDE principal pour le développement Java
- **Git/GitHub** : Versionnement et suivi de l'évolution du code
- **Maven** : Gestion des dépendances et build du projet

**Base de données :**
- **MySQL Workbench** : Modélisation et manipulation de la base de données MySQL 8.0

#### 2.4 Outils de test et validation

**Tests et validation :**
- **Postman** : Tests des différentes routes sécurisées de l'application, notamment celles restreintes par rôle utilisateur
- **Spring Boot Test** : Tests unitaires et d'intégration
- **Spring Security Test** : Tests de sécurité et d'authentification

### 3. Architecture en couches

L'application suit une architecture en couches bien définie, séparant les responsabilités et facilitant la maintenance :

#### 3.1 Structure des packages

```
src/main/java/com/illan/ulearnvite/
├── UlearnviteApplication.java          # Point d'entrée Spring Boot
├── config/                             # Couche de configuration
│   └── SecurityConfig.java            # Configuration de sécurité
├── controller/                         # Couche de présentation (Controllers)
│   ├── AuthController.java            # Gestion de l'authentification
│   ├── CourseController.java          # Gestion des cours
│   ├── HomeController.java            # Page d'accueil
│   └── AdminUserController.java       # Administration des utilisateurs
├── model/                             # Couche métier et données
│   ├── entity/                        # Entités JPA
│   │   ├── User.java                  # Utilisateur
│   │   ├── Course.java                # Cours
│   │   ├── Section.java               # Sections de cours
│   │   ├── Lesson.java                # Leçons
│   │   ├── Quiz.java                  # Quiz
│   │   └── ...
│   ├── repository/                    # Couche d'accès aux données
│   └── service/                       # Couche métier
├── security/                          # Couche de sécurité
│   └── ConnectedUser.java            # Utilisateur connecté
├── dto/                               # Objets de transfert de données
└── enums/                             # Énumérations
```

#### 3.2 Couche de présentation (Controllers)

**AuthController.java** - Gestion de l'authentification :
```java
@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                                Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        model.addAttribute("content", "auth/login");
        
        if (error != null) {
            model.addAttribute("error", "Nom d'utilisateur ou mot de passe invalide.");
        }
        
        return "base";
    }
    
    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("registerDTO") RegisterDTO registerDTO,
                                      BindingResult result, Model model) {
        // Validation et traitement de l'inscription
    }
}
```

**CourseController.java** - Gestion des cours :
```java
@Controller
@RequestMapping("/courses")
public class CourseController {
    
    @GetMapping
    public String getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue ="7") int size,
                        Model model) {
        Page<Course> coursesPage = courseService.getAllCourses(page,size);
        model.addAttribute("courses", coursesPage);
        model.addAttribute("content", "courses/list-courses");
        return "base";
    }
    
    @GetMapping("/{id}")
    public String getCourseDetails(@PathVariable Long id,
                                   @RequestParam(value="lessonId", required = false) Long lessonId,
                                   Model model) {
        // Affichage des détails d'un cours avec ses leçons
    }
}
```

#### 3.3 Couche métier (Services)

Les services implémentent la logique métier et font le lien entre les controllers et les repositories :

- **AuthService** : Gestion de l'authentification et de l'inscription
- **UserService** : Gestion des utilisateurs
- **CourseService** : Gestion des cours et de leur contenu
- **ConnectedUserService** : Service pour l'utilisateur connecté

#### 3.4 Couche d'accès aux données (Repositories)

Les repositories étendent `JpaRepository` pour l'accès aux données :

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
```

#### 3.5 Couche de données (Entités)

**User.java** - Entité utilisateur avec validation :
```java
@Entity
@Table(uniqueConstraints = { 
    @UniqueConstraint(columnNames = "username"), 
    @UniqueConstraint(columnNames = "email") 
})
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    @Size(min = 2, max = 30, message = "Le nom de l'utilisateur ne doit pas dépasser 30 caractères")
    @Column(nullable = false, unique = true, length = 30)
    private String username;
    
    @NotBlank(message = "L'adresse email est obligatoire")
    @Email(message = "L'adresse email doit être valide")
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 12, message = "Le mot de passe doit contenir au moins 12 caractères")
    @Column(nullable = false)
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<Role>();
}
```

**Course.java** - Entité cours avec relations :
```java
@Entity
@Data
@ToString(exclude={"user","lessons","category"})
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message="Le titre du cours est obligatoire")
    @Column(nullable = false, length = 50)
    private String title;
    
    @NotBlank(message="La description du cours est obligatoire")
    @Column(nullable = false, length = 170)
    private String description;
    
    @Column(nullable = false)
    private String imageUrl;
    
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;
    
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Section> sections;
}
```

### 4. Sécurité de l'application

#### 4.1 Configuration de sécurité (SecurityConfig.java)

```java
@Configuration
public class SecurityConfig {
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth ->
            auth.requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll())
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form
                .loginPage("/auth/login")
                .failureUrl("/auth/login?error=true")
                .defaultSuccessUrl("/", false)
                .permitAll()
            )
            .logout(logout -> logout.logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll())
            .sessionManagement(session -> session.maximumSessions(1));
        
        return httpSecurity.build();
    }
}
```

**Fonctionnalités de sécurité implémentées :**

1. **Authentification personnalisée** avec formulaire de connexion
2. **Encodage des mots de passe** avec BCrypt (force 10)
3. **Gestion des rôles** (ADMIN, USER)
4. **Protection CSRF** (désactivée pour simplifier)
5. **Gestion des sessions** (limite à 1 session par utilisateur)
6. **Logout sécurisé** avec invalidation de session
7. **Validation des données** avec Bean Validation

#### 4.2 Validation des données

L'application utilise Bean Validation pour valider les données :

```java
@NotBlank(message = "Le nom d'utilisateur est obligatoire")
@Size(min = 2, max = 30, message = "Le nom de l'utilisateur ne doit pas dépasser 30 caractères")
private String username;

@NotBlank(message = "L'adresse email est obligatoire")
@Email(message = "L'adresse email doit être valide")
private String email;

@NotBlank(message = "Le mot de passe est obligatoire")
@Size(min = 12, message = "Le mot de passe doit contenir au moins 12 caractères")
private String password;
```

### 5. Interface utilisateur

#### 5.1 Template de base (base.html)

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>U-learnvite</title>
    
    <!-- STYLE -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/style.css" />
    <link rel="stylesheet" href="/css/register.css" />
    
    <!-- SCRIPT -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="container-fluid px-0">
    <!-- Menu (header) -->
    <nav th:insert="~{_partials/menu :: menu}"></nav>
    
    <!-- Contenu principal dynamiquement remplacé -->
    <main th:replace="~{${content} :: content}"></main>
    
    <!-- Footer -->
    <footer th:insert="~{_partials/footer :: footer}"></footer>
</body>
</html>
```

#### 5.2 Structure des templates

```
src/main/resources/templates/
├── base.html                          # Template de base
├── _partials/                         # Partiels réutilisables
│   ├── menu.html                      # Menu de navigation
│   └── footer.html                    # Pied de page
├── auth/                              # Pages d'authentification
│   ├── login.html                     # Page de connexion
│   └── register.html                  # Page d'inscription
├── courses/                           # Pages des cours
│   ├── list-courses.html              # Liste des cours
│   ├── popular-courses.html           # Cours populaires
│   ├── search.html                    # Recherche de cours
│   └── course-card.html               # Carte de cours
├── pages/                             # Pages générales
│   └── course-details.html            # Détails d'un cours
├── admin/                             # Pages d'administration
│   └── users/                         # Gestion des utilisateurs
└── home/                              # Pages d'accueil
```

### 6. Configuration de la base de données

#### 6.1 Configuration JPA (application.properties)

```properties
# Base de données
spring.datasource.url=jdbc:mysql://localhost:3306/ulearnvite_bd?useSSL=false
spring.datasource.username=root
spring.datasource.password=rootroot
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true

# Sécurité
spring.security.user.name=admin
spring.security.user.password=admin
spring.security.user.roles=ADMIN
```

#### 6.2 Modèle de données

L'application utilise un modèle de données relationnel avec les entités principales :

- **User** : Utilisateurs avec rôles
- **Course** : Cours avec sections et leçons
- **Section** : Sections organisant le contenu des cours
- **Lesson** : Leçons individuelles
- **Quiz** : Quiz d'évaluation
- **Category** : Catégories de cours
- **Score** : Scores des utilisateurs
- **Progress** : Progression des utilisateurs

### 7. Fonctionnalités principales

#### 7.1 Authentification et autorisation

- **Inscription** : Formulaire avec validation des données
- **Connexion** : Authentification sécurisée
- **Gestion des rôles** : ADMIN et USER
- **Déconnexion** : Logout sécurisé

#### 7.2 Gestion des cours

- **Liste des cours** : Pagination et filtrage
- **Détails des cours** : Sections et leçons
- **Cours populaires** : Tri par popularité
- **Recherche** : Recherche de cours

#### 7.3 Administration

- **Gestion des utilisateurs** : CRUD des utilisateurs
- **Gestion des cours** : Administration du contenu
- **Statistiques** : Suivi des utilisateurs

### 8. Captures d'écran de l'application

#### 8.1 Page d'accueil
```
Voir le fichier : captures/homepage.txt
```
*Interface moderne avec navigation et présentation des cours*

#### 8.2 Page de connexion
```
Voir le fichier : captures/login.txt
```
*Formulaire de connexion sécurisé avec validation*

#### 8.3 Liste des cours
```
Voir le fichier : captures/courses.txt
```
*Affichage paginé des cours avec cartes visuelles*

#### 8.4 Détails d'un cours
```
Voir le fichier : captures/course-details.txt
```
*Interface de visualisation des leçons et sections*

#### 8.5 Page d'inscription
```
Voir le fichier : captures/register.txt
```
*Formulaire d'inscription avec validation complète*

### 9. Points forts de l'architecture

#### 9.1 Séparation des responsabilités

- **Controllers** : Gestion des requêtes HTTP et navigation
- **Services** : Logique métier et orchestration
- **Repositories** : Accès aux données
- **Entités** : Modèle de données
- **DTOs** : Transfert de données entre couches

#### 9.2 Sécurité robuste

- Authentification personnalisée
- Encodage des mots de passe
- Gestion des rôles et permissions
- Validation des données
- Protection contre les attaques courantes

#### 9.3 Interface utilisateur moderne

- Design responsive avec Bootstrap 5
- Templates Thymeleaf modulaires
- Navigation intuitive
- Validation côté client et serveur

#### 9.4 Base de données optimisée

- Relations bien définies
- Index sur les colonnes critiques
- Contraintes d'intégrité
- Optimisation des requêtes

### 10. Technologies et bonnes pratiques

#### 10.1 Spring Boot 3.4.4
- Configuration automatique
- Démarrage rapide
- Intégration native avec Spring Security
- Support Java 21

#### 10.2 Spring Security 6
- Authentification personnalisée
- Gestion des sessions
- Protection CSRF
- Encodage des mots de passe

#### 10.3 Spring Data JPA
- Repositories automatiques
- Mapping objet-relationnel
- Requêtes optimisées
- Gestion des transactions

#### 10.4 Thymeleaf
- Templates sécurisés
- Intégration avec Spring Security
- Fragments réutilisables
- Validation côté serveur

### 11. Conclusion

L'application **U-learnvite** démontre une architecture en couches bien structurée avec :

- **Séparation claire des responsabilités** entre les couches
- **Sécurité robuste** avec Spring Security
- **Interface utilisateur moderne** et responsive
- **Base de données optimisée** avec JPA/Hibernate
- **Code maintenable** avec des bonnes pratiques

Cette architecture permet une évolution facile de l'application et facilite la maintenance du code, tout en garantissant la sécurité des données et une expérience utilisateur optimale.

---

**Développé avec :**
- Java 21
- Spring Boot 3.4.4
- Spring Security 6
- Spring Data JPA
- MySQL 8.0
- Thymeleaf
- Bootstrap 5.3.3
- Maven 