import org.gradle.api.Plugin
import org.gradle.api.Project

/** JVM-unit coverage via Kover (PRD §10/TR-1; JaCoCo rejected — miscounts Kotlin). Per-module
 *  ViewModel/mapper coverage floors are tuned where features land; PR-1 only applies the plugin. */
class KoverConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.pluginManager.apply("org.jetbrains.kotlinx.kover")
    }
}
