package nl.avisi.dddmappingexperiments.domain.aggregate1

import nl.avisi.dddmappingexperiments.domain.StateSnapshotable

class Aggregate1 : StateSnapshotable<Aggregate1Snapshot> {
    private val id: Aggregate1Id
    private var field1: ValueObject1

    constructor() {
        id = Aggregate1Id.createNew()
        field1 = ValueObject1("Hello")
    }

    private constructor(snapshot: Aggregate1Snapshot) {
        id = snapshot.id
        field1 = snapshot.field1
    }

    fun someCommand() {
        field1 = field1.someOperation()
    }

    override fun takeSnapshot() = Aggregate1Snapshot(id, field1)

    companion object {
        fun fromSnapshot(snapshot: Aggregate1Snapshot) = Aggregate1(snapshot)
    }
}
