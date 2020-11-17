# http4k-kodein-exposed
This project is a sample service using [http4k](https://www.http4k.org/) (with Netty) and 
[Exposed](https://github.com/JetBrains/Exposed) ORM. At some point it will also leverage [Kodein](https://kodein.org/di/) 
for dependency injection. The ultimate goal is to incorporate a similar sample service into 
[cautious-engine](https://github.com/vitdebacco/cautious-engine) to test the performance of various frameworks.

This project was initialized as a new Gradle project via IntelliJ.

### Build and Run
1. `docker-compose up`
2. `./gradlew run`

### cURL Requests (w/ jq)
#### Offerings
##### Create
To create an Offering, you must supply `name`, `tasting_notes`, `description`, `url`, and `roaster_name`.
```
curl -X POST 'http://localhost:8080/offerings' -H 'Content-Type: application/json' -d '{                                                                            130 ↵
  "name": "Ethiopia Kochere & Yirgacheffe Jabanto",
  "tasting_notes": "Blueberry, Chocolate, Honey",
  "description": "The Jabanto group formed in 2017, after changes in the Ethiopian coffee policy permitted smallholder farmers to be able to directly export and sell their coffees. The group has worked hard to build an enterprising business from scratch and their commitment to careful harvesting and processing each year results in some of our favorite offerings. This year’s production of Jabanto Natural Sundried tastes like blueberry, chocolate, and honey.",
  "url": "https://counterculturecoffee.com/shop/coffee/jabanto-natural-sundried",
  "roaster_name": "Counter Culture Coffee"
}' | jq
```

##### Get All
```
curl 'http://localhost:8080/offerings' | jq
```

##### Get By ID
```
curl 'http://localhost:8080/offerings/{id}' | jq
```

##### Update
Only `description` is updatable.
```
curl -X PUT 'http://localhost:8080/offerings/{id}' -H 'Content-Type: application/json' -d '{ "description":"Updated Description" }' | jq
```

##### Delete
```
curl -X DELETE 'http://localhost:8080/offerings/{id}' | jq
```

#### Ratings
##### Create
```
curl -X POST 'http://localhost:8080/offerings/61d43952-345e-4648-93d2-d148a84a6c17/ratings' -H 'Content-Type: application/json' -d '{ "value": 5, "comment":"tastes like blueberries and honey!", "offering_id":"61d43952-345e-4648-93d2-d148a84a6c17", "username":"vit" }' | jq
```

```
curl -X POST 'http://localhost:8080/offerings/61d43952-345e-4648-93d2-d148a84a6c17/ratings' -H 'Content-Type: application/json' -d '{ "value": 3, "comment":"it was better last year", "offering_id":"61d43952-345e-4648-93d2-d148a84a6c17", "username":"notvit" }' | jq
```

```
curl -X POST 'http://localhost:8080/offerings/61d43952-345e-4648-93d2-d148a84a6c17/ratings' -H 'Content-Type: application/json' -d '{ "value": 1, "comment":"this tastes like blueberry. gross", "offering_id":"61d43952-345e-4648-93d2-d148a84a6c17", "username":"definitelynotvit" }' | jq
```

##### Get All
```
curl 'http://localhost:8080/offerings/61d43952-345e-4648-93d2-d148a84a6c17/ratings' | jq
```

### Helpful Links
Examples and linked projects from [http4k](https://www.http4k.org/)

[http4k](https://github.com/http4k/http4k) on GitHub

This example in particular: [Real World App (Medium Clone)](https://github.com/alisabzevari/kotlin-http4k-realworld-example-app)

[Guide to the Kotlin Exposed Framework](https://www.baeldung.com/kotlin-exposed-persistence)