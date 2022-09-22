// Firebase Cloud Messaging Configuration File.
// Read more at https://firebase.google.com/docs/cloud-messaging/js/client && https://firebase.google.com/docs/cloud-messaging/js/receive

import { initializeApp } from 'firebase/app';
import { getMessaging, getToken, onMessage } from 'firebase/messaging';

var firebaseConfig = {
    apiKey: "AIzaSyDanlXLdoEjy7rXhvZtPIxsaA-nz97N73A",
    authDomain: "piktekk-dev-f6c54.firebaseapp.com",
    projectId: "piktekk-dev-f6c54",
    storageBucket: "piktekk-dev-f6c54.appspot.com",
    messagingSenderId: "47934776172",
    appId: "1:47934776172:web:98111468791ac502b60949"
};

initializeApp(firebaseConfig);

const messaging = getMessaging();
const keyPair = 'BHSmUzgzJhLDzh8Kl04CnAKsV7HyrkOiizUHToWktwWS6GU0tbcmdgZg5OOfI1Ds6O9Pp8z1_R6wyiP5fYnF7nI';

export const requestForToken = () => {
  return getToken(messaging, { vapidKey: keyPair })
    .then((currentToken) => {
      if (currentToken) {
        console.log('current token for client: ', currentToken);
        // Perform any other neccessary action with the token
      } else {
        // Show permission request UI
        console.log('No registration token available. Request permission to generate one.');
      }
    })
    .catch((err) => {
      console.log('An error occurred while retrieving token. ', err);
    });
};

// Handle incoming messages. Called when:
// - a message is received while the app has focus
// - the user clicks on an app notification created by a service worker `messaging.onBackgroundMessage` handler.
export const onMessageListener = () =>
  new Promise((resolve) => {
    onMessage(messaging, (payload) => {
      resolve(payload);
    });
  });


