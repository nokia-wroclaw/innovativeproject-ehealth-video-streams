from keras.callbacks import ModelCheckpoint
from sklearn.model_selection import train_test_split
from emotions.prepare_data import load_data, data_generator
from emotions.cnn_model import build_cnn_model

# Number of emotions
classes_number = 7
input_shape = (48, 48, 1)
epochs = 25
batch_size = 128

trained_model_path = 'models/_CNN_model' + '.{epoch:02d}-{val_acc:.2f}.hdf5'
model_checkpoint = ModelCheckpoint(trained_model_path, 'val_loss', verbose=1, save_best_only=True)

data, labels = load_data()
X_train, X_test, y_train, y_test = train_test_split(data, labels, test_size=0.3, random_state=42)
model = build_cnn_model(classes_number, input_shape)
model.fit_generator(data_generator.flow(X_train, y_train, batch_size),
					epochs=epochs,
					steps_per_epoch=len(X_train) / batch_size,
					verbose=1,
					validation_data=(X_test, y_test),
					callbacks=[model_checkpoint])
model.save(trained_model_path)
score = model.evaluate(X_test, y_test, verbose=0)
print('Test loss:', score[0])
print('Test accuracy:', score[1])
