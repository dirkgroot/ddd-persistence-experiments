package nl.avisi.dddmappingexperiments.domain.aggregate1

interface Aggregate1Repository {
    fun save(aggregate1: Aggregate1)
    fun findAll(): List<Aggregate1>
    fun findById(id: Aggregate1Id): Aggregate1?
}
