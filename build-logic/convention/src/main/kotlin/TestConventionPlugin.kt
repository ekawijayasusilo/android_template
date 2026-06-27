import com.template.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/** Baseline JVM-unit test dependencies. Coroutine/Koin/MockEngine test rules (`:core:testing`)
 *  are layered on in PR-3 (checklist 5.1); this convention is authored and frozen in PR-1. */
class TestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("testImplementation", libs.findLibrary("junit").get())
            }
        }
    }
}
