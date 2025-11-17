package uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.model.ApprovedPremisesStay
import uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.service.PlacementService
import java.util.UUID

@RestController
class PlacementController(
  private val placementService: PlacementService,
) {
  @PreAuthorize("hasRole('ROLE_PROBATION')")
  @GetMapping("/person/{crn}/profile/cas1/placements", produces = [MediaType.APPLICATION_JSON_VALUE])
  fun getPreviousApStays(
    @PathVariable crn: UUID,
  ): ResponseEntity<List<ApprovedPremisesStay>> = ResponseEntity.ok().body(placementService.getPreviousApStaysForPerson(crn))
}
