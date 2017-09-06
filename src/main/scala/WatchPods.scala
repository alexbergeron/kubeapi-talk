import com.fasterxml.jackson.databind.ObjectMapper
import io.fabric8.kubernetes.client.{DefaultKubernetesClient, KubernetesClientException, Watcher}
import io.fabric8.kubernetes.api.model.Pod

object WatchPod extends App {
  val client = new DefaultKubernetesClient().inNamespace("default")
  val mapper = new ObjectMapper()

  def watchPods(watcher: PodWatcher): Unit = {
    client.pods.watch(watcher)
  }
  
  val watcher = new PodWatcher(retry = watchPods(_))
  watchPods(watcher)
}

class PodWatcher(retry: PodWatcher => Unit) extends Watcher[Pod] {
  def eventReceived(action: Watcher.Action, pod: Pod): Unit = {
    println(s"Pod ${action.name()} - ${pod.getMetadata.getName}")
  }

  def onClose(ex: KubernetesClientException): Unit = {
    println(s"Watcher closed on ${ex.getMessage}")
    retry(this)
  }
}
