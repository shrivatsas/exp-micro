const express = require('express')
const axios = require('axios');
const Consul = require('consul');

const { countAllRequests } = require("./monitoring");

const app = express()
app.use(countAllRequests());
const port = 3001

const consul = new Consul({
  host: '127.0.0.1',
  port: 8500,
  promisify: true,
});

consul.agent.service.register({
  name: serviceName,
  address: '192.168.20.193',
  port: 3000,
  check: {
      http: 'http://192.168.20.193:3000/health',
      interval: '10s',
      timeout: '5s',
  }
}, function(err, result) {
  if (err) {
      console.error(err);
      throw err;
  }

  Console.log(servicename + 'registered successfully!');
})

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

