package com.asahi.testForDatabase

import groovy.sql.Sql
import groovy.sql.GroovyRowResult

String username = "root"
String password = "root"

def sql = Sql.newInstance("jdbc:mysql://localhost:3306/groovysample",username,password,"com.mysql.jdbc.Driver")

/*sql.execute'''
    CREATE TABLE user(
        id int not null,
        name varchar(20),
        primary key(id)
    )
'''*/

/*sql.execute'''
    INSERT INTO user (id,name) 
    VALUES (2,'achu')
'''*/
List<GroovyRowResult> users = sql.rows("select * from user")

println users
File file = new File("user.csv")
//file.write("id,name\n")

println "Created..."


sql.eachRow("select * from user"){singleUser->
    file.append("${singleUser.id},${singleUser.name}\n")

}

sql.close()
