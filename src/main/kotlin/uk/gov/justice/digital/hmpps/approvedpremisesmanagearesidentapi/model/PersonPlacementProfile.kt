package uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.model

data class PersonPlacementProfile(
  val id: java.util.UUID,
  val applicationId: java.util.UUID,
  val premisesName: String,
  val expectedArrivalDate: java.time.LocalDate,
  val expectedDepartureDate: java.time.LocalDate,
  val canonicalArrivalDate: java.time.LocalDate,
  val canonicalDepartureDate: java.time.LocalDate,
  val actualArrivalDate: java.time.LocalDate? = null,
  val actualArrivalTime: String? = null,
  val actualDepartureDate: java.time.LocalDate? = null,
  val actualDepartureTime: String? = null,
  val createdAt: java.time.Instant,
  val bookedBy: User? = null,
  val keyWorkerName: String? = null,
  val deliusEventNumber: String? = null,
  val additionalInformation: String? = null,
  val apType: String,
  val characteristics: List<Cas1SpaceCharacteristic>,
)
