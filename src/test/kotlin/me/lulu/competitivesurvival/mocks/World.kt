package me.lulu.competitivesurvival.mocks

import be.seeseemelk.mockbukkit.WorldMock
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.WorldBorder


class WorldMockImpl : WorldMock() {

    private val worldBorder: WorldBorder = MockWorldBorder(this)

    override fun getWorldBorder(): WorldBorder {
        return worldBorder
    }
}

class MockWorldBorder(world: World) : WorldBorder {
    private var size = -1.0;
    private var center = Location(world, 0.0, 0.0, 0.0)

    override fun reset() {
        TODO("Not yet implemented")
    }

    override fun getSize(): Double {
        return size
    }

    override fun setSize(newSize: Double) {
        this.size = newSize
    }

    override fun setSize(newSize: Double, seconds: Long) {
        TODO("Not yet implemented")
    }

    override fun getCenter(): Location {
        return this.center
    }

    override fun setCenter(x: Double, z: Double) {
        this.center = this.center.clone().zero().add(x, 0.0, z)
    }

    override fun setCenter(location: Location) {
        this.center = location.clone()
    }

    override fun getDamageBuffer(): Double {
        TODO("Not yet implemented")
    }

    override fun setDamageBuffer(blocks: Double) {
        TODO("Not yet implemented")
    }

    override fun getDamageAmount(): Double {
        TODO("Not yet implemented")
    }

    override fun setDamageAmount(damage: Double) {
        TODO("Not yet implemented")
    }

    override fun getWarningTime(): Int {
        TODO("Not yet implemented")
    }

    override fun setWarningTime(seconds: Int) {
        TODO("Not yet implemented")
    }

    override fun getWarningDistance(): Int {
        TODO("Not yet implemented")
    }

    override fun setWarningDistance(distance: Int) {
        TODO("Not yet implemented")
    }

    override fun isInside(location: Location): Boolean {
        TODO("Not yet implemented")
    }
}
