import os
import cv2
import time
import mediapipe as mp
import numpy as np
import pickle
import pyttsx3

# Load model
with open("gesture_recognition_model.pkl", "rb") as f:
    model = pickle.load(f)

# Get labels
LABELS = os.listdir("data")
LABELS.sort()  # Ensure consistent label ordering

# Init MediaPipe & Text-to-Speech
mp_hands = mp.solutions.hands
hands = mp_hands.Hands()
mp_draw = mp.solutions.drawing_utils
engine = pyttsx3.init()

# Capture from webcam
cap = cv2.VideoCapture(0)

# To track previously spoken label
last_label = ""
last_spoken_time = time.time()


while True:
    ret, frame = cap.read()
    image = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
    results = hands.process(image)

    if results.multi_hand_landmarks:
        for hand_landmarks in results.multi_hand_landmarks:
            data = []
            for lm in hand_landmarks.landmark:
                data.extend([lm.x, lm.y])
            
            if len(data) == 42:
                prediction = model.predict([data])
                predicted_label = prediction[0]

                # Display
                cv2.putText(frame, predicted_label, (20, 50), cv2.FONT_HERSHEY_SIMPLEX, 1.5, (0, 255, 0), 3)

                # Speak if label changed OR 2 seconds have passed
                current_time = time.time()
                if predicted_label != last_label or (current_time - last_spoken_time > 2):
                    engine.say(predicted_label)
                    engine.runAndWait()
                    last_label = predicted_label
                    last_spoken_time = current_time

                mp_draw.draw_landmarks(frame, hand_landmarks, mp_hands.HAND_CONNECTIONS)

    cv2.imshow("Real-Time Translator", frame)
    if cv2.waitKey(1) == 27:  # Press ESC to exit
        break

cap.release()
cv2.destroyAllWindows()
