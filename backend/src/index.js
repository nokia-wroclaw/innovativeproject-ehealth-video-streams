const express = require('express');


const SERVER_PORT = 3000;

const app = express();

app.get('/', (req, res) => {
  return res.status(200)
    .send({ 'message': 'Hello!' });
});

const server = app.listen(SERVER_PORT, () => {
  console.log(`Server started on port ${SERVER_PORT}`);
});