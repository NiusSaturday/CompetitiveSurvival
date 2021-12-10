import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import br.com.devsrsouza.kotlinbukkitapi.KotlinBukkitAPI
import br.com.devsrsouza.kotlinbukkitapi.KotlinBukkitAPITest
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.pluginManager
import io.kotest.core.spec.style.AnnotationSpec
import me.lulu.competitivesurvival.CompetitiveSurvival
import java.io.File

abstract class MockBukkitTemplate : AnnotationSpec() {
    protected lateinit var mock: ServerMock
    protected lateinit var plugin: CompetitiveSurvival

    @BeforeEach
    open fun setup() {
        mock = MockBukkit.mock()

        KotlinBukkitAPITest.testMode = true
        MockBukkit.loadWith(KotlinBukkitAPI::class.java, File("lib/kt-api.yml"))

        CompetitiveSurvival.unitTesting = true
        plugin = MockBukkit.loadWith(CompetitiveSurvival::class.java, File("src/main/resources/plugin.yml"));
    }

    @AfterEach
    fun end() {
        MockBukkit.unmock()
    }
}
