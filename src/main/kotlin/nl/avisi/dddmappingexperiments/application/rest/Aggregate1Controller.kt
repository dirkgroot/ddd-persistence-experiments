package nl.avisi.dddmappingexperiments.application.rest

import nl.avisi.dddmappingexperiments.domain.aggregate1.Aggregate1
import nl.avisi.dddmappingexperiments.domain.aggregate1.Aggregate1Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/aggregate1")
class Aggregate1Controller(private val aggregate1Repository: Aggregate1Repository) {
    @GetMapping
    fun findAll() = aggregate1Repository.findAll()
        .map { it.takeSnapshot().field1.toString() }

    @PostMapping
    fun new(): String {
        val aggregate1 = Aggregate1()
        aggregate1.someCommand()
        aggregate1Repository.save(aggregate1)
        return aggregate1.takeSnapshot().field1.toString()
    }
}
