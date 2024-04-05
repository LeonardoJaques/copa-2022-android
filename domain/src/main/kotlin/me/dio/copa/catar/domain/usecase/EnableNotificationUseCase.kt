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
public final class EnableNotificationUseCase_Factory implements Factory<EnableNotificationUseCase> {
  private final Provider<MatchesRepository> repositoryProvider;

  public EnableNotificationUseCase_Factory(Provider<MatchesRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public EnableNotificationUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static EnableNotificationUseCase_Factory create(
      Provider<MatchesRepository> repositoryProvider) {
    return new EnableNotificationUseCase_Factory(repositoryProvider);
  }

  public static EnableNotificationUseCase newInstance(MatchesRepository repository) {
    return new EnableNotificationUseCase(repository);
  }
}
