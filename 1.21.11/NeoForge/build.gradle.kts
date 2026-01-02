plugins {
    id("fuzs.multiloader.multiloader-convention-plugins-neoforge")
}

dependencies {
    modCompileOnly(libs.puzzleslib.common)
    modApi(libs.puzzleslib.neoforge)
    modCompileOnly(libs.tooltipinsights.common)
    modApi(libs.tooltipinsights.neoforge)
    include(libs.tooltipinsights.neoforge)
}
