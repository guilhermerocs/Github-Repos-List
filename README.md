
 ## 👨🏻‍💻 Autor
- Created by [Guilherme Rocha](https://www.linkedin.com/in/guilherme-rocha-396458a6/)
- Guilherme Rocha, guilhermerocs@gmail.com


## Descrição do Projeto
- Uma aplicação Android que lista os repositórios públicos do Github.
- O usuário pode pesquisar um repositório listado.
- O usuário pode ver detalhes daquele repositório e do Owner do mesmo.


## Bibliotecas usadas
- [Retrofit](https://square.github.io/retrofit/), usado para comunicação Http.
- [MOSHI](https://github.com/square/moshi), usado para serializar o Json retornado pela API.
- [Koin](https://insert-koin.io/), usado para Injeção de dependência. 
- [Picasso](https://github.com/square/picasso), usado para fazer o load de imagens.
- [Lottie](https://github.com/airbnb/lottie-android), usado para carregar animações como a de loading.
- [Mockito](https://site.mockito.org/), usado nos testes unitários.


## Arquitetura utilizada
# Neste projeto foi utilizado Clean Architecture juntamente com o MVVM. 
- O pacote 'data' contém o repositório do sistema. Nele são feitas as chamadas para a API. Além disso, este pacote contém a configuração do 'RETROFIT'.
- O pacote 'domain' contém os 'usecases', modelos de retorno da API e a interface externa do 'Repository'.
- O pacote 'presentation' "contém a parte MVVM" do projeto. Temos os Fragments e Activity e suas respectivas ViewModels.
- Por fim, o pacote 'di' contém a configuração do 'Koin' para injeção de dependência.



## Passos para executar o projeto
- Clone este repositório
- Faça um sync do Gradle
- Vá em Build -> Make Project
- Execute a aplicação
