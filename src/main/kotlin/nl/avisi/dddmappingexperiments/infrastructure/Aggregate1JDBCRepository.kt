package nl.avisi.dddmappingexperiments.infrastructure

import nl.avisi.dddmappingexperiments.domain.aggregate1.*
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.*

@Repository
class Aggregate1JDBCRepository(private val jdbcTemplate: JdbcTemplate) : Aggregate1Repository {
    override fun save(aggregate1: Aggregate1) {
        val snapshot = aggregate1.takeSnapshot()
        jdbcTemplate.update(
            "insert into aggregate1 (id, field1) values (?, ?)",
            snapshot.id.toUUID(), snapshot.field1.toString()
        )
    }

    override fun findAll(): List<Aggregate1> =
        jdbcTemplate.query("select * from aggregate1", rowMapper)

    override fun findById(id: Aggregate1Id): Aggregate1? =
        jdbcTemplate.query("select * from aggregate1 where id = ?", rowMapper, id.toUUID())
            .singleOrNull()

    private val rowMapper = { rs: ResultSet, _: Int -> Aggregate1.fromSnapshot(rs.getSnapshot()) }

    private fun ResultSet.getSnapshot() = Aggregate1Snapshot(getId(), getField1())
    private fun ResultSet.getId() = Aggregate1Id(getObject("id", UUID::class.java))
    private fun ResultSet.getField1() = ValueObject1(getString("field1"))
}
