package nl.avisi.dddmappingexperiments.domain.aggregate1

import java.util.UUID

data class Aggregate1Id(private val value: UUID) {
    fun toUUID() = value

    companion object {
        fun createNew(): Aggregate1Id = Aggregate1Id(UUID.randomUUID())
    }
}
