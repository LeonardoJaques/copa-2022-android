package me.dio.copa.catar.domain.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import me.dio.copa.catar.domain.repositories.MatchesRepository;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DisableNotificationUseCase_Factory implements Factory<DisableNotificationUseCase> {
  private final Provider<MatchesRepository> repositoryProvider;

  public DisableNotificationUseCase_Factory(Provider<MatchesRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DisableNotificationUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DisableNotificationUseCase_Factory create(
      Provider<MatchesRepository> repositoryProvider) {
    return new DisableNotificationUseCase_Factory(repositoryProvider);
  }

  public static DisableNotificationUseCase newInstance(MatchesRepository repository) {
    return new DisableNotificationUseCase(repository);
  }
}
