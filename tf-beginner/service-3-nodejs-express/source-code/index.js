const serverless = require('serverless-http');
const express = require('express');
const app = express();
app.use(express.json());
app.get('/health', (req, res) => res.send('Success'));

const router = express.Router();
app.use('/service3', router);

router.get('/', (req, res) => res.send('GET request'));
router.post('/', (req, res) => res.send('POST request'));
router.put('/', (req, res) => res.send('PUT request'));
router.delete('/', (req, res) => res.send('DELETE request'));
router.get("/auth", (req, res, next) => {
  const cdate = new Date();

  return res.status(200).json({
    message: `Hello from auth path! ${cdate}`,
  });
});
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
