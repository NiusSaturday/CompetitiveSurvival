import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import br.com.devsrsouza.kotlinbukkitapi.KotlinBukkitAPI
import br.com.devsrsouza.kotlinbukkitapi.KotlinBukkitAPITest
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.isRootTest
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.commands.registerTogglePvPCommand
import org.bukkit.Bukkit
import org.bukkit.command.SimpleCommandMap
import java.io.File

open class MockDescribeTemplate : DescribeSpec() {
    protected lateinit var mock: ServerMock
    protected lateinit var plugin: CompetitiveSurvival

    init {
        beforeTest {
            if (it.isRootTest()) {
                mock = MockBukkit.mock()

                KotlinBukkitAPITest.testMode = true
                MockBukkit.loadWith(KotlinBukkitAPI::class.java, File("lib/kt-api.yml"))

                plugin = MockBukkit.loadWith(CompetitiveSurvival::class.java, File("src/main/resources/plugin.yml"));

                beforeEachRoot()
            }
        }

        afterTest {
            if (it.a.isRootTest()) {
                afterEachRoot()
                MockBukkit.unmock()
            }
        }
    }

    protected open fun beforeEachRoot() {


    }

    protected open fun afterEachRoot() {

    }
}
