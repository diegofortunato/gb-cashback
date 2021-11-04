<h1 align="center">
   💲 <a href="#" alt="site do GB Cashback"> GB Cashback </a>
</h1>

<h2 align="center">
   Com o GB Cashback você controla todo o fluxo de revendores da plataforma, assim como suas compras e controle de Cashback sobre essas compras.
</h2>

## [](https://github.com/diegofortunato/gb-cashback#stack-)Stack  💻

-   Kotlin
-   Spring Boot
-   Postgres
-   Docker

## [](https://github.com/diegofortunato/gb-cashback#build--)Build  🚀

Certifique que você tenha o Docker instalado em sua maquina e rode os seguintes comandos:

-   Primeiro passo clone o projeto:  `https://github.com/diegofortunato/gb-cashback`
-   Entre na pasta raiz no local clonado.
-   Build o projeto com:  `gradle clean build`
-   Após o build execute o seguinte comando:  `docker build -t app.jar .`
-   Após esse comando. execute:  `docker-compose up`

Pronto, o projeto estara disponivel em:  `localhost:8080`

## [](https://github.com/diegofortunato/gb-cashback#teste-%EF%B8%8F)Teste  ⚙️

Uma Collection do Postman de exemplo esta disponivel aqui: [Collection](https://github.com/diegofortunato/gb-cashback/blob/master/docs/GB_Cashback.postman_collection.json)


## [](https://github.com/diegofortunato/gb-cashback#documeta%C3%A7%C3%A3o-)Documetação  📝

Você pode encontrar a documentação do projeto aqui: [Swagger-UI](http://localhost:8080/swagger-ui.html#/)
                                                    [Doc](https://github.com/diegofortunato/gb-cashback/blob/master/docs/swagger.yaml)
                                                    
## [](https://github.com/diegofortunato/gb-cashback#documeta%C3%A7%C3%A3o-)Health Check  🏥

Saúde da API esta disponivel aqui:  [Actuator](http://localhost:8080/actuator/health)

## [](https://github.com/diegofortunato/gb-cashback#autor-)Autor  🦸

Diego Fortunato Candido
