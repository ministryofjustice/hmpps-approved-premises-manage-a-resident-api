package uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.model.Cas1SpaceCharacteristic
import uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.model.PersonPlacementProfile
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

class PlacementServiceTest {

  private val placementService = PlacementService()

  @Test
  fun `getPreviousApStaysForPerson should return a list of stays for a given CRN`() {
    val crn = UUID.randomUUID()

    val result = placementService.getPreviousApStaysForPerson(crn)

    assertEquals(1, result.size)

    val stay = result[0]
    assertEquals("Elmswood House", stay.name)
    assertEquals(LocalDate.of(2024, 3, 15), stay.arrivalDate)
    assertEquals(LocalDate.of(2024, 6, 20), stay.departureDate)
    assertEquals("Planned move on", stay.departureReason)
  }

  @Test
  fun `getPersonPlacementProfile returns a fully populated dummy profile`() {
    val crn = "X12345"
    val placementId = UUID.randomUUID()

    val today = LocalDate.now()
    val beforeCall = Instant.now()

    val result: PersonPlacementProfile? = placementService.getPersonPlacementProfile(crn, placementId)

    val afterCall = Instant.now()

    assertThat(result).isNotNull
    result!!

    assertThat(result.id).isEqualTo(placementId)
    assertThat(result.applicationId).isNotNull()

    assertThat(result.premisesName).isEqualTo("River View Approved Premises")
    assertThat(result.apType).isEqualTo("Standard AP")

    assertThat(result.expectedArrivalDate).isEqualTo(today.plusDays(5))
    assertThat(result.expectedDepartureDate).isEqualTo(today.plusDays(20))
    assertThat(result.canonicalArrivalDate).isEqualTo(today.plusDays(5))
    assertThat(result.canonicalDepartureDate).isEqualTo(today.plusDays(20))

    assertThat(result.actualArrivalDate).isEqualTo(today.plusDays(5))
    assertThat(result.actualArrivalTime).isEqualTo("09:00")
    assertThat(result.actualDepartureDate).isEqualTo(today.plusDays(20))
    assertThat(result.actualDepartureTime).isEqualTo("10:30")

    assertThat(result.createdAt).isBetween(beforeCall.minusSeconds(60), afterCall.plusSeconds(60))

    assertThat(result.bookedBy).isNotNull
    val bookedBy = result.bookedBy!!
    assertThat(bookedBy.deliusUsername).isEqualTo("jan_delius_doe")
    assertThat(bookedBy.name).isEqualTo("Jane Doe")
    assertThat(bookedBy.roles).contains("ROLE_PROBATION", "ROLE_CAS1_ASSESSOR")

    assertThat(result.keyWorkerName).isEqualTo("John Smith")
    assertThat(result.deliusEventNumber).isEqualTo("1")
    assertThat(result.additionalInformation).contains("dummy placement profile")

    assertThat(result.characteristics).contains(Cas1SpaceCharacteristic.isSingle)
  }
}
