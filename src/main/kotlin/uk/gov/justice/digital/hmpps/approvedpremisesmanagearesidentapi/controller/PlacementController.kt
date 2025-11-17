package uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.model.ApprovedPremisesStay
import uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.model.PersonPlacementProfile
import uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.service.PlacementService
import java.util.UUID

@PersonController
@PreAuthorize("isAuthenticated()")
@SecurityRequirement(name = "bearer-jwt")
class PlacementController(
  private val placementService: PlacementService,
) {

  @Operation(
    summary = "Get previous AP stays for a person",
    description = "Returns a list of previous Approved Premises stays for the given CRN",
    responses = [
      ApiResponse(
        responseCode = "200",
        description = "Previous AP stays returned",
        content = [Content(array = ArraySchema(schema = Schema(implementation = ApprovedPremisesStay::class)))],
      ),
    ],
  )
  @PreAuthorize("hasRole('ROLE_PROBATION')")
  @GetMapping("/{crn}/profile/cas1/placements", produces = [MediaType.APPLICATION_JSON_VALUE])
  fun getPreviousApStays(
    @PathVariable crn: UUID,
  ): ResponseEntity<List<ApprovedPremisesStay>> = ResponseEntity.ok().body(placementService.getPreviousApStaysForPerson(crn))

  @Operation(
    summary = "Get the CAS1 placement profile for a person",
    description = "Returns the person placement profile for the given CRN and CAS1 placement ID",
    responses = [
      ApiResponse(
        responseCode = "200",
        description = "Placement profile found",
        content = [Content(schema = Schema(implementation = PersonPlacementProfile::class))],
      ),
      ApiResponse(
        responseCode = "404",
        description = "Placement profile not found",
        content = [Content()],
      ),
    ],
  )
  @PreAuthorize("hasRole('ROLE_PROBATION')")
  @GetMapping("/{crn}/profile/cas1/placements/{placementId}")
  fun getPersonPlacementProfile(
    @PathVariable crn: String,
    @PathVariable placementId: UUID,
  ): ResponseEntity<PersonPlacementProfile> {
    val profile = placementService.getPersonPlacementProfile(crn, placementId)
      ?: return ResponseEntity.notFound().build()
    return ResponseEntity.ok(profile)
  }
}
