import androidx.room.gradle.RoomExtension
import com.template.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/** Room + KSP wiring with a per-module schema directory. Room library coordinates are appended to
 *  the catalog by the first PR that applies this convention (PR-4); authored and frozen in PR-1. */
class RoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("androidx.room")
                apply("com.google.devtools.ksp")
            }
            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }
            dependencies {
                add("implementation", libs.findLibrary("room-runtime").get())
                add("ksp", libs.findLibrary("room-compiler").get())
            }
        }
    }
}
