package jp.ne.naokiur.dbaccess.model

import jp.ne.naokiur.dbaccess.dao.DBAccess
import jp.ne.naokiur.dbaccess.dto.MemberDto

/**
  * Created by nao-ur on 2016/11/26.
  */
class Member {
    val db = new DBAccess

    def addMember(args: Array[String]): Unit = {

        for (arg <- args; name <- arg.split(",")) {
            db.addMember(name)

        }

        db.close
    }

    def displayAllMember: Unit = {
        val currentMembers: List[MemberDto] = db.selectAllMember

        for (member <- currentMembers) {
//            val name: Some[String] = member.get("NAME").asInstanceOf[Some[String]]

            println(member.name)
        }

//        currentMembers.foreach(member
//            => println((member.get("NAME").asInstanceOf[Some[String]]).flatten)
//        )
    }

//    def foo[Some[String]](arg: Any)(implicit ev: String <:< Some[String]): Unit =  {
//        return arg
//    }

}
