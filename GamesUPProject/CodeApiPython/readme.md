# API de Recommandation de Jeux

Ce projet permet de recommander des jeux aux utilisateurs en fonction de leurs préférences et de prédire les notes qu'un utilisateur pourrait attribuer à un jeu. Il utilise FastAPI pour créer l'API et KNN (K-Nearest Neighbors) pour les modèles de recommandation et de prédiction de notes. Le projet inclut également une partie d'entraînement avec des données utilisateur et des jeux sous forme de fichier CSV.

## Prérequis

Avant de commencer, assurez-vous d'avoir les éléments suivants installés sur votre machine :

- Python 3.x
- pip
- FastAPI
- Uvicorn (serveur ASGI pour FastAPI)
- pandas
- scikit-learn
- pydantic

Vous pouvez installer les dépendances nécessaires en utilisant `pip` avec le fichier `requirements.txt` :

```bash
pip install -r requirements.txt

.
├── app.py                # Code de l'API FastAPI
├── recommendation.py      # Logique des recommandations et prédictions
├── data_loader.py         # Chargement des données utilisateur et jeux
├── requirements.txt       # Dépendances du projet
└── donneesjeux.csv         # Fichier CSV contenant les données des jeux et des utilisateurs

Fonctionnalités

Chargement des données
Le projet utilise un fichier CSV pour charger les données des jeux et des utilisateurs. Vous pouvez spécifier le chemin du fichier dans le script.

Exemple de chargement des données depuis un fichier CSV :

import pandas as pd

def load_training_data(file_path):
    """
    Charge un fichier CSV contenant les données des utilisateurs et des jeux.
    """
    return pd.read_csv(file_path)

# Exemple de test de chargement de données
file_path = "/path/to/donneesjeux.csv"
data = load_training_data(file_path)
print(data.head())  # Affiche les premières lignes des données pour vérifier

API FastAPI
L'API FastAPI offre plusieurs points d'accès pour interagir avec les modèles de recommandation et de prédiction de notes.

1. Route de Base

GET / 
Retourne un message de bienvenue.

2. Route pour obtenir des recommandations

POST /recommend
GET /recommend
Prend en entrée un user_id et un game_id et renvoie des jeux recommandés basés sur ces informations.

3. Route pour prédire la note d'un jeu

POST /predict_rating
GET /predict_rating
Prend en entrée un user_id et un game_id et renvoie la note prédite que l'utilisateur pourrait attribuer à ce jeu.

4. Route d'entraînement

POST /train
Permet d'entraîner le modèle avec les données des utilisateurs et des jeux. Cette route est un point de départ pour réentraîner le modèle avec de nouvelles données.

5. Route /data

GET /data
Retourne un message indiquant que les données sont accessibles via cette route.

Logique de recommandation et prédiction
Les modèles de recommandation et de prédiction sont basés sur KNN (K-Nearest Neighbors). Ils utilisent les données des utilisateurs et des jeux pour effectuer des prédictions sur les jeux que les utilisateurs pourraient aimer et les notes qu'ils pourraient attribuer à ces jeux.

Modèle KNN pour la prédiction de notes :

from sklearn.neighbors import KNeighborsRegressor
from sklearn.preprocessing import LabelEncoder

def retrain_knn_model(game_data):
    """
    Réentraîne le modèle KNN avec les nouvelles données.
    """
    label_encoder_user = LabelEncoder()
    label_encoder_game = LabelEncoder()

    game_data['user_id'] = label_encoder_user.fit_transform(game_data['user_id'])
    game_data['game_id'] = label_encoder_game.fit_transform(game_data['game_id'])
    
    X = game_data[['user_id', 'game_id']]
    y = game_data['rating']
    
    knn_model = KNeighborsRegressor(n_neighbors=5)
    knn_model.fit(X, y)
    
    knn_recommend_model = KNeighborsRegressor(n_neighbors=5)
    knn_recommend_model.fit(X)
    
    return knn_model, knn_recommend_model
Modèle KNN pour les recommandations :

from sklearn.neighbors import NearestNeighbors

def get_recommendations(user_id: int, game_id: int):
    """
    Récupère une liste de jeux recommandés pour un utilisateur et un jeu donnés.
    """
    user_game_data = pd.DataFrame([[user_id, game_id]], columns=['user_id', 'game_id'])
    distances, indices = knn_recommend_model.kneighbors(user_game_data)

    recommended_games = game_data.iloc[indices[0]]['game_id'].tolist()
    return recommended_games
Démarrer l'API

Pour démarrer l'API FastAPI, vous pouvez utiliser Uvicorn :

uvicorn app:app --reload
Cela démarrera le serveur de développement. Vous pouvez maintenant accéder à l'API via http://127.0.0.1:8000.

Tester l'API

Vous pouvez tester les routes de l'API en utilisant curl, Postman ou en accédant directement à l'URL dans votre navigateur. Par exemple, pour obtenir des recommandations :

curl -X 'GET' \
  'http://127.0.0.1:8000/recommend?user_id=1&game_id=108' \
  -H 'accept: application/json'
Cela renverra une liste de jeux recommandés pour l'utilisateur ayant l'ID 1 et le jeu ayant l'ID 108.

Remarques

Assurez-vous que le fichier donneesjeux.csv est dans le bon format avec les colonnes user_id, game_id, et rating pour que le modèle fonctionne correctement.
Vous pouvez réentraîner le modèle avec de nouvelles données en utilisant la route /train.
Auteurs

Développé par [Votre Nom].
Pour toute question ou contribution, veuillez ouvrir une issue ou un pull request.

Ce fichier `README.md` est maintenant complet avec toutes les étapes et le code pour configurer et utiliser votre API de recommandation de jeux avec FastAPI et KNN. Vous pouvez simplement le copier-coller dans votre projet.





