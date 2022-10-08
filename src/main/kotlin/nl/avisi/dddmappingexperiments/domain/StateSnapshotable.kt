package nl.avisi.dddmappingexperiments.domain

interface StateSnapshotable<T> {
    fun takeSnapshot(): T
    fun loadSnapshot(snapshot: T)
}
