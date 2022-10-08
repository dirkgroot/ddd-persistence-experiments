package nl.avisi.dddmappingexperiments.domain.aggregate1

data class Aggregate1Snapshot(
    val id: Aggregate1Id,
    val field1: ValueObject1,
)
