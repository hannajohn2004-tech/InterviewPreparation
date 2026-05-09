import os
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
import pickle

# Path to the dataset
DATA_DIR = "./data"

# Load data
data = []
labels = []

for label in os.listdir(DATA_DIR):
    folder_path = os.path.join(DATA_DIR, label)
    if not os.path.isdir(folder_path):
        continue

    for file in os.listdir(folder_path):
        if file.endswith(".npy"):
            file_path = os.path.join(folder_path, file)
            landmarks = np.load(file_path)
            data.append(landmarks)
            labels.append(label)

# Convert to numpy arrays
data = np.array(data)
labels = np.array(labels)

# Flatten the landmarks
data = data.reshape(data.shape[0], -1)

# Split data
X_train, X_test, y_train, y_test = train_test_split(data, labels, test_size=0.2, random_state=42)

# Train model
model = KNeighborsClassifier(n_neighbors=5)
model.fit(X_train, y_train)

# Evaluate
accuracy = model.score(X_test, y_test)
print(f"✅ Model trained with {accuracy * 100:.2f}% accuracy.")

# Save model
with open("gesture_recognition_model.pkl", "wb") as f:
    pickle.dump(model, f)

print("📦 Model saved as gesture_recognition_model.pkl")
