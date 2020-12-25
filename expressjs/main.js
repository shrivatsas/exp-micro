const express = require('express')
const axios = require('axios');

const { countAllRequests } = require("./monitoring");

const app = express()
app.use(countAllRequests());
const port = 3000

app.get('/', (req, res) => {
  res.send('Hello World!')
})

app.get('/random', (req, res) => {
  axios.get('https://uselessfacts.jsph.pl/random.json?language=en')
  .then(r => {
    res.send(r.data.text)
  })
  .catch(err => {
    console.log(err);
    res.send('Hello errld!')
  });
})

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
})

