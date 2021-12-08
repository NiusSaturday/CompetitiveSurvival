import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.isRootTest
import me.lulu.competitivesurvival.CompetitiveSurvival
import java.io.File

open class MockDescribeTemplate : DescribeSpec() {
    protected lateinit var mock: ServerMock
    protected lateinit var plugin: CompetitiveSurvival

    init {
        beforeTest {
            if (it.isRootTest()) {
                mock = MockBukkit.mock()
                plugin = MockBukkit.loadWith(CompetitiveSurvival::class.java, File("src/main/resources/plugin.yml"));
            }
        }

        afterTest {
            if (it.a.isRootTest()) {
                MockBukkit.unmock()
            }
        }
    }
}
