package uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.service

import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.model.ApprovedPremisesStay
import uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.model.Cas1SpaceCharacteristic
import uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.model.PersonPlacementProfile
import uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.model.User
import java.time.Instant
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

  fun getPersonPlacementProfile(crn: String, placementId: UUID): PersonPlacementProfile? {
    val today = LocalDate.now()

    return PersonPlacementProfile(
      id = placementId,
      applicationId = UUID.randomUUID(),
      premisesName = "River View Approved Premises",
      expectedArrivalDate = today.plusDays(5),
      expectedDepartureDate = today.plusDays(20),
      canonicalArrivalDate = today.plusDays(5),
      canonicalDepartureDate = today.plusDays(20),
      actualArrivalDate = today.plusDays(5),
      actualArrivalTime = "09:00",
      actualDepartureDate = today.plusDays(20),
      actualDepartureTime = "10:30",
      createdAt = Instant.now(),
      bookedBy = User(
        id = UUID.randomUUID(),
        deliusUsername = "jan_delius_doe",
        name = "Jane Doe",
        roles = listOf("ROLE_PROBATION", "ROLE_CAS1_ASSESSOR"),
      ),
      keyWorkerName = "John Smith",
      deliusEventNumber = "1",
      additionalInformation = "This is a dummy placement profile populated for development/testing purposes.",
      apType = "Standard AP",
      characteristics = listOf(Cas1SpaceCharacteristic.isSingle),
    )
  }
}
