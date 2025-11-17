package uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.integration

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.web.reactive.server.returnResult
import uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.model.ApprovedPremisesStay
import java.time.LocalDate
import java.util.UUID

class PlacementControllerTest : IntegrationTestBase() {
  @Test
  fun `Returns previous AP stays for person when called`() {
    val response = webTestClient.get().uri("/person/${UUID.randomUUID()}/profile/cas1/placements")
      .headers(setAuthorisation(roles = listOf("ROLE_PROBATION")))
      .exchange()
      .expectStatus()
      .isOk
      .returnResult<ApprovedPremisesStay>()
      .responseBody
      .collectList()
      .block()!!
    assertThat(response).hasSize(1)
    assertThat(response[0].name).isEqualTo("Elmswood House")
    assertThat(response[0].arrivalDate).isEqualTo(LocalDate.of(2024, 3, 15))
    assertThat(response[0].departureDate).isEqualTo(LocalDate.of(2024, 6, 20))
    assertThat(response[0].departureReason).isEqualTo("Planned move on")
  }

  @Test
  fun `GET person placement profile returns 200 with profile body`() {
    val crn = "X123456"
    val placementId = UUID.randomUUID()

    webTestClient.get()
      .uri("/person/$crn/profile/cas1/placements/$placementId")
      .headers(setAuthorisation(roles = listOf("ROLE_PROBATION")))
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("$.id").isEqualTo(placementId.toString())
      .jsonPath("$.applicationId").isNotEmpty
      .jsonPath("$.premisesName").isEqualTo("River View Approved Premises")
      .jsonPath("$.expectedArrivalDate").isNotEmpty
      .jsonPath("$.expectedDepartureDate").isNotEmpty
      .jsonPath("$.canonicalArrivalDate").isNotEmpty
      .jsonPath("$.canonicalDepartureDate").isNotEmpty
  }
}
