package nl.avisi.dddmappingexperiments.domain.aggregate1

data class ValueObject1(private val value: String) {
    init {
        require(value.isNotBlank()) { "VO1 may not be blank" }
    }

    fun someOperation() = copy(value = "$value World!")

    override fun toString() = value
}
