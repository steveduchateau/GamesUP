import pandas as pd

def load_training_data(file_path):
    """
    Charge un fichier CSV contenant les données des utilisateurs et des jeux.
    """
    return pd.read_csv(file_path)

# Exemple de test de chargement de données avec ton fichier
file_path = "/Users/steveduchateaumelaniesteve/Desktop/donneesjeux.csv"
data = load_training_data(file_path)
print(data.head())  # Affiche les premières lignes des données pour vérifier
