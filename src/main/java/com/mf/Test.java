package com.mf;

import java.time.LocalDateTime;

public class Test {

    public static void main(String[] args) {
        System.out.println("Hibernate: /* select generatedAlias0 from User as generatedAlias0 where ( ( generatedAlias0.name=:param0 ) and ( generatedAlias0.sex=:param1 ) ) and ( generatedAlias0.createTime<:param2 ) */ select user0_.id as id1_1_, user0_.create_time as create_t2_1_, user0_.creator_id as creator_3_1_, user0_.del as del4_1_, user0_.update_time as update_t5_1_, user0_.updator_id as updator_6_1_, user0_.name as name7_1_, user0_.sex as sex8_1_ from user user0_ where user0_.name=? and user0_.sex=? and user0_.create_time<?\n" +
                "2019-07-31 17:38:05.669 TRACE 3936 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [admin]\n" +
                "2019-07-31 17:38:05.670 TRACE 3936 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [0]\n" +
                "2019-07-31 17:38:05.671 TRACE 3936 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [TIMESTAMP] - [2019-07-31T17:38:05.448]");
    }
}
