package scaldi.jsr330

import javax.inject.{Provider => JProvider}

case class ScaldiProvider[T](impl: () => T) extends JProvider[T] {
  def get(): T = impl()
}
