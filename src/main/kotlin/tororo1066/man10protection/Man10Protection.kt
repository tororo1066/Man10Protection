package tororo1066.man10protection

import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.protection.flags.StateFlag
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException
import tororo1066.tororopluginapi.SJavaPlugin

class Man10Protection : SJavaPlugin() {

    companion object {
        lateinit var SIGN_EDIT: StateFlag
        lateinit var USE_CRAFTER: StateFlag
    }

    override fun onLoad() {

        val registry = WorldGuard.getInstance().flagRegistry
        try {
            val flag = StateFlag("sign-edit", false)
            registry.register(flag)
            SIGN_EDIT = flag
        } catch (e: FlagConflictException) {
            logger.severe("Failed to register flag: ${e.message}")
        }
        try {
            val flag = StateFlag("use-crafter", false)
            registry.register(flag)
            USE_CRAFTER = flag
        } catch (e: FlagConflictException) {
            logger.severe("Failed to register flag: ${e.message}")
        }
    }

    override fun onStart() {
        // Plugin startup logic
    }

    override fun onEnd() {
        // Plugin shutdown logic
    }
}
