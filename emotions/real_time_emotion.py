from keras.preprocessing.image import img_to_array
import cv2
from keras.models import load_model
import numpy as np

# model paths
haar_cascade_path = 'haar_cascade/haarcascade_frontalface_default.xml'
emotion_model_path = 'models/model_CNN.30-0.66.hdf5'

font = cv2.FONT_HERSHEY_SIMPLEX

# loading models
face_detection = cv2.CascadeClassifier(haar_cascade_path)
# without compile=False code won't compile - Tensorflow
emotion_model = load_model(emotion_model_path, compile=False)

# labels of  emotion in polish language because of target user
emotions_labels = ['Zlosc', 'Zniesmaczenie', 'Strach', 'Radosc', 'Smutek', 'Zaskoczenie', 'Obojetnosc']

# starting video streaming
cv2.namedWindow('Emocje')
camera = cv2.VideoCapture(0)
while True:
	frame = camera.read()[1]
	cv2.putText(frame, "Wcisnij Q zeby wylaczyc", (460, 700), font, 0.75, (255, 0, 0), 2, cv2.LINE_AA)
	gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
	# detect face
	faces = face_detection.detectMultiScale(gray, scaleFactor=1.3, minNeighbors=5, minSize=(100, 100),
											flags=cv2.CASCADE_SCALE_IMAGE)

	# When face is detected
	if len(faces) > 0:
		for (x, y, w, h) in faces:
			cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 0, 205), 2)
			face = gray[y:y + h, x:x + w]
			# resize the image to be passed to the neural network
			resized_face = cv2.resize(face, (48, 48))
			resized_face = resized_face.astype("float32") / 255.0
			resized_face = img_to_array(resized_face)
			resized_face = np.expand_dims(resized_face, axis=0)

			predictions = emotion_model.predict(resized_face)[0]
			emotion_probability = np.max(predictions)
			# the dominant emotion is the one with the highest probability
			label = emotions_labels[predictions.argmax()]

			for index, (emotion, prob) in enumerate(zip(emotions_labels, predictions)):
				# construct the label text
				emotion_result = "{}: {:0.2f}%".format(emotion, prob * 100)
				cv2.putText(frame, emotion_result, (10, index * 20 + 20), font, 0.75, (0, 0, 205), 2)
				cv2.putText(frame, label, (x, y - 10), font, 1, (0, 0, 205), 2)
	else:
		continue

	cv2.imshow('Emocje', frame)
	if cv2.waitKey(1) & 0xFF == ord('q'):
		break

camera.release()
cv2.destroyAllWindows()
