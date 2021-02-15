package com.envylabs.cautiousengine

import io.gatling.core.Predef.{exec, _}
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.util.Random

class CautiousEngineSimulation extends Simulation {
  val httpConf = http
    .baseUrl(baseUrl)
    .header("Accept", "application/json")

  def userCount: Int = getProperty("loadtests.users", "1000").toInt

  def baseUrl: String = getProperty("loadtests.baseurl", "http://localhost:8080")

  def rampDuration: Int = getProperty("loadtests.rampduration", "10").toInt

  def testDuration: Int = getProperty("loadtests.duration", "60").toInt

  /** * Before ** */
  before {
    println(s"baseUrl: ${baseUrl}")
    println(s"Running test with ${userCount} users")
    println(s"Ramping users over ${rampDuration} seconds")
    println(s"Maximum test duration: ${testDuration} seconds")
  }

  /** * Helper Methods ** */
  protected def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

  def getAddresses() = {
    exec(http("addresses")
      .get("/addresses")
      .check(status.is(200)))
  }

  def getAddress() = {
    exec(http("address")
      .get("/addresses/" + Random.nextInt())
      .check(status.is(200)))
  }

  /** * Scenario Design ** */
  val scn = scenario("Cautious Engine Load Test")
    .forever() {
      exec(getAddress())
        .exec(getAddresses())
    }

  /** * Setup Load Simulation ***/
  setUp(
    scn.inject(
      nothingFor(5.seconds),
      rampUsers(userCount).during(rampDuration.seconds))
  ).protocols(httpConf).maxDuration(testDuration.seconds)

  /** * After ***/
  after {
    println("Stress test completed")
  }
}
