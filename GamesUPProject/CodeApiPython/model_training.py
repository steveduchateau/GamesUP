import pandas as pd
from sklearn.neighbors import KNeighborsRegressor
from sklearn.preprocessing import LabelEncoder

def retrain_knn_model(game_data):
    # Préparation des nouvelles données pour l'entraînement
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
