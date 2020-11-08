# Flight advisor

Flight advisor is a small `Java (Spring)` app which introduces solution for finding a cheapest flight between two cities in the whole world by using `Dijkstra` algorithm in the background. 

Enter two cities: `source` and `destination` and result will provide cheapest flight routes to destination city.


## Installation

`Clone` this repository and you will get folder - `flight-advisor-test`.

`Open Project` in any favourable IDEA and run it as `Spring application`. 

On the other side, if you are old fashioned kind of guy, you can use `terminal`. \
Place yourself inside of `flight-advisor-test` and run:

```bash
mvn clean package
java -jar target/advisor-0.0.1-SNAPSHOT.jar
```

## Usage

![user](https://user-images.githubusercontent.com/16151417/98465160-98298b00-21c7-11eb-9500-f8cfea2947c7.png)

Above you can see `flight-advisor` database structure which can help you visualise what this small app can do.

You will find `Postman.collections` file in this repository with all the requests predefined. \
Use below explained endpoints as a guide.

Currently, `root url` is set to `localhost:8080/api`

### Notable precondition 
Since there are two `.txt` files provided - `airports.txt` and `routes.txt` which are used to populate all available airports and theirs possible routes, we should import them first by using following APIs:
- `POST /upload/airports` - Upload `airports.txt` using this API (this will also create cities which are mentioned in this file). 

- `POST /upload/routes` - Upload `routes.txt` using this API (this will also populate graph for searching cheapest routes).    
#
**User**
* `POST /user` - Creates new `User` which has a `UserType` of `REGULAR`.
* `POST /user/sign-in` - Login `User` returns a `Jws Token` which is used for all other APIs in `Authorization` header.
#
**City**
* `GET /city` - Get all cities available. For each city, there is a list of comments, which can be paginated. Comments are ordered by `created_date`. Since there can be a lot of imported cities, there is pagination for city as well.
   * `commentsLimit` - Limit list of comments for city.
   * `page` and `size` - Traditional pagination for getting all the cities. Default value is set to 
   `0` and `200` respectively.
* `GET /city/search` - Find all available cities by name. Paging is introduces here as well.
   * `commentsLimit` - Limit list of comments for city. 
   * `page` and `size` - Traditional pagination for getting all the cities. Default value is set to 
   `0` and `200` respectively. 
   * `name` - Name of city we are looking for.
* `POST /city` - Creates new city. Only `UserType = ADMIN` can do this.
* `POST /city/{id}/comment` - Creates new comment for a city.
* `POST /city/flight` - Most important API in here. Provide precise names of `source` and `destination` cities and response will contain cheapest flights available.
#
**Comment**
* `PATCH /comment/{id}` - Update provided comment with new text.
* `DELETE /comment/{id}` - Delete provided comment.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
