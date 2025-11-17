package uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@PreAuthorize("isAuthenticated()")
@SecurityRequirement(name = "bearer-jwt")
class DummyController {

  @GetMapping("/hello-world", produces = [MediaType.APPLICATION_JSON_VALUE])
  fun helloWorld(): ResponseEntity<String> = ResponseEntity.ok(
    """ 
      {"message": "hello world"}
    """.trimIndent(),
  )
}
