package account

import play.api.libs.json.{Json, Reads, Writes}

case class RegistrationData(
                             firstName: String,
                             lastName: String,
                             email: String,
                             password: String,
                             phone: Option[String] = None
                           )

object RegistrationData {
  implicit val registrationDataReads: Reads[RegistrationData] = Json.reads[RegistrationData]
  implicit val registrationDataWrites: Writes[RegistrationData] = Json.writes[RegistrationData]
}

