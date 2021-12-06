import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import io.kotest.core.spec.style.AnnotationSpec
import me.lulu.competitivesurvival.CompetitiveSurvival
import java.io.File

abstract class MockBukkitTemplate : AnnotationSpec() {
    protected lateinit var mock: ServerMock
    protected lateinit var plugin: CompetitiveSurvival

    @BeforeEach
    open fun setup() {
        mock = MockBukkit.mock()
        plugin = MockBukkit.loadWith(CompetitiveSurvival::class.java, File("src/main/resources/plugin.yml"));
    }

    @AfterEach
    fun end() {
        MockBukkit.unmock()
    }
}
