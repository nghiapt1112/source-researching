const serverless = require('serverless-http');
const express = require('express');
const app = express();

app.get('/', (req, res) => {
    res.send('Hello, world!');
});

// Add your CRUD routes here...

exports.handler = serverless(app);