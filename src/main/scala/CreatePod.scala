import com.fasterxml.jackson.databind.ObjectMapper
import io.fabric8.kubernetes.client.DefaultKubernetesClient

object CreatePod extends App {
  val client = new DefaultKubernetesClient().inNamespace("default")
  val mapper = new ObjectMapper()

  val pod = client.pods.createNew()
    .withNewMetadata()
      .withName("my-pod")
      .addToLabels("my-label", "demo")
    .endMetadata()
    .withNewSpec()
      .addNewContainer()
        .withName("jetty")
        .withImage("jetty:9-jre8-alpine")
        .addNewPort()
          .withContainerPort(8080)
          .withHostPort(9000)
        .endPort()
      .endContainer()
    .endSpec()
    .done()

  println(mapper.writerWithDefaultPrettyPrinter.writeValueAsString(pod))

}

