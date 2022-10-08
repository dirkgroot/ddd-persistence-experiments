package nl.avisi.dddmappingexperiments.domain.aggregate1

import nl.avisi.dddmappingexperiments.domain.StateSnapshotable

class Aggregate1 : StateSnapshotable<Aggregate1Snapshot> {
    private val id: Aggregate1Id
    private var field1: ValueObject1 = ValueObject1("Hello")

    constructor() : this(Aggregate1Id.createNew())

    private constructor(id: Aggregate1Id) {
        this.id = id
    }

    fun someCommand() {
        field1 = field1.someOperation()
    }

    override fun takeSnapshot() = Aggregate1Snapshot(id, field1)

    override fun loadSnapshot(snapshot: Aggregate1Snapshot) {
        require(snapshot.id == id) { "Cannot load a snapshot of another entity" }
        field1 = snapshot.field1
    }

    companion object {
        fun fromSnapshot(snapshot: Aggregate1Snapshot) = Aggregate1(snapshot.id)
            .apply { loadSnapshot(snapshot) }
    }
}
