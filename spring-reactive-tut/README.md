# Popular purchases API

A new feature of the Discount Ascii Warehouse ecommerce platform.

## Install and start the server
###### Prerequisite: Java 8 or higher version of Java must be run on test machine.
###### Execute shell: 
- `./gradlew build -x test`
- `java -jar -Dserver.port=8080 -Dapp.external.host="http://localhost:8000" ./build/libs/demo-0.0.1-SNAPSHOT.jar`



Explain :
 - `-Dserver.port=8080`: run backend server with port 8080 (port default = 8080).
 - `-Dapp.external.host="http://localhost:8000"`: External API address to get `users`, `purchase`, `product` (address default = http://localhost:8000) 


Now you can make API requests, as: `curl -i localhost:8080/api/recent_purchases/{user_name}`

Example: 
1. Get users from external API: `curl http://localhost:8000/api/users`  with response like this:
```$xslt
{
  "users": [
    {
      "username": "Jeramie.Lockman",
      "email": "Jeramie.Lockman@gmail.com"
    },
    {
      "username": "Johnpaul.Sporer60",
      "email": "Johnpaul.Sporer60@gmail.com"
    },
    {
      "username": "Enoch.MacGyver",
      "email": "Enoch.MacGyver@gmail.com"
    },
    ...
}
```

2. Call Popular Purchase API: `curl -i localhost:8080/api/recent_purchases/Jeramie.Lockman` the response will be like this:
```$xslt
[
  {
    "id": "371558",
    "face": "(　´･‿･｀)",
    "price": 280,
    "size": 19,
    "recent": [
      "Jeramie.Lockman",
      "Johnpaul.Sporer60",
      "Hassan.Bradtke",
      "Mckenna_DuBuque6",
      "Libby_Hoppe",
      "Eliseo99",
      "Alice.Denesik",
      "Ollie_Smitham",
      "Orin15",
      "Luella_Rodriguez90"
    ]
  },
  {
    "id": "380335",
    "face": "¬_¬",
    "price": 793,
    "size": 22,
    "recent": [
      "Jeramie.Lockman",
      "Gerry.Klocko52",
      "Libby_Hoppe",
      "Eliseo99",
      "Maddison_Ritchie",
      "Zella60",
      "Audie62",
      "Carmela_Ritchie97",
      "Carmela_Ritchie97",
      "Vernie50"
    ]
  },
  ...
]  
```


