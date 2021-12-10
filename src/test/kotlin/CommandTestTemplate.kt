import be.seeseemelk.mockbukkit.entity.PlayerMock
import io.kotest.matchers.shouldBe
import org.bukkit.command.Command

abstract class CommandTestTemplate : MockDescribeTemplate() {
    protected lateinit var sender: PlayerMock

    override fun beforeEachRoot() {
        sender = mock.addPlayer()
    }

    protected fun executeCommand(vararg args: String) =
        getTestCommand().execute(sender, getTestCommand().label, args)

    protected fun assertCommandRegistered() {
        it("command should be registered") {
            getTestCommand().isRegistered shouldBe true
        }
    }

    protected fun addPermission(perm: String) {
        sender.addAttachment(plugin, perm, true)
    }

    protected abstract fun getTestCommand(): Command
}
