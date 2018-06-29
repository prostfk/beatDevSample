package unit.by.medvedev.task;

import by.medvedev.task.model.util.StringsUtil;
import org.junit.Assert;
import org.junit.Test;

public class UtilTest {

    @Test
    public void hashTest(){
        String userInput = "123";
        String expected = "202cb962ac59075b964b07152d234b70";
        String actual = StringsUtil.hash(userInput);
        Assert.assertEquals(expected, actual);
    }

}
