package jp.ne.naokiur.dbaccess.dto

import java.util.Date

import scalikejdbc._

/**
  * Created by nao-ur on 2016/11/26.
  */
case class MemberDto(id: BigInt, name: String, created_at: Date)

object MemberDto extends SQLSyntaxSupport[MemberDto] {
    override val tableName = "MEMBERS"
    override val columnNames = Seq("id", "name", "created_at")

    def apply(m: SyntaxProvider[MemberDto])(rs: WrappedResultSet): MemberDto = apply(m.resultName)(rs)
    def apply(m: ResultName[MemberDto])(rs: WrappedResultSet): MemberDto = new MemberDto(rs.bigInt(m.id), rs.string(m.name), rs.date(m.created_at))
}
