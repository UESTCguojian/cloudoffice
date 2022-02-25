package com.guojian.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author guojian
 * @since 2022-02-24-2022/2/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean
{
    private long total;
    private List<?> data;
}
