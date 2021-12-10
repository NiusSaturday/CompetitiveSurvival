package me.lulu.competitivesurvival.listener

import MockDescribeTemplate
import be.seeseemelk.mockbukkit.entity.PlayerMock
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.worlds
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.GameRole
import me.lulu.competitivesurvival.GameState
import me.lulu.competitivesurvival.makePlayer
import org.bukkit.GameMode

class TestJoinListener : MockDescribeTemplate() {
    init {

        it("Join should always in game world") {
            val player = PlayerMock(mock, "Test")
            val anotherWorld = mock.addSimpleWorld("another_world")

            player.teleport(anotherWorld.spawnLocation)

            mock.addPlayer(player)

            player.world shouldBe plugin.gameWorld
        }

        describe("Game is not yet started") {
            plugin.gameState = GameState.WAITING

            describe("Player join") {

                var player: PlayerMock? = null

                beforeTest {
                    player = PlayerMock(mock, "TestPlayer")
                    mock.addPlayer(player)
                }

                it("Should be adventure mode") {
                    player!!.gameMode shouldBe GameMode.ADVENTURE
                }

                it("Teleport to world spawn") {
                    player!!.location shouldBe worlds.first().spawnLocation
                }

                it("Should be full health") {
                    player!!.health shouldBe player!!.maxHealth
                }
            }
        }

        describe("Game started") {
            plugin.gameState = GameState.GAMING

            var player: PlayerMock? = null

            beforeTest {
                player = mock.makePlayer()
            }

            it("Staff join, don't kill") {
                addRoleAndJoin(player!!, GameRole.STAFF)
                player!!.isDead shouldBe false
            }

            it("Player join, kill") {
                addRoleAndJoin(player!!, GameRole.PLAYER)
                player!!.isDead shouldBe true
            }
        }
    }

    private fun addRoleAndJoin(playerMock: PlayerMock, role: GameRole) {
        plugin.roleManager.setRole(playerMock, role)
        mock.addPlayer(playerMock)
    }

}
