package me.lulu.competitivesurvival.listener

import MockDescribeTemplate
import be.seeseemelk.mockbukkit.ServerMock
import be.seeseemelk.mockbukkit.entity.EntityEquipmentMock
import be.seeseemelk.mockbukkit.entity.MonsterMock
import be.seeseemelk.mockbukkit.entity.PlayerMock
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.Config
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.EntityEquipment
import java.util.*

class TestWinListener : MockDescribeTemplate() {
    private lateinit var player: PlayerMock
    private lateinit var dragon: DragonMock

    override fun beforeEachRoot() {
        super.beforeEachRoot()

        player = mock.addPlayer()
        dragon = DragonMock(mock, UUID.randomUUID())
        mock.registerEntity(dragon)
    }

    init {

        it("Damage dragon, should dead") {
            killDragon(killer = null)
            dragon.isDead shouldBe true
        }

        it("Dead without killer, broadcast no one wins") {
            killDragon(killer = null)
            player.nextMessage() shouldBe Config.WIN_NO_ONE_WINS
        }


        it("Killed by someone, broadcast winner") {
            killDragon(killer = player)

            player.nextTitle() shouldBe Config.WIN_WINNER_TITLE
            player.nextSubTitle() shouldBe Config.WIN_WINNER_SUB
        }
    }

    fun killDragon(killer: Player?) {
        if (killer != null)
            dragon.damage(1000.0, killer)
        else
            dragon.damage(1000.0)
    }

}

class DragonMock(server: ServerMock, uuid: UUID) : MonsterMock(server, uuid) {
    private val equipment: EntityEquipment = EntityEquipmentMock(this)
    private var killer: Player? = null

    override fun getType(): EntityType {
        return EntityType.ENDER_DRAGON
    }

    override fun getEquipment(): EntityEquipment {
        return equipment
    }

    override fun damage(amount: Double, source: Entity?) {
        if (source is Player)
            this.killer = source

        super.damage(amount, source)
    }

    override fun getKiller(): Player? {
        return killer
    }


}
