from pydantic import BaseModel
from typing import List

# Modèle pour un achat d'utilisateur
class UserPurchase(BaseModel):
    game_id: int
    rating: float

# Modèle pour les données d'utilisateur avec une liste de ses achats
class UserData(BaseModel):
    user_id: int
    purchases: List[UserPurchase]
