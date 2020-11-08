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

You can find `Postman.collections` file in this repository with all the requests predefined. \
Use below list as a guide and explanations for endpoints.

Currently, `root url` is set to `localhost:8080/api`

- `POST /user` - Creates new `User` which has a `UserType` of `REGULAR`.
- `POST /user/sign-in` - Login `User` returns a `Jws Token` which is used for all other APIs in `Authorization` header.
- `GET /city` - Get all cities available. For each city, there is a list of comments, which can be paginated by providing `commentsLimit` as `Request param`.

```python
import foobar

foobar.pluralize('word') # returns 'words'
foobar.pluralize('goose') # returns 'geese'
foobar.singularize('phenomena') # returns 'phenomenon'
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
