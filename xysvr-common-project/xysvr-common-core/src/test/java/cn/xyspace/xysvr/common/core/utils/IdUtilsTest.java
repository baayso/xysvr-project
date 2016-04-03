/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.utils;

import static org.junit.Assert.assertEquals;

import org.bson.types.ObjectId;
import org.junit.Test;

/**
 * Test class for {@linkplain cn.xyspace.xysvr.common.core.utils.IdUtils}
 *
 * @author ChenFangjie(2015/1/14 15:33:26)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
public class IdUtilsTest {

    /**
     * Test method for {@link cn.xyspace.xysvr.common.core.utils.IdUtils#getId()}.
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @Test
    public void testGetId() {
        String actual = IdUtils.getId();
        ObjectId id = new ObjectId(actual);
        assertEquals(id.toHexString(), actual);

        for (int i = 0; i < 100; i++) {
            System.out.println(IdUtils.getId());
        }
    }

    /**
     * Test method for {@link cn.xyspace.xysvr.common.core.utils.IdUtils#getUuid()}.
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @Test
    public void testGetUuid() {
        for (int i = 0; i < 10; i++) {
            System.out.println(IdUtils.getUuid());
        }
    }

}
