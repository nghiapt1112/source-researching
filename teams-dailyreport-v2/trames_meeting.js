var axios = require('axios');
var data = require('./dailyMeeting.json');

data.sections[0].activityTitle = new Date().toLocaleDateString('en-GB') + ' Daily meeting mọi người nhé !!!';
data.sections[0].activitySubtitle = "On Project Trames - Auto send";
data.sections[0].facts.push({
    "name": "Link vào họp",
    "value": "[Bấm vào link này để vào cuộc họp](https://teams.microsoft.com/l/meetup-join/19%3ameeting_MjM5MWVjYmQtMzJiMi00ZjMyLWFlZTgtNjc5ODQ1YTA0MGZk%40thread.v2/0?context=%7b%22Tid%22%3a%222f173612-6f45-4520-9df8-32d7ba5f2c44%22%2c%22Oid%22%3a%224102a2af-2d8f-4421-8022-ef32fe2e7e82%22%7d)"
});
var config = {
    method: 'post',
    url: 'https://sgcmccomvn.webhook.office.com/webhookb2/0eeb1f18-1d5c-4ecf-8ea0-b6b7216a381d@2f173612-6f45-4520-9df8-32d7ba5f2c44/IncomingWebhook/6ecd21f5bd5c43708fa449705d144f45/4102a2af-2d8f-4421-8022-ef32fe2e7e82',
    headers: {
        'Content-Type': 'application/json'
    },
    data : data
};

axios(config)
    .then(function (response) {
        console.log(JSON.stringify(response.data));
    })
    .catch(function (error) {
        console.log(error);
    });

