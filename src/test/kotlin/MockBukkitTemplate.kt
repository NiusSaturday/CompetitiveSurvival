import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import me.lulu.competitivesurvival.CompetitiveSurvival
import org.junit.After
import org.junit.Before
import java.io.File

abstract class MockBukkitTemplate {
    protected lateinit var mock: ServerMock
    protected lateinit var plugin: CompetitiveSurvival

    @Before
    fun setup() {
        mock = MockBukkit.mock()
        plugin = MockBukkit.loadWith(CompetitiveSurvival::class.java, File("src/main/resources/plugin.yml"));
    }

    @After
    fun end() {
        MockBukkit.unmock()
    }
}
