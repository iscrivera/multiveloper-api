#!groovy

library identifier: '3scale-toolbox-jenkins@master',
        retriever: modernSCM([$class: 'GitSCMSource',
                              remote: 'https://github.com/rh-integration/3scale-toolbox-jenkins.git',
                              traits: [[$class: 'jenkins.plugins.git.traits.BranchDiscoveryTrait']]])

def service = null

node() {
  stage('Checkout Source') {
    checkout scm
  }

  @Library('pipelines') _

  stage("Prepare") {
    service = toolbox.prepareThreescaleService(
        openapi: [filename: "hybrid-usecase-open/swagger.json"],
        environment: [ baseSystemName: "hybrid_usecase_open",
                       privateBaseUrl: params.PRIVATE_BASE_URL,
                       privateBasePath: "/api",
                       publicStagingWildcardDomain: params.PUBLIC_STAGING_WILDCARD_DOMAIN,
                       publicProductionWildcardDomain: params.PUBLIC_PRODUCTION_WILDCARD_DOMAIN ],
        toolbox: [ openshiftProject: params.NAMESPACE,
                   destination: params.TARGET_INSTANCE,
                   image: "quay.io/redhat/3scale-toolbox:master", // TODO: remove me once the final image is released
                   insecure: params.DISABLE_TLS_VALIDATION == "yes",
                   secretName: params.SECRET_NAME],
        service: [:],
        applications: [
            [ name: params.APP_NAME]
        ],
        applicationPlans: [
          [ systemName: "silver", name: "Silver" ],
          [ systemName: "gold", name: "Gold" ],
        ]
    )

    //echo "toolbox version = " + service.toolbox.getToolboxVersion()
  }

  stage("Import OpenAPI") {
    service.importOpenAPI()
    echo "Service with system_name ${service.environment.targetSystemName} created !"
  }

  stage("Create an Application Plan") {
    service.applyApplicationPlans()
  }

  stage("Create an Application") {
    service.applyApplication()
  }
  
  stage("Promote to production") {
    service.promoteToProduction()
  }

}
