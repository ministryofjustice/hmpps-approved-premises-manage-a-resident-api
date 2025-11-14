package uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.service

import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.model.ApprovedPremisesStay
import java.time.LocalDate
import java.util.UUID

@Service
class PlacementService {

  fun getPreviousApStaysForPerson(crn: UUID): List<ApprovedPremisesStay> = listOf(
    ApprovedPremisesStay(
      name = "Elmswood House",
      arrivalDate = LocalDate.of(2024, 3, 15),
      departureDate = LocalDate.of(2024, 6, 20),
      departureReason = "Planned move on",
    ),
  )
}
