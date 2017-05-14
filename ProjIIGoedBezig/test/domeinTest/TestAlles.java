package domeinTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    domeinTest.ActiesGoedgekeurdStateTest.class,
    domeinTest.ActiesInBeoordelingStateTest.class,
    domeinTest.GeenMotivatieStateTest.class,
    domeinTest.GroepTest.class,
    domeinTest.HeeftMotivatieStateTest.class,
    domeinTest.LabelAanvaardStateTest.class,
    domeinTest.MotivatieGoedgekeurdStateTest.class,
    domeinTest.MotivatieInBeoordelingStateTest.class,
    domeinTest.StatePatternTest.class,
    repositoryTest.ContactPersoonTest.class,
    repositoryTest.LoginTest.class
})
public class TestAlles {
    
}
