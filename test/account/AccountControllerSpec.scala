package account

import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.http.Status.{BAD_REQUEST, CREATED}
import play.api.libs.json.Json
import play.api.test.Helpers.{defaultAwaitTimeout, status}
import play.api.test.{FakeRequest, Injecting}


class AccountControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  "AccountController POST" should {

    // given
    "return HTTP 201 on account creation" in {
      val data = RegistrationData("John", "Smith", "john.smith", "password1", Option("123456789"))
      val request = FakeRequest("POST", "/api/v1/user/register")
        .withHeaders("Content-Type" -> "application/json")
        .withBody(Json.toJson(data))
      val controller = inject[AccountController]

      // when
      val response = controller.register.apply(request)

      // then
      status(response) mustBe CREATED
    }

    "return HTTP 400 if any required fields are missing on account creation" in {
      // given
      val invalidData = Json.obj("firstName" -> "John", "lastName" -> "Smith")
      val request = FakeRequest("POST", "/api/v1/user/register")
        .withHeaders("Content-Type" -> "application/json")
        .withBody(Json.toJson(invalidData))
      val controller = inject[AccountController]

      // when
      val response = controller.register.apply(request)

      // then
      status(response) mustBe BAD_REQUEST
    }
  }
}
