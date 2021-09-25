

package nikita.kim.data;

import static junit.framework.TestCase.assertEquals;
import static nikita.kim.data.ActTestData.*;
import static nikita.kim.data.UserTestData.USER1_ID;
import static nikita.kim.data.UserTestData.user1;
import nikita.kim.repository.ActRepository;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;




public abstract class AbstractActRepositoryTest extends AbstractRepositoryTest{
    

    private static final Logger log= Logger.getLogger(AbstractActRepositoryTest.class);

    protected static ActRepository actRepository;
    

    @Test
    public void getAll()
        {
            assertEquals(acts,actRepository.getAll(USER1_ID));
        }
    
    @Test
    public void create()
        {
            assertTrue(actRepository.create(actToCreate,USER1_ID));
            Assert.assertNotNull(actRepository.get(NEW_ACT_ID,USER1_ID));
            assertEquals(newAct, actRepository.get(NEW_ACT_ID, USER1_ID));

        }
    
    @Test
    public void update()
        {
            assertTrue(actRepository.update(updatedAct,USER1_ID));
            assertNotNull(actRepository.get(ACT1_ID,USER1_ID));
            assertEquals(updatedAct,actRepository.get(ACT1_ID,USER1_ID));
        }
    
    @Test
    public void delete()
        {
            assertTrue(actRepository.delete(ACT1_ID,USER1_ID));
            assertNull(actRepository.get(ACT1_ID,USER1_ID));
        }
    
    @Test
    public void get()
        {
            assertEquals(act1,actRepository.get(ACT1_ID,USER1_ID));
            assertEquals(user1,actRepository.get(ACT1_ID,USER1_ID).getUser());
            assertNotNull(actRepository.get(ACT1_ID,USER1_ID));
            
        }
}
