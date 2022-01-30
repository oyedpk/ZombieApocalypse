#  ZombieApocalypse

## Build
```
$ mvn clean install
```

# Depoly the ZombieApocalypse

## Start the springboot app
```
java -jar target/ZombieApocalypse-0.0.1-SNAPSHOT.jar com.apocalypse.ZombieApocalypse.ZombieApocalypseApplication 

or 

mvn spring-boot:run
```

## Test the app
```
App by default started at http://localhost:8080/api/apocalypse

curl -X POST \
  --header 'Content-Type: application/json' \
  --header 'Accept: application/json' \
  -d "`cat ./input.json`" \
  'http://localhost:8080/api/apocalypse'
where
- <ne-ip> is the NE IP address
- ./input.json is...
[
    {
    "grid" : 4,
    "zombiePos" : {"x" : 3, "y" : 2},
    "creaturePosList" : [
        {"x" : 0, "y" : 1},
        {"x" : 1, "y" : 2},
        {"x" : 2, "y" : 1}
    ],
    "directions" : "RDRU"
    }
]
```

## Test the app from swagger
```
simply open below url to try out app
http://localhost:8080/swagger-ui.html
```

##
Demo Test