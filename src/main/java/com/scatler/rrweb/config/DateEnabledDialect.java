package com.scatler.rrweb.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.type.DateType;
import org.hibernate.type.TimestampType;

public class DateEnabledDialect extends MySQL5InnoDBDialect {

    public DateEnabledDialect() {
        registerFunction("adddays", new VarArgsSQLFunction(DateType.INSTANCE, "ADDDATE(", ",", ")"));
    }

}