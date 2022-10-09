package nl.avisi.dddmappingexperiments.infrastructure

import nl.avisi.dddmappingexperiments.domain.aggregate1.*
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
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
        jdbcTemplate.query("select * from aggregate1") { rs, _ ->
            rs.getAggregate1()
        }

    override fun findById(id: Aggregate1Id) =
        jdbcTemplate.query("select * from aggregate1 where id = ?", id.toUUID()) { rs, _ ->
            rs.getAggregate1()
        }.singleOrNull()

    private fun ResultSet.getAggregate1() = Aggregate1.fromSnapshot(getSnapshot())
    private fun ResultSet.getSnapshot() = Aggregate1Snapshot(getId(), getField1())
    private fun ResultSet.getId() = Aggregate1Id(getObject("id", UUID::class.java))
    private fun ResultSet.getField1() = ValueObject1(getString("field1"))
}
