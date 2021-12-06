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
        manager.getNoCleanEnds(player) shouldBe System.currentTimeMillis()
    }

    @Test
    fun testAddNoClean() {
        manager.setNoCleanSeconds(player, 1)

        manager.getNoCleanEnds(player) shouldBe System.currentTimeMillis() + 1000
    }
}
