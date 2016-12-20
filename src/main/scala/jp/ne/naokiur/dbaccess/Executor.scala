package jp.ne.naokiur.dbaccess

import jp.ne.naokiur.dbaccess.model.Member

/**
  * Created by nao-ur on 2016/11/26.
  */
object Executor {

    def main(args: Array[String]): Unit = {

        val model = new Member
        model.addMember(args)
        //        db.execute

        model.displayAllMember
    }
}
