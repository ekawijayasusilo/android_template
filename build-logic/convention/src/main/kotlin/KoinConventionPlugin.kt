import com.template.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/** Adds the Koin BOM + core. Koin library coordinates are appended to the catalog by the first PR
 *  that applies this convention (PR-2+); this plugin is authored and frozen in PR-1. */
class KoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation", platform(libs.findLibrary("koin-bom").get()))
                add("implementation", libs.findLibrary("koin-core").get())
            }
        }
    }
}
