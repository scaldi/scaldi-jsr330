package scaldi.jsr330

import scaldi._

/** Injector that creates JSR 330 compliant bindings on-demand (when they are injected) */
class OnDemandAnnotationInjector
    extends MutableInjectorUser
    with InjectorWithLifecycle[OnDemandAnnotationInjector]
    with ShutdownHookLifecycleManager {
  private var bindings: List[BindingWithLifecycle]       = Nil
  private var lifecycleManager: Option[LifecycleManager] = None

  def getBindingInternal(identifiers: List[Identifier]): Option[BindingWithLifecycle] =
    identifiers
      .collect { case TypeTagIdentifier(tpe) => tpe }
      .map(tpe => tpe -> AnnotationBinding.extractIdentifiers(tpe)) match {
      case (tpe, resultingIdentifiers) :: Nil if Identifier.sameAs(resultingIdentifiers, identifiers) =>
        bindings.find(_ isDefinedFor identifiers) orElse {
          this.synchronized {
            bindings.find(_ isDefinedFor identifiers) orElse {
              val binding = new AnnotationBinding(
                Right(tpe),
                () => injector,
                resultingIdentifiers
              )

              // doesn't appear possible for `isEager` to be true, remove this code?
              if (binding.isEager) {
                lifecycleManager map binding.get getOrElse (throw new InjectException(
                  "Attempt to inject binding before OnDemandAnnotationInjector was initialized"
                ))
              }

              bindings = bindings :+ binding
              Some(binding)
            }
          }
        }
      case _ => None
    }

  def getBindingsInternal(identifiers: List[Identifier]): List[BindingWithLifecycle] =
    getBindingInternal(identifiers).toList

  protected def init(lifecycleManager: LifecycleManager): () => Unit = {
    this.lifecycleManager = Some(lifecycleManager)
    () => ()
  }
}
