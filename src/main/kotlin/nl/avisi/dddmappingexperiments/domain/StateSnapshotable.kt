package nl.avisi.dddmappingexperiments.domain

interface StateSnapshotable<T> {
    fun takeSnapshot(): T
}
