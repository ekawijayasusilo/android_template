import com.diffplug.gradle.spotless.SpotlessExtension
import com.template.buildlogic.libs
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/** Static analysis + format gate: detekt (single root config) + Spotless/ktfmt (PRD §12 QR-1/QR-2).
 *  Compose-lints + a11y Lint→error are wired by the compose/android conventions. */
class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("io.gitlab.arturbosch.detekt")
                apply("com.diffplug.spotless")
            }

            extensions.configure<DetektExtension> {
                buildUponDefaultConfig = true
                parallel = true
                config.setFrom(rootProject.files("config/detekt/detekt.yml"))
            }

            val ktfmtVersion = libs.findVersion("ktfmt").get().requiredVersion
            extensions.configure<SpotlessExtension> {
                kotlin {
                    target("src/**/*.kt")
                    targetExclude("**/build/**/*.kt")
                    ktfmt(ktfmtVersion).kotlinlangStyle()
                }
                kotlinGradle {
                    target("*.gradle.kts")
                    ktfmt(ktfmtVersion).kotlinlangStyle()
                }
            }
        }
    }
}
