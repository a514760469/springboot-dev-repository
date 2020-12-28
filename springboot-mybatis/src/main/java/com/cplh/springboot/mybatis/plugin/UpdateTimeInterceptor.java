package com.cplh.springboot.mybatis.plugin;

import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.JSqlParser;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * @author zhanglifeng
 * @since 2020-12-26
 */
@Component
@Intercepts({
    @Signature(method = "update", type = Executor.class, args = {MappedStatement.class, Object.class})

})
public class UpdateTimeInterceptor implements Interceptor {

    private final CCJSqlParserManager sqlParserManager = new CCJSqlParserManager();

    private final TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        System.out.println("method：" + invocation.getMethod().getName());
        Executor executor = (Executor) invocation.getTarget();
        MappedStatement ms = (MappedStatement) args[0];
        BoundSql boundSql = ms.getBoundSql(args[1]);
        String sql = boundSql.getSql();
        Statement statement = sqlParserManager.parse(new StringReader(sql));

//        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
//        originalSql = (String)metaObject.getValue("delegate.boundSql.sql");

        if (statement instanceof Select) {
            Select selectStatement = (Select) statement;
            List<String> tableList = tablesNamesFinder.getTableList(selectStatement);
            for (String tableName : tableList) {
                System.out.println(tableName);
            }
        } else if (statement instanceof Update) {
            Update updateStatement = (Update) statement;
            List<Column> columns = updateStatement.getColumns();
            for (Column column : columns) {
                System.out.println(column.getColumnName());
            }
            List<String> tableList = tablesNamesFinder.getTableList(updateStatement);
            for (String tableName : tableList) {
                System.out.println(tableName);
            }
            columns.add(new Column(new Table(tableList.get(0)), "updateTime"));
            updateStatement.getExpressions().add(new ExistsExpression());
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
