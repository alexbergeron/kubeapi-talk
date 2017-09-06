import com.fasterxml.jackson.databind.ObjectMapper
import io.fabric8.kubernetes.client.DefaultKubernetesClient

object GetPod extends App {

  val client = new DefaultKubernetesClient().inNamespace("default")
  val mapper = new ObjectMapper()

  val pod = client.pods.withName("my-pod").get()

  println(mapper.writerWithDefaultPrettyPrinter.writeValueAsString(pod))
}

