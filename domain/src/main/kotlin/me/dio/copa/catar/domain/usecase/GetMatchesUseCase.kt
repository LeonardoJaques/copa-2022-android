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
public final class GetMatchesUseCase_Factory implements Factory<GetMatchesUseCase> {
  private final Provider<MatchesRepository> repositoryProvider;

  public GetMatchesUseCase_Factory(Provider<MatchesRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetMatchesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetMatchesUseCase_Factory create(Provider<MatchesRepository> repositoryProvider) {
    return new GetMatchesUseCase_Factory(repositoryProvider);
  }

  public static GetMatchesUseCase newInstance(MatchesRepository repository) {
    return new GetMatchesUseCase(repository);
  }
}
