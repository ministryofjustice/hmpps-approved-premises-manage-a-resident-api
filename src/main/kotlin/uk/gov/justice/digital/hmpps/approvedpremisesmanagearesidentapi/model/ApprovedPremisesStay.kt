package uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.model

import java.time.LocalDate

data class ApprovedPremisesStay(
  val name: String,
  val arrivalDate: LocalDate,
  val departureDate: LocalDate,
  val departureReason: String,
)
