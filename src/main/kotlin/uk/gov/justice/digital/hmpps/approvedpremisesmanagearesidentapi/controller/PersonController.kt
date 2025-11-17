package uk.gov.justice.digital.hmpps.approvedpremisesmanagearesidentapi.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@RestController
@RequestMapping("\${api.base-path:}/person")
annotation class PersonController
