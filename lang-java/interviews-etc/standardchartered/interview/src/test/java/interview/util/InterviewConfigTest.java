package interview.util;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;


/**
 * Created by sujayjayaram on 13/01/2016.
 */
public class InterviewConfigTest {

    @Test
    public void testGetInt() throws Exception {
        int ageCutOff = InterviewConfig.INSTANCE.getInt("age.cutoff");
        assertThat(ageCutOff, equalTo(3));
    }

    @Test
    public void testGetDouble() throws Exception {
        double discountRate = InterviewConfig.INSTANCE.getDouble("discount.rate");
        assertThat(discountRate, equalTo(0.9));
    }
}