package tororo1066.man10protection

import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.bukkit.WorldGuardPlugin
import io.papermc.paper.event.player.PlayerOpenSignEvent
import org.bukkit.event.EventPriority
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.block.CrafterCraftEvent
import tororo1066.tororopluginapi.annotation.SEventHandler

@Suppress("unused")
class Man10ProtectionListener {

    @SEventHandler(priority = EventPriority.LOWEST)
    fun onOpenSign(e: PlayerOpenSignEvent) {
        if (e.cause != PlayerOpenSignEvent.Cause.INTERACT) return
        val sign = e.sign
        val query = WorldGuard.getInstance().platform.regionContainer.createQuery()
        val location = BukkitAdapter.adapt(sign.location)
        val player = WorldGuardPlugin.inst().wrapPlayer(e.player)
        if (!query.testState(location, player, Man10Protection.SIGN_EDIT)) {
            e.isCancelled = true
        }
    }

    @SEventHandler(priority = EventPriority.LOWEST)
    fun onPlaceCrafter(e: BlockPlaceEvent) {
        val block = e.block
        val query = WorldGuard.getInstance().platform.regionContainer.createQuery()
        val location = BukkitAdapter.adapt(block.location)
        val player = WorldGuardPlugin.inst().wrapPlayer(e.player)
        if (!query.testState(location, player, Man10Protection.USE_CRAFTER)) {
            e.isCancelled = true
            e.player.sendMessage(
                WorldGuardPlugin.inst()
                    .configManager
                    .get(player.world)
                    .buildPermissionDenyMessage
            )
        }
    }

    @SEventHandler(priority = EventPriority.LOWEST)
    fun onCrafterCraft(e: CrafterCraftEvent) {
        val query = WorldGuard.getInstance().platform.regionContainer.createQuery()
        val location = BukkitAdapter.adapt(e.block.location)
        if (!query.testState(location, null, Man10Protection.USE_CRAFTER)) {
            e.isCancelled = true
        }
    }
}
