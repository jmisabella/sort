package models

import models.behaviors.{ Sorting, RandomOrdering } 
import models.classes.HistoricalList
import models.modules.Factory

import akka.actor._
import play.api.libs.json._
import play.api.libs.json.Json

object SimpleWebSocketActor {
    // DOCS: Props is a ActorRef configuration object, that is immutable, so it is 
    // thread-safe and fully sharable. Used when creating new actors through 
    // ActorSystem.actorOf and ActorContext.actorOf.
    def props(clientActorRef: ActorRef) = Props(new SimpleWebSocketActor(clientActorRef))
}

class SimpleWebSocketActor(clientActorRef: ActorRef) extends Actor {
    val logger = play.api.Logger(getClass)

    logger.info(s"SimpleWebSocketActor class started")

    // this is where we receive json messages sent by the client
    // and send them a json reply
    def receive = {
        case jsValue: JsValue =>
            logger.info(s"JS-VALUE: $jsValue")
            val clientMessage = getMessage(jsValue).trim()
            println("MESSAGE: " + clientMessage)

            val result: HistoricalList[Int] = Factory.run(clientMessage, (0 to 242).toList)

            // // val json: JsValue = Json.parse(s"""{"result": "${result.history.mkString("[", ", ", "]")}"}""")
            // val json: JsValue = Json.parse(s"""{"body": "${result.history.map(_.mkString(",")).mkString("|")}"}""")
            val json: JsValue = Json.parse(s"""{"body": "${result.toString()}"}""")
            clientActorRef ! (json)
    }

    // parse the "message" field from the json the client sends us
    def getMessage(json: JsValue): String = (json \ "message").as[String]

}
