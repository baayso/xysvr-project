package cn.xyspace.xysvr.common.test.util;

import java.util.List;

import junit.framework.Assert;
import cn.xyspace.xysvr.common.entity.User;

public class EntitiesHelper {

    private static User baseUser = new User("1", "admin1");

    public static void assertUser(User expected, User actual) {
        Assert.assertNotNull(expected);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getUsername(), actual.getUsername());
    }

    public static void assertUsers(List<User> expected, List<User> actual) {
        for (int i = 0; i < expected.size(); i++) {
            User eu = expected.get(i);
            User au = actual.get(i);
            assertUser(eu, au);
        }
    }

    public static void assertUser(User expected) {
        assertUser(expected, baseUser);
    }
}
