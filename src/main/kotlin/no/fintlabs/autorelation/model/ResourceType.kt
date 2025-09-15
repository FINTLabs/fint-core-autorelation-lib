package no.fintlabs.autorelation.model

data class ResourceType(
    val domain: String,
    val pkg: String,
    val resource: String
) {

    fun sameComponent(domain: String, pkg: String) =
        this.domain.equals(domain, ignoreCase = true)
                && this.pkg.equals(pkg, ignoreCase = true)

    companion object {
        fun of(domain: String, pkg: String, resource: String): ResourceType =
            ResourceType(
                domain.lowercase(),
                pkg.lowercase(),
                resource.lowercase()
            )

        fun parse(key: String): ResourceType =
            key.split("-", limit = 3).let { (domain, pkg, resource) -> of(domain, pkg, resource) }
    }
}