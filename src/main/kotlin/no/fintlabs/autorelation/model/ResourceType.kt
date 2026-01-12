package no.fintlabs.autorelation.model

data class ResourceType(
    val domainName: String,
    val packageName: String,
    val resource: String
) {

    companion object {
        fun of(domainName: String, packageName: String, resource: String): ResourceType =
            ResourceType(
                domainName.lowercase(),
                packageName.lowercase(),
                resource.lowercase()
            )

        fun parse(key: String): ResourceType =
            key.split("-", limit = 3)
                .let { (domainName, packageName, resource) -> of(domainName, packageName, resource) }
    }
}