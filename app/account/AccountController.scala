package account

import play.api.libs.json.{JsError, JsValue, Json, OFormat}
import play.api.mvc._

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class AccountController @Inject()(val cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {
  def register: Action[JsValue] = Action.async(parse.json) { request =>
    val req = request.body.validate[RegistrationData]
    val futureJson: JsValue = {
      Json.obj("status" -> "Testing")
    }
    req.fold(
      err => {
        Future.successful(
          BadRequest(
            Json
              .obj("message" -> "Invalid field(s)", "errors" -> JsError.toJson(err))
          )
        )
      },
      success => {
        Future.successful(Created(futureJson))
      }
    )
  }
}
