const serverless = require('serverless-http');
const express = require('express');
const app = express();
app.use(express.json());

app.get('/', (req, res) => res.send('GET request'));
app.post('/', (req, res) => res.send('POST request'));
app.put('/', (req, res) => res.send('PUT request'));
app.delete('/', (req, res) => res.send('DELETE request'));

// Global exception handler
app.use((err, req, res, next) => {
  console.error(err.stack);
  res.status(500).send('Something broke!');
});

// Logger middleware
app.use((req, res, next) => {
  console.log(`${req.method} ${req.url}`);
  next();
});

module.exports.handler = serverless(app);
