package me.lulu.competitivesurvival.manager

import MockBukkitTemplate
import be.seeseemelk.mockbukkit.entity.PlayerMock
import io.kotest.matchers.shouldBe

class TestNoCleanManager : MockBukkitTemplate() {

    private lateinit var manager: NoCleanManager
    private lateinit var player: PlayerMock

    @BeforeEach
    override fun setup() {
        super.setup()

        manager = plugin.noCleanManager
        player = mock.addPlayer()
    }

    @Test
    fun default_NoCleanTimeIsCurrentTime() {
        assertNoClean(time = System.currentTimeMillis(), isNoClean = false)
    }

    @Test
    fun testAddNoClean() {
        manager.setNoCleanSeconds(player, 1)
        assertNoClean(time = System.currentTimeMillis() + 1000, isNoClean = true)
    }

    private fun assertNoClean(time: Long, isNoClean: Boolean) {
        manager.getNoCleanEnds(player) shouldBe time
        manager.isNoCleaning(player) shouldBe isNoClean
    }
}
