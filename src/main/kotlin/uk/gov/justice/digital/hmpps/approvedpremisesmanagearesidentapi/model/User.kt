package uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.model

import java.util.UUID

data class User(
  val id: UUID,
  val deliusUsername: String,
  val name: String,
  val roles: List<String>,
)
