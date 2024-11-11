
Voici le fichier README.md traduit et adapté en français :

Backend GamesUP & API de Recommandation de Jeux

Ce projet contient le backend de l'application GamesUP et une API de recommandation de jeux permettant de proposer des jeux aux utilisateurs en fonction de leurs préférences et de prédire les notes qu’ils pourraient attribuer.

Table des Matières

Présentation du Projet
Fonctionnalités
Prérequis
Installation et Configuration
Documentation de l'API
Technologies Utilisées
Présentation du Projet

Le backend GamesUP, développé en Spring Boot, est une API sécurisée offrant des fonctionnalités d'inscription et de connexion pour les utilisateurs, avec gestion de rôles. Il communique avec un service FastAPI pour obtenir des recommandations de jeux et des prédictions de notes basées sur les préférences de chaque utilisateur.

L’API de recommandation de jeux utilise FastAPI et un modèle KNN (K-Nearest Neighbors) pour proposer des jeux personnalisés et prédire les notes que les utilisateurs pourraient attribuer aux jeux.

Fonctionnalités

Backend GamesUP (Spring Boot)
Inscription et Connexion : Création et authentification des comptes utilisateurs avec gestion des rôles.
Sécurisation des Routes : Accès différencié selon le rôle de l’utilisateur (ex. : accès administrateur).
Pages protégées : Certaines pages sont accessibles uniquement aux utilisateurs connectés.
Communication avec l’API de recommandation : Utilisation de l’API FastAPI pour les recommandations de jeux.
API de Recommandation de Jeux (FastAPI)
Recommandations de Jeux : Propose des jeux aux utilisateurs selon leurs préférences.
Prédiction de Notes : Prédit les notes que les utilisateurs pourraient attribuer à des jeux spécifiques.
Réentraînement du Modèle : Réentraînement possible avec de nouvelles données utilisateur/jeu.
Prérequis

Avant de démarrer, assurez-vous d’avoir installé :

Java 17
Maven
Python 3.x
pip
Les dépendances FastAPI et machine learning pour Python : pandas, scikit-learn, pydantic, etc.
Installez les dépendances Python avec :

pip install -r requirements.txt
Installation et Configuration

1. Backend Spring Boot
Clonez le projet, puis exécutez la commande Maven pour lancer le backend :

mvn spring-boot:run
2. API de Recommandation (FastAPI)
Démarrez l’API FastAPI en exécutant la commande suivante :

uvicorn app:app --reload
Structure du Projet

En cas d'échec à cause des dépendances il faut faire un clean installer dans dans le backend mais également dans le dossier parent ; il faut reiterer l'action pour les dépendnaces python.

.
├── src/main/java/com/gamesUP
│   ├── GamesUPApplication.java       # Point d'entrée du backend
│   ├── config                        # Configuration Spring Security
│   ├── controller                    # Contrôleurs pour les routes d'API
│   └── service                       # Services d’authentification et logique d’affaires
├── app.py                            # Code de l'API FastAPI
├── recommendation.py                 # Logique des recommandations et prédictions
├── data_loader.py                    # Chargement des données utilisateur/jeu
├── requirements.txt                  # Dépendances Python
└── donneesjeux.csv                   # Données des jeux et utilisateurs
Documentation de l'API

Routes Backend (Spring Boot)
Inscription : POST /register – Permet la création d’un compte utilisateur.
Connexion : POST /login – Authentifie un utilisateur et retourne un token de session.
Accès Utilisateur : GET /user/dashboard – Accessible uniquement pour les utilisateurs authentifiés.
Accès Admin : GET /admin/dashboard – Accessible uniquement pour les administrateurs.
Routes API FastAPI
Accueil
GET / – Retourne un message de bienvenue.
Recommandation de jeux
POST /recommend ou GET /recommend – Prend user_id et game_id en entrée et retourne des jeux recommandés.
Prédiction de notes
POST /predict_rating ou GET /predict_rating – Prend user_id et game_id et retourne la note prédite.
Entraînement du modèle
POST /train – Réentraîne le modèle avec de nouvelles données.
Données
GET /data – Retourne un message confirmant l’accès aux données.
Technologies Utilisées

Backend : Spring Boot, Spring Security
API : FastAPI, Uvicorn
Machine Learning : Scikit-Learn (KNN)
Base de Données : MySQL (ou autre SGBD selon votre configuration)
Lancement du Projet

Lancez le backend Spring Boot :
mvn spring-boot:run
Lancez l'API FastAPI :
uvicorn app:app --reload

Auteurs

Développé par Steve
