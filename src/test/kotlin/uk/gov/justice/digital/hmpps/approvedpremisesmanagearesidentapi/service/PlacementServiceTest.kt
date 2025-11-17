package uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.UUID

class PlacementServiceTest {

  @Test
  fun `getPreviousApStaysForPerson should return a list of stays for a given CRN`() {
    val placementService = PlacementService()
    val crn = UUID.randomUUID()

    val result = placementService.getPreviousApStaysForPerson(crn)

    assertEquals(1, result.size)

    val stay = result[0]
    assertEquals("Elmswood House", stay.name)
    assertEquals(LocalDate.of(2024, 3, 15), stay.arrivalDate)
    assertEquals(LocalDate.of(2024, 6, 20), stay.departureDate)
    assertEquals("Planned move on", stay.departureReason)
  }
}
