package example

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class SimpleExample extends Simulation {
    
    val httpConf = http.baseUrl("http://www.google.com.br/")
        .header("Accept", "application/json")

    val demoScenario = scenario("Sanity")
        .exec(http("Getting all games")
        .get("search?q=videogames")
         )
         .exec(http("Getting all consoles")
        .get("search?q=consoles")
         )

    setUp(
        demoScenario.inject(
               rampConcurrentUsers(0) to 20 during (20 seconds)
        )
    ).protocols(httpConf)

}