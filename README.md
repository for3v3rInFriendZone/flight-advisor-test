# Flight advisor

Flight advisor is a small `Java (Spring)` app which introduces solution for finding the cheapest flight between two cities in the whole world by using `Dijkstra` algorithm in the background. 

Enter two cities: `source` and `destination` and the result will provide the cheapest flight routes to the destination city.


## Installation

`Clone` this repository and you will get a folder - `flight-advisor-test`.

`Open Project` in any favourable IDEA and run it as `Spring application`. 

On the other side, if you are an old fashioned kind of guy, you can use `terminal`. \
Place yourself inside of `flight-advisor-test` and run:

```bash
mvn clean package
java -jar target/advisor-0.0.1-SNAPSHOT.jar
```

## Usage

![user](https://user-images.githubusercontent.com/16151417/98465160-98298b00-21c7-11eb-9500-f8cfea2947c7.png)

Above you can see `flight-advisor` database structure which can help you with visualisation.

Inside `files` folder you will find `flight-advisor.postman_collection.json` file for this app with all the requests predefined. \
Use below explained endpoints as a guide.

Currently, `root url` is set to `localhost:8080/api`

### Notable precondition 
Since there are two `.txt` files provided - `files/airports.txt` and `files/routes.txt` which are used to populate all available airports and their possible routes, we should import them first by using the following APIs:
- `POST /upload/airports` - Upload `airports.txt` using this API (this will also create cities which are mentioned in this file). 

- `POST /upload/routes` - Upload `routes.txt` using this API (this will also populate graph for searching cheapest routes).    
#
**User**
* `POST /user` - Creates new `User` which has `UserType` of `REGULAR`.
* `POST /user/sign-in` - Login `User` returns `Jws Token` which is used for all other APIs in `Authorization` header.
#
**City**
* `GET /city` - Get all the available cities. For each city, there is a list of comments, which can be paginated. Comments are ordered by `created_date`. Since there can be a lot of imported cities, there is pagination for the cities as well.
   * `commentsLimit` - Limit the list of comments for the city.
   * `page` and `size` - Traditional pagination for getting all the cities. Default value is set to 
   `0` and `200` respectively.
* `GET /city/search` - Find all available cities by name. Paging is introduced here as well.
   * `commentsLimit` - Limit list of comments for the city. 
   * `page` and `size` - Traditional pagination for getting all the cities. Default value is set to 
   `0` and `200` respectively. 
   * `name` - Name of the city we are looking for.
* `POST /city` - Creates a new city. Only `UserType = ADMIN` can do this.
* `POST /city/{id}/comment` - Creates a new comment for the city.
* `POST /city/flight` - The most important API here. Provide precise names of `source` and `destination` cities and response will contain the cheapest flights available.
#
**Comment**
* `PATCH /comment/{id}` - Update the provided comment with new text.
* `DELETE /comment/{id}` - Delete the provided comment.

## Notable mention
**Internationalisation** is also implemented in this application. \
Currently available translations are in English `en` (this is a default) and in Serbian `sr`. \
Just add `Accept-Language` Header in order to see translated messages.

## License
N/A - Intended for demonstration purposes only.
