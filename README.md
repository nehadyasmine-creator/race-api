# Race API – TP REST

## Description

Ce projet est une **API REST développée avec Spring Boot** permettant de gérer l'inscription de coureurs à différentes courses.

L'API permet de :

* gérer les **coureurs (runners)**
* gérer les **courses (races)**
* gérer les **inscriptions (registrations)**

Les données sont stockées dans une **base de données PostgreSQL** et les migrations sont gérées avec **Flyway**.

---

# Technologies utilisées

* Java 25
* Spring Boot 4
* Spring Web
* Spring Data JPA
* Flyway
* Docker
* PostgreSQL
* Adminer

---

# Lancer le projet

## 1. Cloner le projet

```bash
git clone <https://github.com/nehadyasmine-creator/race-api>
cd race-api
```

---

## 2. Lancer la base de données

Démarrer PostgreSQL et Adminer avec Docker :

```bash
docker compose up -d
```

---

## 3. Accéder à Adminer

Adminer permet de visualiser la base de données.

URL :

```
http://localhost:8081
```

Paramètres de connexion :

| Champ    | Valeur        |
| -------- | ------------- |
| System   | PostgreSQL    |
| Server   | race_postgres |
| Username | race          |
| Password | race          |
| Database | race_db       |

---

## 4. Lancer l'application

Depuis le terminal :

```bash
mvn spring-boot:run
```

L'API sera disponible sur :

```
http://localhost:8080
```

---

# Endpoints implémentés

## Runners

| Méthode | Endpoint                  | Description                    |
| ------- | ------------------------- | ------------------------------ |
| GET     | /runners                  | Liste tous les coureurs        |
| GET     | /runners/{id}             | Récupère un coureur            |
| POST    | /runners                  | Crée un coureur                |
| PUT     | /runners/{id}             | Modifie un coureur             |
| DELETE  | /runners/{id}             | Supprime un coureur            |
| GET     | /runners/{runnerId}/races | Liste les courses d'un coureur |

---

## Races

| Méthode | Endpoint                           | Description                     |
| ------- | ---------------------------------- | ------------------------------- |
| GET     | /races                             | Liste toutes les courses        |
| GET     | /races?location=Paris              | Filtre les courses par location |
| GET     | /races/{id}                        | Récupère une course             |
| POST    | /races                             | Crée une course                 |
| PUT     | /races/{id}                        | Modifie une course              |
| GET     | /races/{raceId}/participants/count | Nombre de participants          |

---

## Registrations

| Méthode | Endpoint                      | Description                         |
| ------- | ----------------------------- | ----------------------------------- |
| POST    | /races/{raceId}/registrations | Inscrit un coureur à une course     |
| GET     | /races/{raceId}/registrations | Liste les participants d'une course |

---

# Règles métier implémentées

* Un coureur ne peut pas être inscrit **deux fois à la même course** → `409 Conflict`
* Une **course ne peut pas dépasser son nombre maximum de participants** → `409 Conflict`
* Un **email doit contenir @** → `400 Bad Request`
* Les ressources doivent exister (**runner ou race**) → `404 Not Found`
