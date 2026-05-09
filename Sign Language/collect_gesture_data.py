import cv2
import os
import numpy as np
import mediapipe as mp

# Setup
mp_hands = mp.solutions.hands
hands = mp_hands.Hands(static_image_mode=False, max_num_hands=1)
mp_draw = mp.solutions.drawing_utils

GESTURES = ['Hello', 'No', 'Yes', 'Thanks', 'OK']
DATA_DIR = 'gesture_data'

if not os.path.exists(DATA_DIR):
    os.mkdir(DATA_DIR)

cap = cv2.VideoCapture(0)

for label in GESTURES:
    print(f"\n📸 Get ready to record: {label}")
    os.makedirs(os.path.join(DATA_DIR, label), exist_ok=True)
    input("Press Enter to start recording...")

    count = 0
    while count < 200:
        ret, frame = cap.read()
        image = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
        results = hands.process(image)

        if results.multi_hand_landmarks:
            for hand_landmarks in results.multi_hand_landmarks:
                mp_draw.draw_landmarks(frame, hand_landmarks, mp_hands.HAND_CONNECTIONS)
                landmarks = []

                for lm in hand_landmarks.landmark:
                    landmarks.extend([lm.x, lm.y])

                if len(landmarks) == 42:  # 21 x and y
                    np.save(os.path.join(DATA_DIR, label, f"{count}.npy"), np.array(landmarks))
                    count += 1

        cv2.putText(frame, f"{label}: {count}/200", (10, 30),
                    cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 0), 2)
        cv2.imshow("Recording", frame)

        if cv2.waitKey(1) == 27:  # ESC to exit
            break

cap.release()
cv2.destroyAllWindows()
print("✅ Data collection complete!")
print(f"Data saved in {DATA_DIR} directory.")