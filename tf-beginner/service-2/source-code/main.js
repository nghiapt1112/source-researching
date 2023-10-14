// const express = require('express');
// const serverlessHttp = require('serverless-http');
//
// const app = express();
//
// app.use(express.json());
//
// // Logger middleware
// app.use((req, res, next) => {
//     console.log(`[${new Date().toISOString()}] ${req.method} - ${req.url}`);
//     next();
// });
//
// // Routes
// app.get('/', (req, res) => {
//     res.json({ message: 'GET request' });
// });
//
// //
// // app.post('/', (req, res) => {
// //     res.json({ message: 'POST request' });
// // });
// //
// // app.put('/', (req, res) => {
// //     res.json({ message: 'PUT request' });
// // });
// //
// // app.delete('/', (req, res) => {
// //     res.json({ message: 'DELETE request' });
// // });
//
// // Global exception handler
// app.use((err, req, res, next) => {
//     console.error(err.stack);
//     res.status(500).send('Something went wrong!');
// });
//
// exports.handler = serverlessHttp(app);


const serverless = require("serverless-http");
const express = require("express");
const app = express();

app.get("/", (req, res, next) => {
    return res.status(200).json({
        message: "Hello from root!",
    });
});

app.get("/path", (req, res, next) => {
    return res.status(200).json({
        message: "Hello from path!",
    });
});

app.use((req, res, next) => {
    return res.status(404).json({
        error: "Not Found",
    });
});

module.exports.handler = serverless(app);



// module.exports.handler = async (event) => {
//     console.log('Event: ', event);
//     let responseMessage = 'Hello, World!';
//
//     return {
//         statusCode: 200,
//         headers: {
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify({
//             message: responseMessage,
//         }),
//     }
// }