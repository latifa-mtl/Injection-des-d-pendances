# Injection des Dépendances 

## Description

Ce projet illustre les différentes approches d'**injection de dépendances (IoC)** en Java, en progressant d'une instanciation manuelle statique jusqu'à l'utilisation du framework Spring (XML et annotations).

---

## Architecture du Projet

```
src/main/java/net/moutawakil/
├── dao/
│   ├── IDao.java              # Interface DAO
│   └── DaoImpl.java           # Implémentation BDD (Spring @Component)
├── ext/
│   └── DaoImplV2.java         # Implémentation alternative (bean "d2")
├── metier/
│   ├── IMetier.java           # Interface Métier
│   └── MetierImpl.java        # Implémentation avec couplage faible
└── pres/
    ├── PresStatique.java       # Injection statique
    ├── PresDynamique.java      # Injection dynamique (réflexion)
    ├── PresSpringXML.java      # Injection via Spring XML
    └── PresSpringAnnotation.java # Injection via Spring Annotations
src/main/resources/
├── config.xml                 # Configuration Spring XML
└── config.txt                 # Fichier de config pour injection dynamique
```

---

## Technologies

| Technologie | Version |
|-------------|---------|
| Java        | 17+     |
| Spring Framework | 6.x |
| Maven       | 3.x     |

---

## Étapes de Réalisation

### 1. Interface `IDao`

Définit le contrat de la couche d'accès aux données avec une méthode `getData()`.

```java
public interface IDao {
    double getData();
}
```

### 2. Implémentations de `IDao`

- **`DaoImpl`** — simule un accès base de données, retourne `34`.
- **`DaoImplV2`** — implémentation alternative, retourne `5`, enregistrée sous le bean Spring `"d2"`.

### 3. Interface `IMetier`

Définit la couche métier avec une méthode `calcul()`.

```java
public interface IMetier {
    double calcul();
}
```

### 4. Implémentation `MetierImpl` (Couplage Faible)

Dépend de `IDao` via l'interface (pas de l'implémentation concrète). La dépendance est injectée via le **constructeur** avec `@Qualifier("d2")` pour cibler `DaoImplV2`.

```java
@Component
public class MetierImpl implements IMetier {
    private IDao dao;

    public MetierImpl(@Qualifier("d2") IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        double t = dao.getData();
        return t * t;  
    }
}
```

---

## Les 4 Modes d'Injection de Dépendances

### a) Instanciation Statique `PresStatique.java`

Les dépendances sont câblées manuellement dans le code source. Simple mais rigide : tout changement nécessite une recompilation.

```java
MetierImpl metier = new MetierImpl(new DaoImplV2());
System.out.println("RES=" + metier.calcul()); 
```

---

### b) Instanciation Dynamique `PresDynamique.java`

Les noms des classes sont lus depuis `config.txt` et instanciés via la **réflexion Java** (`Class.forName`, `newInstance`). Permet de changer d'implémentation sans recompiler.

**`config.txt` :**
```
net.moutawakil.ext.DaoImplV2
net.moutawakil.metier.MetierImpl
```

```java
Scanner scanner = new Scanner(new File("config.txt"));
Class cDao = Class.forName(scanner.nextLine());
IDao dao = (IDao) cDao.newInstance();

Class cMetier = Class.forName(scanner.nextLine());
IMetier metier = (IMetier) cMetier.getConstructor(IDao.class).newInstance(dao);

System.out.println("RES=" + metier.calcul());
```

---

### c) Spring Framework Version XML `PresSpringXML.java`

Le contexte Spring est configuré dans `config.xml`. Spring instancie et injecte les beans automatiquement.

```java
ApplicationContext springContext = new ClassPathXmlApplicationContext("config.xml");
IMetier metier = springContext.getBean(IMetier.class);
System.out.println("RES=" + metier.calcul());
```

---

### d) Spring Framework Version Annotations `PresSpringAnnotation.java`

Spring scanne le package `net.moutawakil` à la recherche des annotations (`@Component`, `@Autowired`, `@Qualifier`) et gère l'injection automatiquement.

```java
ApplicationContext ctx = new AnnotationConfigApplicationContext("net.moutawakil");
IMetier metier = ctx.getBean(IMetier.class);
System.out.println("RES=" + metier.calcul());
```

## Lancement du Projet

### Prérequis
- Java 17+
- Maven 3.x

### Compilation
```bash
mvn clean install
```

### Exécution (exemple avec Spring Annotations)
```bash
mvn exec:java -Dexec.mainClass="net.moutawakil.pres.PresSpringAnnotation"
```

---
