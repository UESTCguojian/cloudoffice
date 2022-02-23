package com.guojian.server.Exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.guojian.server.pojo.RespBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author guojian
 * @since 2022-02-22-2022/2/22
 */
@RestControllerAdvice
public class GlobalException
{
    @ExceptionHandler(SQLException.class)
    public RespBean MySqlException(SQLException e)
    {
        if (e instanceof SQLIntegrityConstraintViolationException)
        {
            return RespBean.error("该数据有关联数据，不可被操作");
        }
        return RespBean.error("数据库异常，操作失败");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public RespBean DuplicateKeyException(DuplicateKeyException e)
    {
        return RespBean.error("重复键值数据，操作失败");
    }

    @ExceptionHandler(JsonParseException.class)
    public RespBean JsonParseException(JsonParseException e)
    {
        return RespBean.error("JSON解析错误，操作失败");
    }
}
