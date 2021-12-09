package me.lulu.competitivesurvival.listener

import MockDescribeTemplate
import be.seeseemelk.mockbukkit.entity.PlayerMock
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.worlds
import io.kotest.core.annotation.Ignored
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.GameState
import org.bukkit.GameMode

class TestJoinListener : MockDescribeTemplate() {

    private lateinit var player: PlayerMock

    override fun beforeEachRoot() {
        super.beforeEachRoot()

        player = PlayerMock(mock, "TestPlayer")
    }

    init {
        describe("Game is not yet started") {
            plugin.gameState = GameState.WAITING

            describe("Player join") {
                mock.addPlayer(player)

                it("Should be adventure mode") {
                    player.gameMode shouldBe GameMode.ADVENTURE
                }

                it("Teleport to world spawn") {
                    player.location shouldBe worlds.first().spawnLocation
                }
            }
        }

        describe("Game started") {
            plugin.gameState = GameState.GAMING

            it("Staff join, do nothing") {

            }

            it("Player join, kill") {

            }
        }
    }

}
