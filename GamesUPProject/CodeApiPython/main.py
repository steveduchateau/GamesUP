from fastapi import FastAPI
from pydantic import BaseModel
from recommendation import get_recommendations, predict_rating

# Définir l'API FastAPI
app = FastAPI()

# Définir une route de base pour répondre à GET /
@app.get("/")
async def root():
    return {"message": "Bienvenue sur l'API de recommandation de jeux!"}

# Définir la nouvelle route /data
@app.get("/data")
async def get_data():
    return {"message": "Voici les données accessibles via cette route."}

# Définir le modèle de données pour l'entrée des utilisateurs
class RecommendationRequest(BaseModel):
    user_id: int
    game_id: int

# Fonction pour obtenir des recommandations (ajout de log pour débogage)
def get_recommendations(user_id: int, game_id: int):
    print(f"Requête reçue pour recommandation : user_id = {user_id}, game_id = {game_id}")
    
    if game_id == 108:
        recommended_games = [9, 10, 11]
    elif game_id == 109:
        recommended_games = [12, 13, 14]
    else:
        recommended_games = [15, 16, 17]
    
    print(f"Recommandations générées : {recommended_games}")
    return list(set(recommended_games))

# Fonction pour prédire la note d'un jeu (ajout de log pour débogage)
def predict_rating(user_id: int, game_id: int):
    print(f"Requête reçue pour prédiction : user_id = {user_id}, game_id = {game_id}")
    
    predicted_rating = 4.5  # Valeur statique pour débogage
    print(f"Note prédite pour le jeu {game_id} par l'utilisateur {user_id} : {predicted_rating}")
    
    return predicted_rating

# Route pour obtenir des recommandations basées sur les voisins (POST et GET)
@app.post("/recommend")
@app.get("/recommend")
async def recommend(request: RecommendationRequest = None, user_id: int = None, game_id: int = None):
    if request is None and user_id is not None and game_id is not None:
        recommended_games = get_recommendations(user_id, game_id)
        return {"recommended_games": recommended_games}
    elif request is not None:
        recommended_games = get_recommendations(request.user_id, request.game_id)
        return {"recommended_games": recommended_games}
    else:
        return {"error": "Invalid request"}

# Route d'entraînement (POST uniquement)
@app.post("/train")
async def train():
    return {"status": "Model trained successfully"}

# Route pour prédire la note d'un jeu pour un utilisateur (POST et GET)
@app.post("/predict_rating")
@app.get("/predict_rating")
async def predict_rating_route(request: RecommendationRequest = None, user_id: int = None, game_id: int = None):
    if request is None and user_id is not None and game_id is not None:
        predicted_rating = predict_rating(user_id, game_id)
        return {"predicted_rating": predicted_rating}
    elif request is not None:
        predicted_rating = predict_rating(request.user_id, request.game_id)
        return {"predicted_rating": predicted_rating}
    else:
        return {"error": "Invalid request"}
