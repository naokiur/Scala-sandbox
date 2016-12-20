package jp.ne.naokiur.dbaccess.dao

import java.lang.reflect.Member

import jp.ne.naokiur.dbaccess.dto.MemberDto
import scalikejdbc._
import scalikejdbc.config._

class DBAccess {
    open()
    implicit val session = AutoSession

    private def open(): Unit = {

        // DBs.setup/DBs.setupAll loads specified JDBC driver classes.
        DBs.setupAll()
        // DBs.setup()
        // DBs.setup('legacy)
        // // Unlike DBs.setupAll(), DBs.setup() doesn't load configurations under global settings automatically
        // DBs.loadGlobalSettings()
        //        implicit val session = AutoSession

        val createTable = DB autoCommit (implicit session =>
            sql"""
            create table if not exists members (
            id serial not null primary key,
            name varchar(64),
            created_at timestamp not null
        )
        """.execute.apply())

        val createSeq = DB autoCommit (implicit session =>
            sql"""
            create sequence if not exists members_seq start with 1 INCREMENT BY 1 maxvalue 9999 cycle
        """.execute.apply())


    }

    def addMember(name: String): Unit = {
        DBs.setupAll()

        val count = DB autoCommit { implicit session =>
            sql"insert into members (name, created_at) values (${name}, current_date())".update.apply()
        }

        DBs.closeAll()
    }

    def selectAllMember(): List[MemberDto] = {
        DBs.setupAll()


        val m = MemberDto.syntax("m")
        val members = withSQL { select.from(MemberDto as m) }.map(MemberDto(m)).list.apply()
        //
        //        val members: List[Map[String, Any]] = DB readOnly { implicit session =>
        //            sql"select * from members".map(rs => rs.toMap()).list.apply()
        //        }
        //         val member = sql"select id, name from members"
        //            .map(rs => UserDto(rs)).single.apply()
        // for now, retrieves all data as Map value
        //        return DB readOnly { implicit session => sql"select * from members".map(_.toMap).list.apply() }

        DBs.closeAll()
        return members
    }


    def execute(): Unit = {
        // loaded from "db.default.*"
        val memberIds = DB readOnly { implicit session =>
            sql"select id from public.members".map(_.long(1)).list.apply()
        }

    }

    def close: Unit = {

        // wipes out ConnectionPool
        DBs.closeAll()

    }


}