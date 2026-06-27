import org.gradle.api.Plugin
import org.gradle.api.Project

/** The :api/:impl feature seam: a feature :impl module is an Android library + Compose + Koin.
 *  Structural enforcement (no impl→impl edges, keys-only :api) is exercised when the first feature
 *  lands (PR-13 / Lane B); the plugin is authored and frozen here. */
class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target.pluginManager) {
            apply("template.android.library")
            apply("template.android.compose")
            apply("template.android.koin")
        }
    }
}
