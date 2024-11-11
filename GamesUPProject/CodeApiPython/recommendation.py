import pandas as pd
from sklearn.preprocessing import LabelEncoder
from sklearn.neighbors import KNeighborsRegressor, NearestNeighbors
import os

# Charger les données depuis le fichier CSV
file_path = '/Users/steveduchateaumelaniesteve/Desktop/donneesjeux.csv'  # Remplacez par le chemin réel
game_data = pd.DataFrame()

# Vérification si le fichier existe avant de le charger
if os.path.exists(file_path):
    game_data = pd.read_csv(file_path, sep=';')
    print(game_data.head())  # Afficher les premières lignes pour vérifier
else:
    print(f"Le fichier à l'emplacement '{file_path}' n'a pas été trouvé. Veuillez vérifier le chemin.")
    exit()

# Vérification des colonnes nécessaires
if 'user_id' not in game_data.columns or 'game_id' not in game_data.columns or 'rating' not in game_data.columns:
    print("Les colonnes 'user_id', 'game_id' et 'rating' doivent être présentes dans le fichier CSV.")
    exit()

# Encodage des colonnes 'user_id' et 'game_id' pour les rendre numériques
label_encoder_user = LabelEncoder()
label_encoder_game = LabelEncoder()

game_data['user_id'] = label_encoder_user.fit_transform(game_data['user_id'])
game_data['game_id'] = label_encoder_game.fit_transform(game_data['game_id'])

# Préparation des données pour l'entraînement
X = game_data[['user_id', 'game_id']]  # Colonnes à utiliser comme caractéristiques
y = game_data['rating']  # Cible (les évaluations des jeux)

# Traitement des valeurs manquantes
X = X.fillna(0)  # Remplacer les NaN par 0 ou une autre stratégie

# Initialiser et entraîner le modèle KNN pour régression
knn_model = KNeighborsRegressor(n_neighbors=3)
knn_model.fit(X, y)

# Initialisation du modèle KNN pour recommandation basé sur les plus proches voisins
knn_recommend_model = NearestNeighbors(n_neighbors=3)

# Entraînement du modèle pour les recommandations
def train_knn_recommendation():
    # Nous utilisons les données d'entrée pour l'entraînement du modèle de recommandation
    knn_recommend_model.fit(X)

# Entraîner le modèle de recommandation
train_knn_recommendation()

# Fonction pour obtenir des recommandations
def get_recommendations(user_id: int, game_id: int):
    user_game_data = pd.DataFrame([[user_id, game_id]], columns=['user_id', 'game_id'])
    distances, indices = knn_recommend_model.kneighbors(user_game_data)

    # Renvoyer les recommandations basées sur les indices
    recommended_games = game_data.iloc[indices[0]]['game_id'].tolist()
    return recommended_games

# Fonction pour prédire la note d'un jeu pour un utilisateur
def predict_rating(user_id: int, game_id: int):
    user_game_data = pd.DataFrame([[user_id, game_id]], columns=['user_id', 'game_id'])
    predicted_rating = knn_model.predict(user_game_data)
    return predicted_rating[0]
