var axios = require('axios');
var data = require('./dailyData.json');

data.sections[0].activityTitle = 'Mọi người gửi report ngày hôm nay nhé '  + new Date().toLocaleDateString('en-GB') + ' !!!';

const getConfig = () => {
  const reqUrl = process.env.REQ_URL;
  console.log('reqUrl' , reqUrl);

  var config = {
    method: 'post',
    url: reqUrl,
    headers: {
      'Content-Type': 'application/json'
    },
    data : data
  };
  return config;
}

axios(getConfig())
.then(function (response) {
  console.log(JSON.stringify(response.data));
})
.catch(function (error) {
  console.log(error);
});